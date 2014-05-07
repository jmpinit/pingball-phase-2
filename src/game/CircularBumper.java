package game;

import java.util.HashSet;
import java.util.Set;

import physics.Circle;
import physics.Geometry;
import physics.Vect;


/**
 * A circular gadget of diameter 1L
 * that is triggered whenever the ball hits it.
 * 
 * Circle bumpers have a coefficient of reflection of 1.0
 * 
 * @author: jzwang
 */
public class CircularBumper implements Gadget {
    private final String name;
    private final Vect position;

    private final static double BOARDSIZE = 20; //size of board is 20Lx20L
    private final Circle boundary; //based on position and dimensions
    private final static double RADIUS = 0.5; //radius of circlebumper is .5L
    private static final char SYMBOL = '0';


    /**
     * Creates circle bumpers on the board from file information
     * @param file board file
     */
    public CircularBumper(String name, double x, double y) {
        this.name = name;
        this.position = new Vect(x,y);
        this.boundary = new Circle(x+RADIUS,y+RADIUS,RADIUS);

    }

    /**
     * @return string name representing this gadget
     */
    @Override
    public String getName() {
        checkRep();
        return this.name;
    }


    /**
     * @return set of tiles this gadget takes up on the board
     */
    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> tiles = new HashSet<Vect>();

        Vect tile = position;
        tiles.add(tile);

        return tiles;
    }

    /**
     * 
     * @return Vect representing center of circular bumper
     */
    private Vect getCenter() {
        checkRep();
        return new Vect(position.x()+RADIUS,position.y()+RADIUS);
    }

    /**
     * @return char symbol that represents this gadget on the board string
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
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        return Geometry.timeUntilCircleCollision(boundary, ballShape, ball.getVelocity());
    }

    /**
     * Simulates a collision with a circular bumper by updating the velocity of the
     * ball by the coefficient of reflection, 1.0.
     * 
     * @param ball ball on board
     * 
     */
    @Override
    public void progressAndCollide(double amountOfTime, Ball ball) {
        checkRep();
        ball.progressIgnoringPhysicalConstants(amountOfTime);
        Vect velocity = physics.Geometry.reflectCircle(this.getCenter(), ball.getPosition(), ball.getVelocity());
        ball.setVelocity(velocity);
        checkRep();
    }

    /**
     * Circle bumpers have no action.
     */
    @Override
    public void doAction() {
        //do nothing
        checkRep();
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
        //do nothing
        checkRep();
    }

    /**
     * Check the rep invariant.
     */
    private void checkRep() {
        assert (this.position.x() >= 0 && this.position.x() <= BOARDSIZE);
        assert (this.position.y() >= 0 && this.position.y() <= BOARDSIZE);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        CircularBumper other = (CircularBumper) obj;
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
