package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;


/**
 * A square bumper is a 1L x 1L square shaped gadget
 * that is triggered whenever the ball hits it.
 * It has four corners that are represented by
 * circles of radius 0.
 * 
 * Square bumpers have coefficient of reflection of 1.0
 * 
 * @author jzwang
 */
public class SquareBumper implements Gadget {
    //line segments represent the bumper boundaries
    private final String name;
    private final Vect position;
    private final static double BOARDSIZE = 20; //size of board is 20Lx20L
    private final List<LineSegment> boundaries; //based on position and dimensions
    private final List<Circle> corners;

    private static final char SYMBOL = '#';


    public SquareBumper(String name, double x, double y) {
        this.name = name;
        this.position = new Vect(x,y);
        LineSegment top = new LineSegment(x,y,x+1,y);
        LineSegment left = new LineSegment(x,y,x,y+1);
        LineSegment right = new LineSegment(x+1,y,x+1,y+1);
        LineSegment bottom = new LineSegment(x,y+1,x+1,y+1);
        Circle topRight = new Circle(x+1,y,0);
        Circle topLeft = new Circle(x,y,0);
        Circle bottomRight = new Circle(x+1,y+1,0);
        Circle bottomLeft = new Circle(x,y+1,0);
        this.boundaries = new ArrayList<LineSegment>(Arrays.asList(left,top,right,bottom));
        this.corners = new ArrayList<Circle>(Arrays.asList(topLeft,topRight,bottomRight,bottomLeft));
    }

    /**
     * Check the rep invariant.
     */
    private void checkRep() {
        assert (this.position.x() >= 0 && this.position.x() <= BOARDSIZE);
        assert (this.position.y() >= 0 && this.position.y() <= BOARDSIZE);
    }

    /**
     * @return string name representing name of bumper
     */
    @Override
    public String getName() {
        checkRep();
        return this.name;
    }

    /**
     * @return set of tiles taken up by bumper
     */
    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Vect tile = position;
        Set<Vect> set = new HashSet<Vect>();
        set.add(tile);
        return set;
    }

    /**
     * @return char symbol that represents this gadget on a board string
     */
    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }

    /**
     * @return Vect position of gadget on board
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
        double minTime=Double.POSITIVE_INFINITY;
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        for (LineSegment line : boundaries) {
            double collisionTime = Geometry.timeUntilWallCollision(line, ballShape, ball.getVelocity());
            if (collisionTime < minTime) {
                minTime = collisionTime;
            }
        }
        for (Circle circle : corners) {
            double cornerCollisionTime = Geometry.timeUntilCircleCollision(circle, ballShape, ball.getVelocity());
            if (cornerCollisionTime < minTime) {
                minTime = cornerCollisionTime;
            }
        }
        checkRep();
        return minTime;
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
        checkRep();
        ball.progressIgnoringPhysicalConstants(amountOfTime);

        //find closest wall
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        LineSegment closestWall = boundaries.get(0);
        Circle closestCorner = corners.get(0);
        double minTime=Double.POSITIVE_INFINITY;
        double minCornerTime=Double.POSITIVE_INFINITY;

        for (LineSegment line : boundaries) {
            double collisionTime = Geometry.timeUntilWallCollision(line, ballShape, ball.getVelocity());
            if (collisionTime < minTime) {
                minTime = collisionTime;
                closestWall = line;
            }
        }
        for (Circle circle : corners) {
            double cornerCollisionTime = Geometry.timeUntilCircleCollision(circle, ballShape, ball.getVelocity());
            if (cornerCollisionTime < minCornerTime) {
                minCornerTime = cornerCollisionTime;
                closestCorner = circle;
            }
        }

        Vect velocity;
        if (minTime < minCornerTime) {
            velocity = physics.Geometry.reflectWall(closestWall,ball.getVelocity());
        }
        else {
            velocity = physics.Geometry.reflectCircle(closestCorner.getCenter(), ball.getPosition(), ball.getVelocity());
        }

        ball.setVelocity(velocity);
        checkRep();
    }

    /**
     * Square bumpers have no action.
     */
    @Override
    public void doAction() {
        checkRep();
        //do nothing
    }

    /***
     * Progresses this gadget by the given amountOfTime (in seconds),
     * assuming the given physical constants. Square bumpers have no progress.
     * 
     * @param amountOfTime amount of time to progress
     * @param gravity constant value of gravity
     * @param mu constant value of the friction with respect to time
     * @param mu2 constant value of the friction with respect to distance
     */
    @Override
    public void progress(double amountOfTime, double gravity, double mu,
            double mu2) {
        checkRep();
        //do nothing
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((corners == null) ? 0 : corners.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
            + ((position == null) ? 0 : position.hashCode());
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
        SquareBumper other = (SquareBumper) obj;
        if (corners == null) {
            if (other.corners != null)
                return false;
        } else if (!corners.equals(other.corners))
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
        return true;
    }




}
