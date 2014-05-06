package game;

import java.util.HashSet;
import java.util.Set;

import physics.Circle;
import physics.Geometry.VectPair;
import physics.Vect;


/***
 * A ball has
 * a state of being active or not,
 * a diameter of approximately 0.5L,
 * a location (where the location is of the center of the ball),
 * a velocity with a magnitude that is 0 L/sec or between 0.01 L/sec and 200 L/sec,
 * it has no independent 'action' associated with it,
 * and it reflects other balls with a reflection constant.
 * 
 * @author pkalluri
 *
 */
public class Ball implements GamePiece{
    private String name;
    private static final double RADIUS = .25; //radius of ball
    private static final double BOARDSIZE = 20; //size of board is 20L
    private Vect position;
    private Vect velocity;
    private boolean active;  //whether or not this ball is currently active
    
    private static final char SYMBOL = '*';

    public Ball(String name, Vect position, Vect velocity){
        this.name = name;
        this.position = position;
        this.velocity = velocity;
        this.active = true;
    }
    
    /***
     * @return a String name representing name of gadget
     */
    @Override
    public String getName() {
        checkRep();
        return name;
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
        checkRep();
        Vect oldVelocity = velocity;
        
        double speedGain = 1 - mu * amountOfTime - mu2 * velocity.length() * amountOfTime;
        velocity = new Vect (velocity.x() * speedGain, (velocity.y() + gravity * amountOfTime) * speedGain);
        
        Vect averageVelocity = new Vect (   (velocity.x() + oldVelocity.x())/2, (velocity.y() + oldVelocity.y())/2  );
        position = new Vect(position.x() + averageVelocity.x()* amountOfTime, position.y() + averageVelocity.y()* amountOfTime );
        checkRep();
    }


    /** 
     * Progresses the ball by amountOfTime, ignoring gravity and friction.
     * @param amountOfTime amount of time to move ball
     */
    public void progressIgnoringPhysicalConstants(double amountOfTime) {
        setPosition(
                new Vect(
                        position.x() + velocity.x()*amountOfTime,
                        position.y() + velocity.y()*amountOfTime
                        )
        );
    }
    

    /***
     * Does this gadget's action.
     */
    @Override
    public void doAction() {
        checkRep();
        //Do nothing.
    }
    
    /***
     * Calculates time until collision with this ball.
     * @param ball
     * @return a double representing the time, in seconds, to 
     * collision between the ball and the gadget
     */
    @Override
    public double timeTillCollision(Ball otherBall) {
        checkRep();
        Circle thisBallCircle = new Circle(position, RADIUS);
        Circle otherBallCircle = new Circle(otherBall.getPosition(), otherBall.getRadius());
        double time = physics.Geometry.timeUntilBallBallCollision(
                thisBallCircle,
                this.velocity,
                otherBallCircle,
                otherBall.getVelocity());
        checkRep();
        return time;
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
    public void progressAndCollide(double amountOfTime, Ball otherBall) {
        checkRep();
        //progress given amount of time
        this.progressIgnoringPhysicalConstants(amountOfTime);
        otherBall.progressIgnoringPhysicalConstants(amountOfTime);
        
        //collide
        VectPair newVelocities = physics.Geometry.reflectBalls(
                this.position,
                1.0, //weight to give this ball's mass, with respect to other ball's mass
                this.velocity,
                otherBall.getPosition(),
                1.0, //weight to give this ball's mass, with respect to other ball's mass
                otherBall.getVelocity());
        
        otherBall.setVelocity(newVelocities.v2);
        this.setVelocity(newVelocities.v1);      
        checkRep();
    }


    /***
     * Checks if ball is active.
     * @return True iff ball is active.
     */
    public boolean isActive() {
        checkRep();
        return active;
    }

    /***
     * Sets ball's activity state to given activity state.
     * @param active true iff ball's activity state should be set to true
     * 
     */
    public void setActive(boolean active) {
        checkRep();
        this.active = active;
    }


    /***
     * Get current origin at center of ball
     * @return current position
     */
    @Override
    public Vect getPosition() {
        checkRep();
        return position;
    }

    /***
     * Returns current velocity of ball.
     * @return current velocity of ball
     */
    public Vect getVelocity() {
        checkRep();
        return velocity;
    }

    /***
     * Return ball's radius.
     * @return ball's radius.
     */
    public double getRadius() {
        checkRep();
        return RADIUS;
    }

    /**
     * @return char symbol that represents this gadget on the board
     */
    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }

    /***
     * Sets velocity to given velocity
     * @param velocity
     */
    public void setVelocity(Vect velocity) {
        checkRep();
        this.velocity = velocity;
    }
    
    /***
     * Sets position to given position
     * @param position
     */
    public void setPosition(Vect position) {
        checkRep();
        this.position = position; 
    }

    /***
     * Get set of tiles that this gadgets occupies
     * @return Set<Vect>
     */
    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> tiles = new HashSet<Vect>();
        
        tiles.add(new Vect( Math.floor(position.x()), Math.floor(position.y())   )  );
        
        return tiles;
    }
    
    /**
     * Check rep invariant.
     */
    private void checkRep(){
        assert (this.position.x() >= 0 && this.position.x() <= BOARDSIZE);
        assert (this.position.y() >= 0 && this.position.y() <= BOARDSIZE);
    }

    
    
    
    
    
    
}
