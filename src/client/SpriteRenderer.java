package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.swing.JPanel;

import server.NetworkProtocol.NetworkState.Field;

public class SpriteRenderer extends JPanel {
    private final static long serialVersionUID = 1L;
    private final static Color backgroundColor = Color.white;
    
    private final ConcurrentMap<Integer, Sprite> sprites;
    
    /**
     * Make a smile.
     * @param width width in pixels
     * @param height height in pixels
     */
    public SpriteRenderer(int width, int height) {
        sprites = new ConcurrentHashMap<>();
        this.setPreferredSize(new Dimension(width, height));
        
        setFocusable(true);
        requestFocusInWindow();
        
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                //changeColor(Color.yellow);
            }

            public void keyReleased(KeyEvent e) {
                //changeColor(Color.white);
            }
        });
    }
    
    public boolean has(int instanceID) {
        return sprites.containsKey(instanceID);
    }
    
    public void update(int spriteID, int instanceID, Field field) {
        // create sprite if it doesn't already exist
        if(!has(instanceID)) {
            Sprite sprite = Sprite.make(spriteID);
            
            if(sprite == null) {
                System.out.println("Unknown sprite ID " + spriteID);
            }
            
            sprites.put(instanceID, sprite);
        }
        
        Sprite subject = sprites.get(instanceID);
        subject.set(field);
        this.repaint();
    }
    
    public void clear() {
        sprites.clear();
    }
    
    /*
     * Make the drawing buffer entirely white.
     */
    private void clearWindow(final Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0,  0,  getWidth(), getHeight());
    }
    
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        clearWindow(g2);
        
        g2.scale(getWidth()/20, getHeight()/20);
        for(Sprite sprite: sprites.values()) {
            sprite.render(g2);
        }
    }
}