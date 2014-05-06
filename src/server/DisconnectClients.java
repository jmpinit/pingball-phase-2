package pingball.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentMap;

public class DisconnectClients implements Runnable {

    private final ServerSocket serverSocket;
    private final ConcurrentMap<String, Client> clientNames;
    private final BlockingQueue<Client> disconnects;

    /**
     * Constructor
     * @param clients
     * @param serverSocket
     * @param timer
     */
    public DisconnectClients(ServerSocket serverSocket, ConcurrentMap<String, Client> clientNames, BlockingQueue<Client> disconnects) {
        this.serverSocket = serverSocket;
        this.clientNames = clientNames;
        this.disconnects = disconnects;
    }

    @Override
    public void run() {
        Client client;
        while (true) {
            try {
                client = disconnects.take();
                // TODO
                //List<Client> others = client.getBoard().getNeighbors();
                System.out.println(client.getBoard().getName() + " just disconnected!");
                synchronized(this.clientNames) {
                    Set<String> names = this.clientNames.keySet();
                    for (String name: names) {
                        Client other = clientNames.get(name);
                        other.getBoard().disjoin(client.getBoard());
                    }
                    clientNames.remove(client.getBoard().getName());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
