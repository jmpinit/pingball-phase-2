package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import client.Sprite;
import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * An Absorber is a rectangular Gadget that simulates the ball-return mechanism in a pingball game. 
 * When a ball hits an absorber, the absorber stops the ball and holds it in the bottom right-hand corner of the absorber.
 * An absorber's action is to release 1 ball, if it has 1, straight upwards at a default velocity.
 *
 */
public class Absorber implements Gadget, NetworkProtocol.NetworkSerializable {
    private static final int STATICUID = Sprite.Absorber.ID;
    private final int instanceUID;
    private final String name;
    private final Vect boundingBoxPosition;
    private final int width;
    private final int height;
    private final Queue<Ball> storedBalls;
    private final Vect storePosition;

    private final List<LineSegment> boundaries; //based on position and dimensions
    
    private final static double STOREDBALLVELOCITY = -50; //stored ball velocity is 50L/s upwards when shot out

    private static final char SYMBOL = '=';


    /**
     * Constructs a new absorber with given parameters
     * @param name name of particular gadget
     * @param x x-coordinate of top left corner or absorber
     * @param y y-coordinate of top left corner of absorber
     * @param width dimension of gadget - how far right it extends
     * @param height dimension of gadget - how far down it extends
     */
    public Absorber(String name, int x, int y, int width, int height) {
        this.name = name;
        this.boundingBoxPosition = new Vect(x,y);
        this.width = width;
        this.height = height;
        this.storedBalls = new  LinkedList<Ball>(); //initially has no stored ball
        this.storePosition = new Vect(boundingBoxPosition.x()+this.width-.25,boundingBoxPosition.y()+this.height-.25);

        //construct bounding lines
        LineSegment top = new LineSegment(x,y,x+width,y);
        LineSegment left = new LineSegment(x,y,x,y+height);
        LineSegment right = new LineSegment(x+width,y,x+width,y+height);
        LineSegment bottom = new LineSegment(x,y+height,x+width,y+height);
        this.boundaries = new ArrayList<LineSegment>(Arrays.asList(left,right,top,bottom));
        
        instanceUID = NetworkProtocol.getUID();
    }
    
    


    @Override
    public String getName() {
        checkRep();
        return this.name;
    }


    @Override
    public double getTimeTillCollision(Ball ball) {
        checkRep();
        if (this.contains(ball)) {
            return Double.POSITIVE_INFINITY;
        } else {
            double min = Double.POSITIVE_INFINITY;
            final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
            for (LineSegment line : boundaries) {
                double time = physics.Geometry.timeUntilWallCollision(line,ballShape,ball.getVelocity());
                if (time < min) {
                    min = time;
                }
            }
            if (min == 0 ) {
                //colliison just occurred!
                return Double.POSITIVE_INFINITY;
            }
            else {
                return min;
            }
        } 

    }


    /***
     * States true iff ball is inside this absorber.
     * @param ball
     * @return
     */
    private boolean contains(Ball ball) {
        checkRep();
        boolean contains =
            ball.getPosition().x() > this.boundingBoxPosition.x()
            && ball.getPosition().x() < this.boundingBoxPosition.x() + this.width
            && ball.getPosition().y() > this.boundingBoxPosition.y() 
            && ball.getPosition().y() < this.boundingBoxPosition.y() + this.height;
        return contains;
    }


    @Override
    public void progress(double amountOfTime, double gravity, double mu, double mu2) {
        checkRep();
        //Do nothing.
    }


    @Override
    public void progressAndCollide(double amountOfTime, Ball ball) {
        //Jump forward to collision (progressing ball to position of collision is unnecessary)

        //Collide
        checkRep();
        ball.setPosition(storePosition); //store ball
        ball.setActive(false);
        ball.setVelocity(new Vect(0,0));
        storedBalls.add(ball);
        checkRep();
    }

    /**
     * Activates and releases one stored ball, if one exists.
     */
    @Override
    public void doAction() {
        checkRep();
        if (!storedBalls.isEmpty()) {
            Ball ballToShootOut = storedBalls.remove();
            ballToShootOut.setVelocity(new Vect(0,STOREDBALLVELOCITY));
            ballToShootOut.setActive(true);
        }
        checkRep();
    }

    /**
     * Checks the rep invariant.
     * Rep invariant - absorber is within board and is not larger than board.
     */
    private void checkRep() {
        assert (width <= Board.SIDELENGTH);
        assert (height <= Board.SIDELENGTH);
        assert (this.boundingBoxPosition.x() >= 0 && this.boundingBoxPosition.x() <= Board.SIDELENGTH);
        assert (this.boundingBoxPosition.y() >= 0 && this.boundingBoxPosition.y() <= Board.SIDELENGTH);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
            + ((boundingBoxPosition == null) ? 0 : boundingBoxPosition.hashCode());
        result = prime * result + width;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Absorber other = (Absorber) obj;
        if (height != other.height)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (boundingBoxPosition == null) {
            if (other.boundingBoxPosition != null)
                return false;
        } else if (!boundingBoxPosition.equals(other.boundingBoxPosition))
            return false;
        if (width != other.width)
            return false;
        return true;
    }
    
    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)boundingBoxPosition.x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)boundingBoxPosition.y()),
                new Field(FieldName.WIDTH, width),
                new Field(FieldName.HEIGHT, height)
        };
        
        return new NetworkState(fields);
    }

    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> set = new HashSet<Vect>();
        for (int i=0; i<width; i++) {
            for (int j=0; j<height;j++) {
                Vect tile = new Vect(this.boundingBoxPosition.x()+i,this.boundingBoxPosition.y()+j);
                set.add(tile);
            }
        }
        checkRep();
        return set;
    }


    @Override
    public char getSymbol() {
        return SYMBOL;
    }



    @Override
    public int getInstanceUID() {
        return instanceUID;
    }




    @Override
    public int getStaticUID() {
        return STATICUID;
    }
    
    private boolean sent = false;
    
    @Override
    public boolean hasBeenSent() {
        return sent;
    }
    
    @Override
    public void hasBeenSent(boolean v) {
        sent = v;
    }
}
