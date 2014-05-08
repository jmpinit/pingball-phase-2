package client;

import game.Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
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
import java.util.Random;

import javax.swing.JFrame;

import server.NetworkClient;
import boardfile.BoardFactory;

public class PingballGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public static final int DEFAULT_PORT = 10987; // default port
    public static final int MAX_PORT_NUM = 65535;
    
    public static final int BOARD_WIDTH = 22;
    public static final int BOARD_HEIGHT = 22;
    
    private Canvas canvas;
    private Color[] lookup;

    /**
     * Constructor to be called for online play.
     * @param host hostname to connect to
     * @param port port of host to connect to
     * @param file constructor to create File
     * @throws IOException if connection not successful.
     */
    public PingballGUI(String host, int port, String filename) throws IOException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        /*
         * Setup networking
         */

        String name = "unknown";
        
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
        
        try {
            String content = BoardFactory.readFile(filename, StandardCharsets.UTF_8);
            Board board = BoardFactory.parse(content);
            name = board.getName();
            out.println(content);
            out.println("STOP");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                System.out.println("key pressed");
                // TODO send input to server
            }

            public void keyReleased(KeyEvent e) {
                System.out.println("key released");
            }
        });
        
        makeGUI(name);
        
        String line;
        StringBuilder boardBuilder = new StringBuilder();
        while((line = in.readLine()) != null) {
            if(line.length() > 0) {
                if((int)line.charAt(0) == 12) {
                    parseEvent(boardBuilder.toString());
                    boardBuilder.setLength(0);
                } else {
                    boardBuilder.append(line+"\n");
                }
            }
        }
    }

    /**
     * Constructor for single player local play.
     * @param file File to be parsed to initialize the board
     * @throws IOException if reading file is unsuccessful
     */
    public PingballGUI(String filename) throws IOException {
        String content = BoardFactory.readFile(filename, StandardCharsets.UTF_8);
        Board board = BoardFactory.parse(content);
        NetworkClient client = new NetworkClient(board, null, false);
        Thread t = new Thread(client); // disconnects
        t.start();
        
        makeGUI(board.getName());
    }
    
    private void makeGUI(String name) {
        setTitle(name);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        canvas = new Canvas(200, 200);
        add(canvas, BorderLayout.CENTER);
        
        lookup = new Color[127];
        Random generator = new Random(1337);
        for(int i=0; i < lookup.length; i++)
            lookup[i] = new Color(127+generator.nextInt(128), 127+generator.nextInt(128), 127+generator.nextInt(128));
        
        pack();
        setVisible(true);        
    }
    
    public void parseEvent(String board) {
        String[] lines = board.split("\n");
        
        for(int y=0; y < lines.length && y < BOARD_HEIGHT; y++) {
            String line = lines[y];
            for(int x=0; x < line.length() && x < BOARD_WIDTH; x++) {
                canvas.set(x, y, lookup[line.charAt(x)]);
            }
        }
        
        canvas.repaint();
    }

    public static void main(String[] args) {
        boolean onlinePlay = false;
        String host = "";
        int port = DEFAULT_PORT;
        String filename = null;
        
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        
        try {
            while(!arguments.isEmpty()) {
                String flag = arguments.remove();
                
                try {
                    if (flag.equals("--host")) {
                        onlinePlay = true;
                        host = arguments.remove();
                    } else if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        
                        if(port < 0 || port > MAX_PORT_NUM)
                            throw new IllegalArgumentException("port " + port + " out of range");
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
            // TODO constructors should construct and then exit (put 4=-14
            if(onlinePlay) {
                new PingballGUI(host, port, filename);
            } else {
                new PingballGUI(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
