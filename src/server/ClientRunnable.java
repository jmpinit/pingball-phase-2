package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

/**
 * Runnable that runs for every client
 */
public class ClientRunnable implements Runnable {
    private final Client client;
    private final BlockingQueue<Client> pendingDisconnects;
    private final PrintWriter out;

    public ClientRunnable(Client client, BlockingQueue<Client> pendingDisconnects) {
        this.client = client;
        this.pendingDisconnects = pendingDisconnects;
        PrintWriter out = null;
        
        // setup communication with the client
        if(client.isOnline()) {
            try {
                out = new PrintWriter(this.client.getSocket().getOutputStream(), true);
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
                client.getBoard().step();
                out.println(client.getBoard());
                out.println((char)12); // mark end of board
                Thread.sleep(100); // FIXME
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (out.checkError()) {
                if (client.isOnline()) {
                    try {
                        pendingDisconnects.put(client);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}
