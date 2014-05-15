package test;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState.FieldName;
import client.PingballGUI;
import client.Sprite;

public class GUINetworkTest implements Runnable {
    private final ServerSocket connection;
    private final PingballGUI gui;
    
    public GUINetworkTest(int port) throws IOException {
        connection = new ServerSocket(10987);
        gui = new PingballGUI();
    }
    
    public void run() {
        try {
            Socket client = connection.accept();
            
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {}
            
            System.out.println("Client connected.");
            
            BufferedOutputStream out = new BufferedOutputStream(client.getOutputStream());
            //BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
            ByteBuffer ballX = ByteBuffer.allocate(NetworkProtocol.MESSAGE_LENGTH);
            ballX.putInt(Sprite.Ball.ID);            // class uid
            ballX.putInt(NetworkProtocol.getUID());  // instance uid
            ballX.putInt(FieldName.X.getUID());      // field uid
            ballX.putLong(10);                       // value
            
            ByteBuffer ballY = ByteBuffer.allocate(NetworkProtocol.MESSAGE_LENGTH);
            ballY.putInt(Sprite.Ball.ID);            // class uid
            ballY.putInt(NetworkProtocol.getUID());  // instance uid
            ballY.putInt(FieldName.X.getUID());      // field uid
            ballY.putLong(10);                       // value
            
            out.write(NetworkProtocol.PREAMBLE);
            out.write(ballX.array());
            out.write(NetworkProtocol.PREAMBLE);
            out.write(ballY.array());
            out.flush();
            
            System.out.println("Created ball?");
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("arguments: <port>");
            System.exit(1);
        }
        
        int port;
        
        try {
            port = Integer.parseInt(args[0]);
            
            GUINetworkTest test = new GUINetworkTest(port);
            test.run();
        } catch(NumberFormatException e) {
            System.out.println("arguments: <port>");
            System.exit(1);
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        System.out.println("Test finished.");
    }
}
