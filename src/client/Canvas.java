package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Canvas extends JPanel {
    private static final long serialVersionUID = 1L;
    
    Color backgroundColor = Color.white;
    
    /**
     * Make a smile.
     * @param width width in pixels
     * @param height height in pixels
     */
    public Canvas(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        
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
    
    private void changeColor(final Color color) {
        backgroundColor = color;
        this.repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        fillWindow(g2);
    }
    
    /*
     * Make the drawing buffer entirely white.
     */
    private void fillWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
}