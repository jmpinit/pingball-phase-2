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
            
            create(out, Sprite.Absorber.ID, 0, 0);
            create(out, Sprite.CircularBumper.ID, 1, 0);
            create(out, Sprite.Flipper.ID, 2, 0);
            create(out, Sprite.Portal.ID, 3, 0);
            create(out, Sprite.SquareBumper.ID, 4, 0);
            create(out, Sprite.TriangularBumper.ID, 5, 0);
            create(out, Sprite.Wall.ID, 6, 0);
            
            try {
                Thread.sleep(3000);
            } catch(InterruptedException e) { }
            
            for(int i=0; i < 100; i++) {
                create(out, Sprite.Ball.ID, (int)(Math.random()*20), (int)(Math.random()*20));
                
                try {
                    Thread.sleep((long)((1.0/60.0)*1000));
                } catch(InterruptedException e) { }
            }
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private void create(BufferedOutputStream out, int spriteID, int x, int y) throws IOException {
        int instanceID = NetworkProtocol.getUID();
        
        ByteBuffer ballX = ByteBuffer.allocate(NetworkProtocol.MESSAGE_LENGTH);
        ballX.putInt(spriteID);            // class uid
        ballX.putInt(instanceID);  // instance uid
        ballX.putInt(FieldName.X.getUID());      // field uid
        ballX.putLong(x);                       // value
        
        ByteBuffer ballY = ByteBuffer.allocate(NetworkProtocol.MESSAGE_LENGTH);
        ballY.putInt(spriteID);            // class uid
        ballY.putInt(instanceID);  // instance uid
        ballY.putInt(FieldName.Y.getUID());      // field uid
        ballY.putLong(y);                       // value
        
        out.write(NetworkProtocol.PREAMBLE);
        out.write(ballX.array());
        out.write(NetworkProtocol.PREAMBLE);
        out.write(ballY.array());
        out.flush();
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
