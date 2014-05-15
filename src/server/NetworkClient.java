package server;

import game.Board;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

import client.PingKeyEvent;
import boardfile.BoardFactory;
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
    private final BufferedReader in;
    private final BufferedOutputStream out;
    
    private final List<PingKeyEvent> keyEvents = Collections.synchronizedList(new ArrayList<PingKeyEvent>());
    private final BlockingQueue<String> userInputQueue;
    private final String source;
    private Board board;
    private final boolean online;
    
    private boolean paused;
    private boolean disconnect;

    public NetworkClient(BlockingQueue<String> userInputQueue, Board board, String boardSource, Socket socket, boolean online) {
        this.userInputQueue = userInputQueue;
        this.source = boardSource;
        this.board = board;
        this.online = online;
        
        this.paused = false;
        this.disconnect = false;
        
        // setup communication with the client
        BufferedOutputStream out = null;
        BufferedReader in = null;
        
        if(online) {
            try {
                out = new BufferedOutputStream(socket.getOutputStream());
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch(IOException e) {
                e.printStackTrace();
                // TODO disconnect clients we can't communicate with
            }
        } else {
            // TODO
            //out = new PrintWriter(new OutputStreamWriter(System.out));
        }
        
        this.in = in;
        this.out = out;
        
        (new Thread(new Commander())).start();
    }

    @Override
    public void run() {
        while (!disconnect) {
            if(!paused) {
                try {
                    synchronized(Board.class) {
                        List<NetworkEvent> events = board.step(keyEvents);
                        keyEvents.clear();
                        
                        // send event updates
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
                    Thread.sleep((long)((1.0/30.0)*1000.0)); // FIXME
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    disconnect = true; // disconnect this client
                }
            }
        }
    }
    
    class Commander implements Runnable {
        public void run() {
            while(!disconnect) {
                try {
                    String message;
                    while((message = in.readLine()) != null) {
                        switch(message.charAt(0)) {
                            case NetworkProtocol.MESSAGE_KEYPRESSED:
                                keyPressed(message.charAt(2));
                                break;
                            case NetworkProtocol.MESSAGE_KEYRELEASED:
                                keyReleased(message.charAt(2));
                                break;
                            default:
                                userInputQueue.add(message);
                        }
                    }
                } catch(IOException e) {
                    disconnect = true; // disconnect this client
                }
            }
        }
    }
    
    private void keyPressed(char key) {
        keyEvents.add(new PingKeyEvent(key, true));
    }
    
    private void keyReleased(char key) {
        keyEvents.add(new PingKeyEvent(key, false));
    }
    
    public void pause() {
        paused = true;
    }
    
    public void play() {
        paused = false;
    }
    
    public void restart() {
        this.board = BoardFactory.parse(this.source);
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
