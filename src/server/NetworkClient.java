package server;

import game.Board;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Runs in a separate thread for every client.
 * # Thread safety argument
 * - board is directly modified by the thread for this Runnable when step is called.
 *      But only one NetworkClient calls step on a given board, so the mutation is confined.
 * - disconnect is used as a flag to handle disconnection. This thread does not directly
 *      remove the client from the server, so the 
 */
public class NetworkClient implements Runnable {
    private final PrintWriter out;
    
    private final Board board;
    private final boolean online;
    
    private boolean disconnect;

    public NetworkClient(Board board, Socket socket, boolean online) {
        this.board = board;
        this.online = online;
        
        this.disconnect = false;
        
        // setup communication with the client
        PrintWriter out = null;
        if(online) {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch(IOException e) {
                e.printStackTrace();
                // TODO disconnect clients we can't communicate with
            }
        } else {
            out = new PrintWriter(new OutputStreamWriter(System.out));
        }
        
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {            
            try {
                synchronized(Board.class) {
                    board.step();
                    out.println(board);
                }
                out.println((char)12); // mark end of board
                Thread.sleep(100); // FIXME
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if(out.checkError() && online)
                disconnect = true;
        }
    }
    
    public boolean shouldDisconnect() {
        return disconnect;
    }
    
    public String getName() {
        return board.getName();
    }
    
    public Board getBoard() {
        return board;
    }
}
