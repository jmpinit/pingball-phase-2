package server;

import game.Board;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import boardfile.BoardFactory;

/**
 * Class activated by client to play Pingball.
 * @author yqlu
 *
 */
public class PingballClient {


    public static final int DEFAULT_PORT = 10987; // default port
    public static final int MAX_PORT_NUM = 65535;

    /**
     * Constructor to be called for online play.
     * @param host hostname to connect to
     * @param port port of host to connect to
     * @param file constructor to create File
     * @throws IOException if connection not successful.
     */
    public PingballClient(String host, int port, String filename) throws IOException {
        Socket socket = null;
        PrintWriter out = null;    	 
        BufferedReader in = null;

        try {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Could not find host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not connect to host.");
            System.exit(1);
        }
        String fromServer; 

        FileReader in2;
        try {
            String content = BoardFactory.readFile(filename, StandardCharsets.UTF_8);
            out.println(content);
            out.println("STOP");
        } catch (IOException e) {
            e.printStackTrace();
        }


        while ((fromServer = in.readLine()) != null) {
            System.out.println(fromServer);
        }
    }



    /** constructor for single player offline play
     * 
     * @param file File to be parsed to initialize the board
     * @throws IOException if reading file is unsuccessful
     */
    public PingballClient(String filename) throws IOException {
        String content = BoardFactory.readFile(filename, StandardCharsets.UTF_8);
        Board board = BoardFactory.parse(content);
        BlockingQueue<Integer> timeQueue = new ArrayBlockingQueue<Integer>(5);
        Client client = new Client(board, null, false, timeQueue); // 
        Thread t = new Thread(new ClientRunnable(client, new ArrayBlockingQueue<Client>(1))); // disconnects
        t.start();        
    }

    public static void main(String[] args) {
        // Command-line argument parsing is provided. Do not change this method.
        boolean onlinePlay = false;
        String host = "";
        int port = DEFAULT_PORT;
        String filename = null;
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        try {
            while ( ! arguments.isEmpty()) {
                String flag = arguments.remove();
                try {
                    if (flag.equals("--host")) {
                        onlinePlay = true;
                        host = arguments.remove();
                    } else if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        if (port < 0 || port > MAX_PORT_NUM) {
                            throw new IllegalArgumentException("port " + port + " out of range");
                        }
                    } else if (flag.equals("--file")) {
                        filename = arguments.remove();
                    } else {
                        throw new IllegalArgumentException("unknown option: \"" + flag + "\"");
                    }
                } catch (NoSuchElementException nsee) {
                    throw new IllegalArgumentException("missing argument for " + flag);
                } catch (NumberFormatException nfe) {
                    throw new IllegalArgumentException("unable to parse number for " + flag);
                }
            }
        } catch (IllegalArgumentException iae) {
            System.err.println(iae.getMessage());
            System.err.println("usage: PingballClient [--host HOST] [--port PORT] --file FILE");
            return;
        }


        try {
            if (onlinePlay) {
                new PingballClient(host, port, filename);
            } else {
                new PingballClient(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
