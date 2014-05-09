package server;

import game.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import boardfile.BoardFactory;

/**
 * # Thread safety argument:
 * - connector creates new client threads for each connection.
 *      The creation part of that operation is thread-safe because it is fully confined to the connector thread.
 *      The registration part of the operation (adding the client to the list of connected clients) is
 *      thread-safe because it synchronizes on the list of clients and in doing so prevents the only other thread
 *      that mutates the list of clients, disconnector, from interleaving a change in the list.
 * - disconnector cleans up after clients who have disconnected.
 *      To tell whether a client has disconnected, disconnector checks an externally read-only flag on each client.
 *      If the flag is set then it synchronizes on the list of clients and removes the client from the list.
 *      It also synchronizes on the Board class and registers the removal of the client's board with the boards it
 *      was connected to. Any code that wants to change a particular Board synchronizes on the Board class, so no Board
 *      operations can be interleaved. Specifically, the only other mutators of boards outside of board's own hierarchy
 *      are PingballServer and NetworkClient. Both of them synchronize on the Board class during Board operations.
 */
public class PingballServer {
    public static final int DEFAULT_PORT = 10987; // default port
    public static final int MAX_PORT_NUM = 65535;
    public static final int DC_CAPACITY = 10;

    public static final int FRAMERATE = 20;
    public static final int NUM_MILLISECONDS = 1000;
    
    private final ServerSocket serverSocket;
    private final ConcurrentMap<String, NetworkClient> clientFromName;
    
    /**
     * Constructor initializes everything
     * @param port
     * @throws IOException
     */
    public PingballServer(int port) throws IOException {
        // initialize everything
        serverSocket = new ServerSocket(port);
        clientFromName = new ConcurrentHashMap<String, NetworkClient>();
    }

    /**
     * Run the server.
     * A specialized thread (receiveClient) listens for client connections and handles them
     * by creating new threads for each client.
     * 
     * The main thread focuses on reading gluing instructions in from System.in.
     * 
     * Never returns unless an exception is thrown.
     * 
     * @throws IOException if the main server socket is broken
     *                     (IOExceptions from individual clients do *not* terminate serve())
     */
    public void serve() throws IOException {
        // start allowing clients to connect
        Thread receiveClients = new Thread(connector);
        receiveClients.start();
        
        // start disconnecting clients that request it
        Thread disconClients = new Thread(disconnector);
        disconClients.start();

        // server CLI user interface
        Scanner sc = new Scanner (System.in);
        System.out.println("Welcome to the Pingball server.");
        System.out.println("Boards available for gluing are: " + getListOfBoards());
        System.out.println("To glue boards, type 'h NAME1 NAME2' or 'v NAME1 NAME2'.");
        
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String regex = "(h|v) ([a-zA-Z_0-9]+) ([a-zA-Z_0-9]+)";
            
            if (!line.matches(regex)) {
                System.out.println("Invalid gluing command.");
            } else {
                String[] args = line.split(" ");
                Board firstBoard = null;
                Board secondBoard = null;
                
                synchronized(clientFromName) {
                    if (clientFromName.keySet().contains(args[1]) && clientFromName.keySet().contains(args[2])) {
                        firstBoard = clientFromName.get(args[1]).getBoard();
                        secondBoard = clientFromName.get(args[2]).getBoard();
                    }
                }
                
                synchronized (Board.class) {
                    if (firstBoard != null && secondBoard != null) {
                        if ("h".equals(args[0])) {
                            firstBoard.joinRightWallTo(secondBoard);
                            secondBoard.joinLeftWallTo(firstBoard);
                        } else {
                            firstBoard.getName();
                            secondBoard.getName();
                            firstBoard.joinBottomWallTo(secondBoard);
                            secondBoard.joinTopWallTo(firstBoard);
                        }
                        
                        System.out.println("Gluing command successful.");
                    } else {
                        System.out.println("Invalid gluing command.");
                    }
                }
            }
            System.out.println("Boards available for gluing are: " + getListOfBoards());
        }
        sc.close();
    }

    /**
     * @return a message containing the names of the boards of the connected clients.
     */
    private String getListOfBoards() {
        StringBuilder s = new StringBuilder();
        
        synchronized(clientFromName) {
            for(NetworkClient client : clientFromName.values()) {
                s.append(client.getName() + ", ");
            }
        }
        
        if (s.length() > 0) {
            s.delete(s.length()-2, s.length());
            s.append(".");
            return s.toString();
        } else {
            s.append("<NONE>");
            return s.toString();
        }
    }

    /**
     * Reads arguments passed in from command line and accordingly initializes server
     */
    public static void main(String[] args) {
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        int port = DEFAULT_PORT;
        
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                
                try {
                    if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        
                        if (port < 0 || port > MAX_PORT_NUM) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else {
                        throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballServer [--port PORT]");
            return;
        }

        try {
            runPingballServer(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks for clients who have flagged themselves as disconnected,
     * removes them from the server, and closes the sides of boards on other clients
     * that previously opened onto 
     */
    Runnable disconnector = new Runnable() {
        @Override
        public void run() {
            while (true) {
                for(NetworkClient client: clientFromName.values()) {
                    if(client.shouldDisconnect()) {
                        synchronized(clientFromName) {
                            clientFromName.remove(client.getBoard().getName());
                        }
                        
                        Set<String> names = clientFromName.keySet();
                        Board clientBoard = client.getBoard();
                        
                        synchronized(Board.class) {
                            for (String name: names) {
                                NetworkClient other = clientFromName.get(name);
                                other.getBoard().disjoin(clientBoard);
                            }
                        }
                        
                        System.out.println(client.getBoard().getName() + " just disconnected!");
                    }
                }
            }
        }
    };
    
    /**
     * Listens to the server socket and spins off threads
     * running new NetworkClients to handle connections.
     */
    Runnable connector = new Runnable() {
        @Override
        public void run() {
            while (true) {
                Socket clientSocket;
                
                try {
                    // block until a client connects
                    // set up communication to the client
                    clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));            
                    
                    // read in the board
                    StringBuilder content = new StringBuilder();
                    String line = in.readLine();
                    while (!(line==null) && !line.equals("STOP")) {
                        content.append(line + "\n");
                        line = in.readLine();
                    }
                    
                    // create a Board from the board data
                    Board board = BoardFactory.parse(content.toString());
                    
                    // add the data for the client
                    if (board.getName() != null) { // if client with that name doesn't already exist
                        synchronized(clientFromName) {
                            NetworkClient client = new NetworkClient(board, clientSocket, true);
                            
                            clientFromName.put(board.getName(), client);
                            
                            Thread t = new Thread(client);
                            t.start();
                            
                            System.out.println(board.getName() + " just connected.");
                        }
                    } else {
                        try {
                            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                            out.println("Client with your name has already joined the server.");
                        } catch(IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    /**
     * Initializes the server
     */

    public static void runPingballServer(int port) throws IOException {
        PingballServer server = new PingballServer(port);
        server.serve();
    }
}
