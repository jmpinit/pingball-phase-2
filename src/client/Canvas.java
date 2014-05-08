package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JPanel;

public class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    
    private int blockWidth, blockHeight;
    private Color[][] boardImage;
    
    Color backgroundColor = Color.white;
    
    /**
     * Make a smile.
     * @param width width in pixels
     * @param height height in pixels
     */
    public Canvas(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        
        blockWidth = width/PingballGUI.BOARD_WIDTH;
        blockHeight = height/PingballGUI.BOARD_HEIGHT;
        
        boardImage = new Color[width][height];
        for(Color[] col: boardImage)
            Arrays.fill(col, Color.WHITE);
        
        setFocusable(true);
        requestFocusInWindow();
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                changeColor(Color.yellow);
            }

            public void keyReleased(KeyEvent e) {
                changeColor(Color.white);
            }
        });

    }
    
    /*
     * Make the drawing buffer entirely white.
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
    
    private void changeColor(final Color color) {
        backgroundColor = color;
        this.repaint();
    }
    
    public void set(int x, int y, Color c) {
        boardImage[x][y] = c;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
        
        for(int y=0; y < PingballGUI.BOARD_HEIGHT; y++) {
            for(int x=0; x < PingballGUI.BOARD_WIDTH; x++) {
                g2.setColor(boardImage[x][y]);
                g2.fill(new Rectangle(x*blockWidth, y*blockHeight, blockWidth, blockHeight));
            }
        }
    }
}