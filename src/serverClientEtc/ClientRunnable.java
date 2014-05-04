package serverClientEtc;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Runnable that runs for every client
 * @author yqlu
 *
 */
public class ClientRunnable implements Runnable {
	private final Client client;
	private final BlockingQueue<Client> disconnects;
    private final PrintWriter out;


	public ClientRunnable(Client client, BlockingQueue<Client> disconnects) {
		this.client = client;
		this.disconnects = disconnects;
		PrintWriter tmpOut = null;
		if (client.isOnline()) {
			try {
				tmpOut = new PrintWriter(this.client.getSocket().getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();

			}
		} else {
			tmpOut = new PrintWriter(new OutputStreamWriter(System.out));
		}
		this.out = tmpOut;
		
		if (!client.isOnline() || client.getBoard().getName() == null){
			// run the clock yourself
	        Timer timer = new Timer();
	        timer.scheduleAtFixedRate(new myTimerTask(client),0,PingballServer.NUM_MILLISECONDS/PingballServer.FRAMERATE);
		}
	}

	@Override
	public void run() {
		while (true){
			Integer t = client.getTime();
        	try {
				client.getBoard().step();
				out.println(client.getBoard());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        	if (out.checkError()) {
				if (client.isOnline()) {
					try {
						disconnects.put(client);
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
