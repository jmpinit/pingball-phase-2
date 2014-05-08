package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;

public class PingballGUI extends JFrame {
    private Canvas canvas;
    private Color[] lookup;
    
    public PingballGUI() {
        setTitle("Pingball");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        canvas = new Canvas(400, 400);
        add(canvas, BorderLayout.CENTER);
        
        lookup = new Color[127];
        Random generator = new Random(1336);
        for(int i=0; i < lookup.length; i++)
            lookup[i] = new Color(127+generator.nextInt(128), 127+generator.nextInt(128), 127+generator.nextInt(128));
        
        pack();
        setVisible(true);
    }
    
    public void parseEvent(String board) {
        String[] lines = board.split("\n");
        
        for(int y=0; y < lines.length && y < 20; y++) {
            String line = lines[y];
            for(int x=0; x < line.length() && x < 20; x++) {
                canvas.set(x, y, lookup[line.charAt(x)]);
            }
        }
        
        canvas.repaint();
        System.out.println("repainted");
    }
}
