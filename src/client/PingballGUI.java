package client;

import game.Board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import server.NetworkClient;
import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState.Field;
import boardfile.BoardFactory;

public class PingballGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public static final int DEFAULT_PORT = 10987; // default port
    public static final int MAX_PORT_NUM = 65535;
    
    public static final int BOARD_WIDTH = 22;
    public static final int BOARD_HEIGHT = 22;
    
    private ConcurrentMap<Integer, Sprite> sprites;
    
    /*
     * GUI Elements
     */
    
    private SpriteRenderer canvas;
    private JMenuBar menuBar;

    /**
     * Constructor to be called for online play.
     * @param host hostname to connect to
     * @param port port of host to connect to
     * @param file constructor to create File
     * @throws IOException if connection not successful.
     */
    public PingballGUI() {
        String name = "unknown";
        
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
        
        connect("localhost", 10987); // FIXME debugging
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
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        
        menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("Game");
        menuFile.setMnemonic(KeyEvent.VK_F);
        menuFile.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menuFile);
        
        JMenuItem itemSelectBoard = new JMenuItem("Select Board");
        itemSelectBoard.setMnemonic(KeyEvent.VK_B);
        menuFile.add(itemSelectBoard);
        
        JMenuItem itemRestart = new JMenuItem("Restart");
        itemRestart.setMnemonic(KeyEvent.VK_R);
        menuFile.add(itemRestart);
        
        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.setMnemonic(KeyEvent.VK_ESCAPE);
        menuFile.add(itemExit);
        
        setJMenuBar(menuBar);
        
        canvas = new SpriteRenderer(300, 300);
        
        JPanel gameControls = new JPanel();
        gameControls.setLayout(new BoxLayout(gameControls, BoxLayout.LINE_AXIS));
        gameControls.add(new JButton("Pause"));
        
        JPanel netControls = new JPanel();
        netControls.setLayout(new BoxLayout(netControls, BoxLayout.LINE_AXIS));
        netControls.add(new JLabel("Hostname:"));
        netControls.add(new JTextField("test"));
        netControls.add(new JLabel("Port:"));
        netControls.add(new JTextField("123"));
        netControls.add(new JButton("Connect"));
        
        content.add(gameControls, BorderLayout.NORTH);
        content.add(canvas, BorderLayout.CENTER);
        content.add(netControls, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);
        
        pack();
        setVisible(true);        
    }
    
    private void connect(String host, int port) {
        try {
            NetworkListener listener = new NetworkListener(host, port);
            Thread networkingThread = new Thread(listener);
            networkingThread.start();
        } catch (UnknownHostException e) {
            System.err.println("Could not find host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Could not connect to host.");
            System.exit(1);
        }
    }
    
    private class NetworkListener implements Runnable {
        private final Socket socket;
        private final PrintWriter out;
        private final BufferedInputStream in;
        
        private boolean listening;
        
        public NetworkListener(String host, int port) throws IOException {
            socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedInputStream(socket.getInputStream());
            
            listening = true;
        }
        
        public void run() {
            /*try {
                String content = BoardFactory.readFile(filename, StandardCharsets.UTF_8);
                Board board = BoardFactory.parse(content);
                name = board.getName();
                out.println(content);
                out.println("STOP");
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            
            while(listening) {        
                System.out.println("listening");

                try {
                    while(true) {
                        for(byte[] preamble = new byte[NetworkProtocol.PREAMBLE.length];
                                !Arrays.equals(preamble, NetworkProtocol.PREAMBLE);
                                in.read(preamble, 0, preamble.length)) { }
                        
                        byte[] message = new byte[NetworkProtocol.MESSAGE_LENGTH];
                        in.read(message, 0, NetworkProtocol.MESSAGE_LENGTH);
                        
                        boolean successful = parseMessage(message);
                        if(!successful)
                            System.out.println("Message not parsed successfully.");
                    }
                } catch(IOException e) {
                    e.printStackTrace();
                } finally {
                    stop();
                }
            }
        }
        
        private boolean parseMessage(byte[] message) {
            if(message.length == NetworkProtocol.MESSAGE_LENGTH) {
                ByteBuffer buffer = ByteBuffer.allocate(message.length);                    
                buffer.put(message);
                buffer.position(0);
                
                int spriteID = buffer.getInt();
                int instanceID = buffer.getInt();
                int fieldID = buffer.getInt();
                long value = buffer.getLong();
    
                // update
                canvas.update(spriteID, instanceID, new Field(fieldID, value));
                
                return true;
            } else {
                return false;
            }
        }
        
        public void stop() {
            try {
                socket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
            
            listening = false;
        }
    }

    public static void main(String[] args) {
        /*boolean onlinePlay = false;
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
        }*/
        
        new PingballGUI();

        /*try {
            // TODO constructors should construct and then exit (put 4=-14
            if(onlinePlay) {
                new PingballGUI(host, port, filename);
            } else {
                new PingballGUI(filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
