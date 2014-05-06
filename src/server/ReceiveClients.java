package server;

import game.Board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

import boardfile.BoardFactory;

/**
 * Specialized thread to focus on receiving client connections
 * @author yqlu
 *
 */
public class ReceiveClients implements Runnable {

    private final ServerSocket serverSocket;
    private final ConcurrentMap<String, Client> clientNames;
    private final BlockingQueue<Client> disconnects;

    /**
     * Constructor
     * @param clients
     * @param serverSocket
     * @param timer
     */
    public ReceiveClients(ServerSocket serverSocket, ConcurrentMap<String, Client> clientNames, BlockingQueue<Client> disconnects) {
        this.serverSocket = serverSocket;
        this.clientNames = clientNames;
        this.disconnects = disconnects;
    }

    @Override
    public void run() {
        while (true) {
            // block until a client connects
            Socket clientSocket;
            try {
                clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));            
                String line = in.readLine();
                StringBuilder content = new StringBuilder();
                while (!line.equals("STOP")) {
                    content.append(line + "\n");
                    line = in.readLine();
                }
                Board board = BoardFactory.parse(content.toString());
                synchronized(clientNames){
                    Client client = new Client(board, clientSocket, true, new ArrayBlockingQueue<Integer>(5));
                    if (board.getName() != null){
                        clientNames.put(board.getName(), client);
                    }
                    Thread t = new Thread(new ClientRunnable(client, disconnects));
                    t.start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
