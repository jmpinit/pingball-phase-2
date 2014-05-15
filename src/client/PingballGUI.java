package client;

import game.Board;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState.Field;
import boardfile.BoardFactory;
/**
 * Thread safety argument:
 * - All information passing between network and GUI is threadsafe because it synchronizes on the writer to the network protocol.
 * Therefore, messages must be handled in the order they are given, and will only be processed when the previous one is done processing.
 * 
 * All GUI-related listeners are threadsafe due to Swing's event dispatch thread. All of these threads are queued and dealt with
 * in the order they occur. Therefore, the next GUI action will not occur until the previous one has been completed. 
 * @author meghana
 *
 */
public class PingballGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public static final int DEFAULT_PORT = 10987; // default port
    public static final int MAX_PORT_NUM = 65535;
    
    public static final int BOARD_WIDTH = 22;
    public static final int BOARD_HEIGHT = 22;
    
    private static final String BOARDS_DIRECTORY = "./boards";
    
    private static final String ARG_FILENAME = "filename";
    private static final String ARG_HOST = "host";
    private static final String ARG_PORT = "port";
    
    private String filename;
    
    private NetworkListener listener;
    private PrintWriter out;
        
    /*
     * GUI Elements
     */
    
    private SpriteRenderer canvas;
    private JMenuBar menuBar;
    private JTextField fieldHost, fieldPort;

    /**
     * Constructor to be called for online play.
     * @param host hostname to connect to
     * @param port port of host to connect to
     * @param file constructor to create File
     * @throws IOException if connection not successful.
     */
    public PingballGUI(Map<String, String> argMap) {
        makeGUI();
        
        if(argMap.containsKey(ARG_HOST))
            fieldHost.setText(argMap.get(ARG_HOST));
        else
            fieldHost.setText("localhost");
        
        if(argMap.containsKey(ARG_PORT))
            fieldPort.setText("" + argMap.get(ARG_PORT));
        else
            fieldPort.setText("" + DEFAULT_PORT);
        
        if(argMap.containsKey(ARG_FILENAME))
            this.filename = argMap.get(ARG_FILENAME);
    }

    private void makeGUI() {
        setTitle("Pingball");
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
        
        JMenuItem itemColorScheme = new JMenuItem("Color Scheme");
        itemExit.setMnemonic(KeyEvent.VK_C);
        menuFile.add(itemColorScheme);
        
        itemSelectBoard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeBoard();
            }
        });
        
        itemRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restart();
            }
        });
        
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        itemColorScheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectColors();
            }
        });
        
        setJMenuBar(menuBar);
        
        canvas = new SpriteRenderer(300, 300);
        
        final JButton pauseButton = new JButton("Pause");
        pauseButton.setActionCommand("pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("pause")) {
                    pause();
                    pauseButton.setActionCommand("play");
                    pauseButton.setText("play");
                } else if(e.getActionCommand().equals("play")) {
                    play();
                    pauseButton.setActionCommand("pause");
                    pauseButton.setText("pause");
                }
            }
        });
        
        JPanel gameControls = new JPanel();
        gameControls.setLayout(new BoxLayout(gameControls, BoxLayout.LINE_AXIS));
        gameControls.add(pauseButton);
        
        JPanel netControls = new JPanel();
        netControls.setLayout(new BoxLayout(netControls, BoxLayout.LINE_AXIS));
        netControls.add(new JLabel("Hostname:"));
        fieldHost = new JTextField();
        netControls.add(fieldHost);
        netControls.add(new JLabel("Port:"));
        fieldPort = new JTextField();
        netControls.add(fieldPort);
        JButton buttonConnect = new JButton("Connect");
        netControls.add(buttonConnect);
        
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
        
        content.add(gameControls, BorderLayout.NORTH);
        content.add(canvas, BorderLayout.CENTER);
        content.add(netControls, BorderLayout.SOUTH);
        add(content, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }
    
    private void connect() {
        if(filename == null)
            selectBoard();
        
        if(listener != null)
            listener.stop();
        
        connect(filename, fieldHost.getText(), Integer.parseInt(fieldPort.getText()));
    }
    
    /**
     * Sends appropriate message to network to pause gameplay
     */
    private void pause() {
        synchronized(out) {
            out.println(NetworkProtocol.MESSAGE_PAUSE);
        }
    }
    
    /**
     * Sends appropriate message to network to continue gameplay
     */
    private void play() {
        synchronized(out) {
            out.println(NetworkProtocol.MESSAGE_PLAY);
        }
    }
    
    
    /**
     * Sends appropriate message to network to restart gameplay
     */
    private void restart() {
        synchronized(out) {
            out.println(NetworkProtocol.MESSAGE_RESTART);
        }
    }
    
    private void changeBoard() {
        canvas.clear();
        selectBoard();
        connect();
    }
    
    private void selectBoard() {
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Board File", "pb");

        final JFileChooser fc = new JFileChooser(BOARDS_DIRECTORY);
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(PingballGUI.this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            filename = fc.getSelectedFile().getPath();
        }
    }
    
    private void selectColors() {
        
    }
    
    private void connect(String filename, String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);

            String content = BoardFactory.readFile(filename, StandardCharsets.UTF_8);
            
            synchronized(out) {
                out.println(content);
                out.println("STOP");
            }
            
            final Board board = BoardFactory.parse(content);
            setBoardName(board.getName());
            
            listener = new NetworkListener(socket);
            Thread networkingThread = new Thread(listener);
            networkingThread.start();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Could not find host.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not connect to host.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private class NetworkListener implements Runnable {
        private final Socket socket;
        private final BufferedInputStream in;
        
        private boolean listening;
        
        public NetworkListener(Socket socket) throws IOException {
            this.socket = socket;
            in = new BufferedInputStream(socket.getInputStream());
            
            listening = true;
        }
        
        public void run() {

            
            while(listening) {        
                System.out.println("listening");

                try {
                    while(true) {
                        byte[] scan = new byte[NetworkProtocol.PREAMBLE.length];
                        while(true) { // look for preamble
                            // make space in buffer
                            for(int i=0; i < scan.length - 1; i++)
                                scan[i] = scan[i+1];
                            
                            // read in from network
                            in.read(scan, scan.length - 1, 1);
                            
                            // stop searching if it's the preamble
                            if(Arrays.equals(scan, NetworkProtocol.PREAMBLE))
                                break;
                        }
                        
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
                try {
                    canvas.update(spriteID, instanceID, new Field(fieldID, value));
                } catch(RuntimeException e) {
                    System.out.println("sprite=" + spriteID);
                    System.out.println("instance=" + instanceID);
                    System.out.println("fieldID=" + fieldID);
                    System.out.println("value=" + value);
                    e.printStackTrace();
                }
                    
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
    
    private void setBoardName(String boardName) {
        setTitle("Pingball: " + boardName);
    }

    public static void main(String[] args) {
        String host = null;
        int port = DEFAULT_PORT;
        boolean portSpecified = false;
        String filename = null;
        
        Queue<String> arguments = new LinkedList<String>(Arrays.asList(args));
        
        try {
            while(!arguments.isEmpty()) {
                String flag = arguments.remove();
                
                try {
                    if (flag.equals("--host")) {
                        host = arguments.remove();
                    } else if (flag.equals("--port")) {
                        port = Integer.parseInt(arguments.remove());
                        portSpecified = true;
                        
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
        
        Map<String, String> argMap = new HashMap<String, String>();
        
        if(filename != null)
            argMap.put(ARG_FILENAME, filename);
        if(host != null)
            argMap.put(ARG_HOST, host);
        if(portSpecified)
            argMap.put(ARG_PORT, ""+port);
        
        new PingballGUI(argMap);
    }
}
