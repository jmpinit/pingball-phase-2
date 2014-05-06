package pingball.server;

import gameParts.Board;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Thread safety argument:
 * The only objects which are accessed by multiple threads are:
 * 
 *  clientNames -- any operation reading from or modifying this needs to obtain a lock on it
 *  (when a client connects / disconnects / gluing happens / balls are transferred between clients...)
 *  
 *  disconnects -- threadsafe BlockingQueue used and only operations called are peek, put and take (which block)
 *  
 * individual client's board objects
 * (when gluing happens, balls are passed, ...)
 * ball queues are implemented with threadsafe blocking queues
 * gluing requires server to obtain locks for both client boards
 * this is only ever done by the server -- no deadlocks possible
 * 
 * ungluing is only ever triggered by 1 of clients disconnecting, and requires
 * lock of the board still existing to update said board. No deadlock possible.
 * 
 * boards are timed by threadsafe blocking queues with messages sent by a single Timer thread.
 * 
 */
public class PingballServer {
    private final ServerSocket serverSocket;
    private final ConcurrentMap<String, Client> clientNames;
    private final BlockingQueue<Client> disconnects;
    private final int port;
    public static final int DEFAULT_PORT = 10987; // default port
    public static final int MAX_PORT_NUM = 65535;
    public static final int DC_CAPACITY = 10;
    
    public static final int FRAMERATE = 20;
    public static final int NUM_MILLISECONDS = 1000;

    
    /**
     * Constructor initializes everything
     * @param port
     * @throws IOException
     */
    public PingballServer(int port) throws IOException {
        // initialize everything
    	this.port = port;
    	serverSocket = new ServerSocket(port);
    	clientNames = new ConcurrentHashMap<String, Client>();
    	disconnects = new ArrayBlockingQueue<Client>(DC_CAPACITY);
    }

    private String StringListOfBoards() {
    	StringBuilder s = new StringBuilder();
    	synchronized(clientNames){
    		for (Client client : clientNames.values()){
    			s.append(client.getBoard().getName() + ", ");
    		}
    	}
    	if (s.length() > 0){
	    	s.delete(s.length()-2, s.length());
	    	s.append(".");
	    	return s.toString();
    	} else {
    		s.append("<NONE>");
    		return s.toString();
    	}
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
        Thread receiveClients = new Thread(new ReceiveClients(serverSocket, clientNames, disconnects));
        receiveClients.start();
        Thread disconClients = new Thread(new DisconnectClients(serverSocket, clientNames, disconnects));
        disconClients.start();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new myTimerTask(clientNames),0,NUM_MILLISECONDS / FRAMERATE);

     
        
        Scanner sc = new Scanner (System.in);
        System.out.println("Welcome to the Pingball server.");
        System.out.println("Boards available for gluing are: " + StringListOfBoards());
        System.out.println("To glue boards, type 'h NAME1 NAME2' or 'v NAME1 NAME2'.");
        while (sc.hasNextLine()) {
        	String line = sc.nextLine();
            String regex = "(h|v) ([a-zA-Z_0-9]+) ([a-zA-Z_0-9]+)";
            if ( ! line.matches(regex)) {
                 System.out.println("Invalid gluing command.");
            } else {
            	String[] args = line.split(" ");
            	Board firstBoard = null;
            	Board secondBoard = null;
	        	synchronized(clientNames){
	        		if (clientNames.keySet().contains(args[1]) && clientNames.keySet().contains(args[2])){
	        			firstBoard = clientNames.get(args[1]).getBoard();
	        			secondBoard = clientNames.get(args[2]).getBoard();
	        		}
	        	}
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
            System.out.println("Boards available for gluing are: " + StringListOfBoards());
        }
        sc.close();
    }

    
    private class myTimerTask extends TimerTask {
    	
    	private final ConcurrentMap<String, Client> clientNames;
    	private Integer i;
    	
    	public myTimerTask(ConcurrentMap<String, Client> clientNames) {
    		this.clientNames = clientNames;
    		this.i = 0;
    	}
    
        @Override
        public void run() {
        	Collection<Client> clients = clientNames.values();
        	for (Client client: clients){
        			client.sendTime(i);
        		}
        	i++;
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
     * Initializes the server
     */
    
    public static void runPingballServer(int port) throws IOException {
        
        PingballServer server = new PingballServer(port);
        server.serve();
    }
}
