package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * A rectangle kL x mL where k and m are positive integers <= 20.
 * 
 * Trigger: generated whenever the ball hits it
 * Action: shoots out a stored ball 
 * Coefficient of reflection: not applicable; the ball is captured
 * An absorber simulates the ball-return mechanism in a pingball game. 
 * When a ball hits an absorber, the absorber stops the ball and holds it
 * in the bottom right-hand corner of the absorber. 
 * 
 * The ball's center is .25L from the bottom of the absorber and .25L from the right side of the absorber.
 * 
 * If the absorber is holding a ball, then the action of an absorber, 
 * when it is triggered, is to shoot the ball straight upwards in the 
 * direction of the top of the playing area with initial 
 * velocity 50L/sec. If the absorber is not holding the ball, 
 * or if the previously ejected ball has not yet left the absorber, then 
 * the absorber takes no action when it receives a trigger signal.
 * 
 * When the ball hits a self-triggering absorber, 
 * it should be moved to the bottom right-hand corner of the absorber, 
 * and then be shot upward as described above. 
 * 
 * @author jzwang
 *
 */
public class Absorber implements Gadget {
    private final String name;
    private final int width;
    private final int height;
    private final Vect position;
    private Queue<Ball> storedBalls;

    private final static double STOREDBALLVELOCITY = -50; //stored ball velocity is 50L/s upwards when shot out

    private final static double BOARDSIZE = 20; //size of board is 20Lx20L
    private final List<LineSegment> boundaries; //based on position and dimensions

    private static final char SYMBOL = '=';


    public Absorber(String name, int x, int y, int width, int height) {
        this.name = name;
        this.position = new Vect(x,y);
        this.width = width;
        this.height = height;
        LineSegment top = new LineSegment(x,y,x+width,y);
        LineSegment left = new LineSegment(x,y,x,y+height);
        LineSegment right = new LineSegment(x+width,y,x+width,y+height);
        LineSegment bottom = new LineSegment(x,y+height,x+width,y+height);
        this.boundaries = new ArrayList<LineSegment>(Arrays.asList(left,right,top,bottom));
        this.storedBalls = new  LinkedList<Ball>(); //initially has no stored ball
    }

    /***
     * @return a String name representing name of gadget
     */
    @Override
    public String getName() {
        checkRep();
        return this.name;
    }

    /***
     * Get set of tiles that this gadgets occupies
     * @return Set<Vect>
     */
    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> set = new HashSet<Vect>();
        for (int i=0; i<width; i++) {
            for (int j=0; j<height;j++) {
                Vect tile = new Vect(this.position.x()+i,this.position.y()+j);
                set.add(tile);
            }
        }
        checkRep();
        return set;
    }

    /***
     * Returns a char symbol to represent this gadget.
     * @return a char symbol to represent this gadget.
     */
    @Override
    public char getSymbol() {
        return SYMBOL;
    }

    /***
     * Get current origin
     * @return current position
     */
    @Override
    public Vect getPosition() {
        checkRep();
        return position;
    }



    /***
     * Calculates time until collision with this ball.
     * @param ball
     * @return a double representing the time, in seconds, to 
     * collision between the ball and the gadget
     */
    @Override
    public double timeTillCollision(Ball ball) {
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
        boolean contains =
            ball.getPosition().x() > this.position.x()
            && ball.getPosition().x() < this.position.x() + this.width
            && ball.getPosition().y() > this.position.y() 
            && ball.getPosition().y() < this.position.y() + this.height;
        checkRep();
        return contains;
    }

    /***
     * Progresses this gadget by the given amountOfTime (in seconds),
     * assuming the given physical constants.
     * 
     * @param amountOfTime amount of time to progress
     * @param gravity constant value of gravity
     * @param mu constant value of the friction with respect to time
     * @param mu2 constant value of the friction with respect to distance
     */
    @Override
    public void progress(double amountOfTime, double gravity, double mu, double mu2) {
        //Do nothing.
        checkRep();
    }


    /***
     * Progresses this gadget by the given amountOfTime (in seconds),
     * simplifying to no physical constants/accelerations,
     * and collides given ball with this gadget,
     * by updating ball's velocity and this gadget's velocity accordingly.
     * 
     * @param amountOfTime amount of time to progress
     * @param ball ball to collide with
     */
    @Override
    public void progressAndCollide(double amountOfTime, Ball ball) {
        //Jump forward to collision (progressing to position of collision is unnecessary)

        //Collide
        checkRep();
        //ball is stored .25Lx.25L away from the bottom right corner of absorber
        ball.setPosition(new Vect(position.x()+this.width-.25,position.y()+this.height-.25)); 
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
     * Check the rep invariant.
     */
    private void checkRep() {
        assert (width <= BOARDSIZE);
        assert (height <= BOARDSIZE);
        assert (this.position.x() >= 0 && this.position.x() <= BOARDSIZE);
        assert (this.position.y() >= 0 && this.position.y() <= BOARDSIZE);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
            + ((position == null) ? 0 : position.hashCode());
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
        if (position == null) {
            if (other.position != null)
                return false;
        } else if (!position.equals(other.position))
            return false;
        if (width != other.width)
            return false;
        return true;
    }




}
