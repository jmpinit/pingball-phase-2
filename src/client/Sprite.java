package client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState.Field;

public abstract class Sprite {
    protected String name;
    
    public abstract void render(Graphics2D g2);
    public abstract void set(Field f);
    
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
    
    private static Map<Integer, Class<? extends Sprite>> sprites = null;
    
    public static Sprite make(int uid) {
        if(sprites == null) {
            sprites = new HashMap<>();
            sprites.put(Absorber.ID, Absorber.class);
            sprites.put(Ball.ID, Ball.class);
            sprites.put(CircularBumper.ID, CircularBumper.class);
            sprites.put(Flipper.ID, Flipper.class);
            sprites.put(Portal.ID, Portal.class);
            sprites.put(SquareBumper.ID, SquareBumper.class);
            sprites.put(TriangularBumper.ID, TriangularBumper.class);
            sprites.put(Wall.ID, Wall.class);
        }
        
        if(sprites.containsKey(uid)) {
            try {
                return sprites.get(uid).newInstance();
            } catch(Exception e) {
                throw new RuntimeException("Couldn't make Sprite from ID.");
            }
        } else {
            return null;
        }
    }
    
    @SuppressWarnings("unchecked") // certain the returned array contains classes extending Sprite
    public static Class<? extends Sprite>[] getSprites() {
        return new Class[] { Ball.class, Absorber.class, CircularBumper.class,
                Flipper.class, Portal.class, SquareBumper.class, TriangularBumper.class, Wall.class };
    }
    
    /*
     * Game Sprites
     */
    
    public static class Ball extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        public final static long FIXED_POINT = Long.MAX_VALUE / 32;
        private final static Color color = getUniqueColor(127);
        private final static double RADIUS = 0.5;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS*2, RADIUS*2);
        
        private double x, y;
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x - RADIUS, y - RADIUS);
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = field.getValue() / ((double)FIXED_POINT);
                    break;
                case Y:
                    y = field.getValue() / ((double)FIXED_POINT);
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }

    public static class Absorber extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        private final static Color color = Color.RED;
        
        private int width, height;
        private int x, y;
        
        public Absorber() {
            width = 1;
            height = 1;
        }
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x, y);
            
            g2.setColor(color);
            g2.fill(new Rectangle(0, 0, width, height)); // TODO more efficient
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case WIDTH:
                    width = (int)field.getValue();
                    break;
                case HEIGHT:
                    height = (int)field.getValue();
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }
    
    public static class CircularBumper extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static double RADIUS = 0.5;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS*2, RADIUS*2);
        
        private int x, y;
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x, y);
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }
    
    public static class Flipper extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static double LENGTH = 2.0;
        private final static Shape shape = new Line2D.Double(0, 0, 0, LENGTH);
        private final static Stroke stroke = new BasicStroke(0.1f);
        
        private int x, y;
        private double angle;

        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x, y);
            g2.rotate(-angle);
            
            g2.setColor(color);
            Stroke oldStroke = g2.getStroke();
            g2.setStroke(stroke);
            g2.draw(shape);
            g2.setStroke(oldStroke);
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case ANGLE:
                    angle = field.getValue();
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }
    
    public static class Portal extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        private final static Color outerColor = getUniqueColor(127);
        private final static Color innerColor = getUniqueColor(127);
        private final static double RADIUS = 0.5;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS*2, RADIUS*2);
        private final static Shape inner = new Ellipse2D.Double(0, 0, RADIUS, RADIUS);
        
        private int x, y;
        
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x, y);
            
            g2.setColor(outerColor);
            g2.fill(shape);
            
            g2.translate(RADIUS/2, RADIUS/2);
            g2.setColor(innerColor);
            g2.fill(inner);
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }
    
    public static class SquareBumper extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 1;
        private final static Shape shape = new Rectangle(0, 0, SIZE, SIZE);
        
        private int x, y;
        
        @Override
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x, y);
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }
    
    public static class TriangularBumper extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 1;
        private final static Polygon SHAPE = new Polygon(
                new int[] { 0, SIZE, 0 },
                new int[] { 0, 0, SIZE },
        3);
        
        private int x, y;
        private int angle;
        
        @Override
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x, y);
            g2.rotate(-angle);
            g2.setColor(color);
            g2.fill(SHAPE);
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case ANGLE:
                    angle = (int)field.getValue();
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }
    
    public static class Wall extends Sprite {
        public final static int ID = NetworkProtocol.getUID();
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 1;
        private final static Shape shape = new Rectangle(0, 0, SIZE, SIZE);
        
        private int x, y;
        
        @Override
        public void render(Graphics2D g2) {
            AffineTransform saved = g2.getTransform();
            
            g2.translate(x, y);
            
            g2.setColor(color);
            g2.fill(shape);
            
            g2.setTransform(saved);
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                default:
                    throw new RuntimeException("No such field on Sprite.");
            }
        }
    }
}
