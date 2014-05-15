package client;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
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

import server.NetworkProtocol.NetworkState.Field;

/**
 * Following class deals with the rendering and drawing for all the gadget types
 * @author meghana
 *
 */
public abstract class Sprite {
    protected String name;
    
    public abstract void render(Graphics2D g2);
    public abstract void set(Field f);
    
    private static Random generator = new Random(1337);
    
    /**
     * Randomly generates and returns a Color. Is constrained by a given bound
     * The bound MUST be between the numbers 0 and 255.
     * @param lower bound for color generator
     * @return random color within given bound
     */
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
    
    /**
     * Takes in a uid that represents the gadget-type that needs to be represented and creates
     * the resulting Sprite that can be rendered.
     * @param uid of gadget type to make
     * @return Sprite instance of the gadget represented by uid
     */
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
            sprites.put(Letter.ID, Letter.class);
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
    
    /*
     * Game Sprites
     */
    
    /**
     * Sprite representation of a ball.
     * @author meghana
     *
     */
    public static class Ball extends Sprite {
        public final static int ID = 0;
        
        public final static long FIXED_POINT = Long.MAX_VALUE / 32;
        private final static Color color = getUniqueColor(127); 
        private final static double RADIUS = 0.5;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS*2, RADIUS*2);
        
        private boolean visible = true;
        private double x, y;
        
        /**
         * Draws and fills the shape of a ball.
         */
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x - RADIUS, y - RADIUS);
                
                g2.setColor(color);
                g2.fill(shape);
                
                g2.setTransform(saved);
            }
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = field.getValue() / ((double)FIXED_POINT);
                    break;
                case Y:
                    y = field.getValue() / ((double)FIXED_POINT);
                    break;
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }

    /**
     * Sprite representation of an absorber
     * @author meghana
     *
     */
    public static class Absorber extends Sprite {
        public final static int ID = 1;
        
        private final static Color color = Color.RED;
        
        private boolean visible = true;
        private int width, height;
        private int x, y; //(x,y) coordinate of top-left point of absorber.
        
        public Absorber() {
            width = 1;
            height = 1;
        }
        
        /**
         * Draws and fills the shape of an absorber.
         */
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                
                g2.setColor(color);
                g2.fill(new Rectangle(0, 0, width, height)); // TODO more efficient
                
                g2.setTransform(saved);
            }
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
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
    
    /**
     * Sprite representation of a circular bumper
     * @author meghana
     *
     */
    public static class CircularBumper extends Sprite {
        public final static int ID = 2;
        
        private final static Color color = getUniqueColor(127);
        private final static double RADIUS = 0.5;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS*2, RADIUS*2);
        
        private boolean visible = true;
        private int x, y;
        
        /**
         * Draws and fills the shape of a circular bumper.
         */
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                
                g2.setColor(color);
                g2.fill(shape);
                
                g2.setTransform(saved);
            }
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
    
    /**
     * Sprite representation of a flipper
     * @author meghana
     *
     */
    public static class Flipper extends Sprite {
        public final static int ID = 3;
        
        private final static Color color = getUniqueColor(127);
        private final static double LENGTH = 2.0;
        private final static Shape shape = new Line2D.Double(0, 0, 0, LENGTH);
        private final static Stroke stroke = new BasicStroke(0.1f);
        
        private boolean visible = true;
        private int x, y;
        private double angle;
        
        /**
         * Draws and the shape of a flipper.
         */
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                g2.rotate(Math.toRadians(-angle));
                
                g2.setColor(color);
                Stroke oldStroke = g2.getStroke();
                g2.setStroke(stroke);
                g2.draw(shape);
                g2.setStroke(oldStroke);
                
                g2.setTransform(saved);
            }
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
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
    
    /**
     * Sprite representation of a portal
     * @author meghana
     *
     */
    public static class Portal extends Sprite {
        public final static int ID = 4;
        
        private final static Color outerColor = getUniqueColor(127);
        private final static Color innerColor = getUniqueColor(127);
        private final static double RADIUS = 0.5;
        private final static Shape shape = new Ellipse2D.Double(0, 0, RADIUS*2, RADIUS*2);
        private final static Shape inner = new Ellipse2D.Double(0, 0, RADIUS, RADIUS);
        
        private boolean visible = true;
        private int x, y;
        
        /**
         * Draws and fills the shape of a portal. Creates two concentric circles of color.
         */
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                
                g2.setColor(outerColor);
                g2.fill(shape);
                
                g2.translate(RADIUS/2, RADIUS/2);
                g2.setColor(innerColor);
                g2.fill(inner);
                
                g2.setTransform(saved);
            }
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
    
    /**
     * Sprite representation of a square bumper
     * @author meghana
     *
     */
    public static class SquareBumper extends Sprite {
        public final static int ID = 5;
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 1;
        private final static Shape shape = new Rectangle(0, 0, SIZE, SIZE);
        
        private boolean visible = true;
        private int x, y;
        
        /**
         * Draws and fills the shape of a square bumper.
         */
        @Override
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                
                g2.setColor(color);
                g2.fill(shape);
                
                g2.setTransform(saved);
            }
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
    
    /**
     * Sprite representation of a triangular bumper
     * @author meghana
     *
     */
    public static class TriangularBumper extends Sprite {
        public final static int ID = 6;
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 1;
        private final static Polygon SHAPE = new Polygon(
                new int[] { 0, SIZE, 0 },
                new int[] { 0, 0, SIZE },
        3);
        
        private boolean visible = true;
        private int x, y;
        private int angle;
        
        /**
         * Draws and fills the shape of a triangular bumper.
         */
        @Override
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                g2.rotate(Math.toRadians(-angle));
                g2.setColor(color);
                g2.fill(SHAPE);
                
                g2.setTransform(saved);
            }
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
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
    
    /**
     * Sprite representation of outer walls.
     * @author meghana
     *
     */
    public static class Wall extends Sprite {
        public final static int ID = 7;
        
        private final static Color color = getUniqueColor(127);
        private final static int SIZE = 1;
        private final static Shape shape = new Rectangle(0, 0, SIZE, SIZE);
        
        private boolean visible = true;
        private int x, y;
        
        /**
         * Draws and fills the shape of the otuer walls.
         */
        @Override
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                
                g2.setColor(color);
                g2.fill(shape);
                
                g2.setTransform(saved);
            }
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
    
    /**
     * Sprite representation of a single letter. 
     * Used to display connected board's name in the outer walls.
     * @author meghana
     *
     */
    public static class Letter extends Sprite {
        public final static int ID = 8;
        
        private final static Color color = Color.BLACK;
        private final static Font font = new Font("Monospaced", Font.PLAIN, 1);
        
        private boolean visible = true;
        private int x, y;
        private char c = 'x';
        
        /**
         * Draws and displays letters on a board. Used for when boards are connected to draw outer walls.
         */
        @Override
        public void render(Graphics2D g2) {
            if(visible) {
                AffineTransform saved = g2.getTransform();
                
                g2.translate(x, y);
                
                g2.setColor(color);
                g2.setFont(font);
                g2.drawString("" + c, x, y);
                
                g2.setTransform(saved);
            }
        }
        
        public void set(Field field) {
            switch(field.getFieldName()) {
                case X:
                    x = (int)field.getValue();
                    break;
                case Y:
                    y = (int)field.getValue();
                    break;
                case CHARACTER:
                    c = (char)field.getValue();
                    break;
                case VISIBLE:
                    visible = field.getValue() == 1? true : false;
                    break;
                default:
                    throw new RuntimeException(field.getFieldName().toString() + " not a field on Sprite.");
            }
        }
    }
}
