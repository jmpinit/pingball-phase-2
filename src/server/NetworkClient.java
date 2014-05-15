package server;

import game.Board;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.List;

import server.NetworkProtocol.NetworkEvent;
import server.NetworkProtocol.NetworkState.FieldName;

/**
 * Runs in a separate thread for every client.
 * # Thread safety argument
 * - board is directly modified by the thread for this Runnable when step is called.
 *      But only one NetworkClient calls step on a given board, so the mutation is confined.
 * - disconnect is used as a flag to handle disconnection. This thread does not directly
 *      remove the client from the server, so the 
 */
public class NetworkClient implements Runnable {
    private final BufferedOutputStream out;
    
    private final Board board;
    private final boolean online;
    
    private boolean disconnect;

    public NetworkClient(Board board, Socket socket, boolean online) {
        this.board = board;
        this.online = online;
        
        this.disconnect = false;
        
        // setup communication with the client
        BufferedOutputStream out = null;
        if(online) {
            try {
                out = new BufferedOutputStream(socket.getOutputStream());
            } catch(IOException e) {
                e.printStackTrace();
                // TODO disconnect clients we can't communicate with
            }
        } else {
            // TODO
            //out = new PrintWriter(new OutputStreamWriter(System.out));
        }
        
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {            
            try {
                synchronized(Board.class) {
                    List<NetworkEvent> events = board.step();
                    
                    for(NetworkEvent event: events) {
                        ByteBuffer message = ByteBuffer.allocate(NetworkProtocol.MESSAGE_LENGTH);
                        message.putInt(event.getTypeUID());
                        message.putInt(event.getInstanceUID());
                        message.putInt(event.getFieldUID());
                        message.putLong(event.getValue());
                        
                        out.write(NetworkProtocol.PREAMBLE);
                        out.write(message.array());
                        out.flush();
                    }
                }
                Thread.sleep(100); // FIXME
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                shouldDisconnect();
                break; // disconnect this client
            }
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
