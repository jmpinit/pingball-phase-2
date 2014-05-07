package client;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class PingballGUI extends JFrame {
    public PingballGUI() {
        setTitle("Pingball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        Canvas canvas = new Canvas(400, 400);
        add(canvas, BorderLayout.CENTER);
        
        pack();
        setVisible(true);
    }
    
    public void handleEvent(String fromServer) {
        // TODO use event to change board
    }
}
