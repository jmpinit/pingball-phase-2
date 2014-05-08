package server;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;
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
        
        if(!client.isOnline() || client.getBoard().getName() == null) {
            // run the clock yourself
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new myTimerTask(client), 0, PingballServer.NUM_MILLISECONDS/PingballServer.FRAMERATE);
        }
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

    /**
     * TODO
     * timer loop:
     * figure out what balls were added to you
     * add them to board
     * run board.step()
     * print to out or System.out (depending on client.isOnline())
     * mail balls to other clients --> synchronize on clientNames
     * flush inbox
     * 
     * surround in try/catch:
     * upon error: call server to delete from clientNames (disconnects)
     * tell each neighbor board to seal up wall --> synchronize on clientNames
     */

    private class myTimerTask extends TimerTask {
        private final Client client;
        private Integer i;

        public myTimerTask(Client client) {
            this.client = client;
            this.i = 0;
        }

        @Override
        public void run() {
            client.sendTime(i);
            i++;
        }
    }
}
