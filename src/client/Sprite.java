package client;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Random;

import physics.Vect;
import server.NetworkProtocol;

public abstract class Sprite {
    protected String name;
    
    public abstract int getID();
    public abstract void render(Graphics2D g2);
    
    private static Random generator = new Random(1337);
    
    public static Color getUniqueColor(int lower) {
        if(lower < 0 || lower >= 255)
            throw new RuntimeException("Lower bound must be between 0 and 255");
        
        int rest = 255-lower;
        
        return new Color(
                lower + generator.nextInt(rest),  // red
                lower + generator.nextInt(rest),  // green
                lower + generator.nextInt(rest)   // blue
        );
    }
    
    /*
     * Game Sprites
     */
    
    static class Ball extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static double RADIUS = 8.0;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS, RADIUS);
        
        public Vect position;
        public Vect velocity;
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }
        
        public int getID() { return id; }
    }

    static class Absorber extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color color = Color.RED;
        
        public int width;
        public int height;
        public Vect position;
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            
            g2.setColor(color);
            g2.fill(new Rectangle(0, 0, 0, 0)); // TODO more efficient
            
            g2.setTransform(saved);
        }

        public int getID() { return id; }
    }
    
    static class CircularBumper extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static double RADIUS = 8.0;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS, RADIUS);
        
        public Vect position;
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }

        public int getID() { return id; }
    }
    
    static class Flipper extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static double LENGTH = 8.0;
        private final static Shape shape = new Line2D.Double(0, 0, 0, LENGTH);
        
        public Vect position;
        public double angle;

        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            g2.rotate(-angle);
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }
        
        public int getID() { return id; }
    }
    
    static class Portal extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color fillColor = getUniqueColor(127);
        private final static Color strokeColor = getUniqueColor(127);
        private final static int RADIUS = 8;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS, RADIUS);
        
        public Vect position;
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            
            g2.setColor(fillColor);
            g2.fill(shape);
            g2.setColor(strokeColor);
            g2.draw(shape);
            
            g2.setTransform(saved);
        }

        public int getID() { return id; }
    }
    
    static class SquareBumper extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 8;
        private final static Shape shape = new Rectangle(0, 0, SIZE, SIZE);
        
        public Vect position;
        
        @Override
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }

        public int getID() { return id; }
    }
    
    static class TriangularBumper extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 8;
        private final static Polygon SHAPE = new Polygon(
                new int[] { 0, SIZE, 0 },
                new int[] { 0, 0, SIZE },
        3);
        
        public Vect position;
        public int angle;
        
        @Override
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            g2.rotate(-angle);
            g2.setColor(color);
            g2.fill(SHAPE);
            
            g2.setTransform(saved);
        }

        public int getID() { return id; }
    }
    
    static class Wall extends Sprite {
        private static int id = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 8;
        private final static Shape shape = new Rectangle(0, 0, SIZE, SIZE);
        
        public Vect position;
        
        @Override
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(position.x(), position.y());
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }

        public int getID() { return id; }
    }
}
