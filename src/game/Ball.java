package game;

import java.util.HashSet;
import java.util.Set;

import client.Sprite;
import physics.Circle;
import physics.Geometry.VectPair;
import physics.Vect;
import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;


/***
 * A Ball is an active or inactive circular pingball GamePiece,
 * which reflects other balls,
 * and has a constant default radius and a changing location and velocity.
 * 
 * @author pkalluri
 *
 */
public class Ball implements GamePiece {
    public static final int STATICUID = Sprite.Ball.ID;
    private final int instanceUID;
    private String name;
    public static final double RADIUS = .25; //radius of ball
    private Vect position; //position of center of ball
    private Vect velocity;
    private boolean active;  //whether or not this ball is currently active
    
    private static final char SYMBOL = '*';

    public Ball(String name, Vect position, Vect velocity) {
        this.name = name;
        this.position = position;
        this.velocity = velocity;
        this.active = true;
        this.instanceUID = NetworkProtocol.getUID();

    }

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
     * Progresses the ball by given amount of time, ignoring any physical constants/forces.
     * @param amountOfTime amount of time to progress ball, in seconds
     */
    public void progressIgnoringPhysicalConstants(double amountOfTime) {
        setPosition(
                new Vect(
                        position.x() + velocity.x()*amountOfTime,
                        position.y() + velocity.y()*amountOfTime
                        )
        );
    }
   

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
        
        this.setVelocity(newVelocities.v1);    
        otherBall.setVelocity(newVelocities.v2);
        checkRep();
    }
    
/******** ACCESSOR METHODS ********/

    @Override
    public String getName() {
        checkRep();
        return name;
    }
    
    @Override
    public int getInstanceUID() {
        return instanceUID;
    }
    
    @Override
    public double getTimeTillCollision(Ball otherBall) {
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

    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)(position.x()*Sprite.Ball.FIXED_POINT)), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)(position.y()*Sprite.Ball.FIXED_POINT))
        };
        
        return new NetworkState(fields);
    }

    /***
     * Return ball's radius.
     * @return ball's radius.
     */
    public double getRadius() {
        checkRep();
        return RADIUS;
    }
    
    /***
     * Get current origin at center of ball
     * @return current position
     */
    public Vect getPosition() {
        checkRep();
        return position;
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
     * Returns current velocity of ball.
     * @return current velocity of ball
     */
    public Vect getVelocity() {
        checkRep();
        return velocity;
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
    

    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }
    

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
    private void checkRep() {
        assert (this.position.x() >= 0 && this.position.x() <= Board.SIDELENGTH);
        assert (this.position.y() >= 0 && this.position.y() <= Board.SIDELENGTH);
    }

    @Override
    public int getStaticUID() {
        return STATICUID;
    }


}
