package game;

import java.util.Set;

import physics.Vect;
import server.NetworkProtocol.NetworkSerializable;
import server.NetworkProtocol.NetworkState;


/***

 * A game piece is a named component on a board of a pingball game.
 * (i.e. any Ball or Gadget)
 * 
 * @author pkalluri
 */

public interface GamePiece extends NetworkSerializable{    
    /***
     * Get name.
     * @return a String representation of the name of this
     */
    public String getName();
    
    
    /***
     * Get current position
     * @return current position
     */
    public Vect getPosition();
    
    /***
     * Returns state of this GamePiece.
     * @return
     */
    public NetworkState getState();
    
    /***
     * Progresses this gadget by the given amount of time, assuming the given physical constants
     * 
     * @param amountOfTime amount of time to progress, in seconds
     * @param gravity gravitational constant, in TODO: figure out the units here
     * @param mu constant representing friction with respect to time, in 1/second
     * @param mu2 constant representing friction with respect to distance, in 1/L
     */
    public void progress(double amountOfTime, double gravity, double mu, double mu2);
    
    /***
     * Calculates time until collision with given ball, ignoring any physical constants
     * @param ball the ball with which this will collide in the returned amount of time
     * @return the time until collision between this and the ball, in seconds,
     */
    public double timeTillCollision(Ball ball);  

    /***
     * Progresses this gadget by the given amount of time, ignoring any physical constants,
     * and collides this with the given ball by updating ball and gamepiece accordingly.
     * 
     * @param amountOfTime amount of time to progress, in seconds.
     * @param ball ball to collide with
     */
    public void progressAndCollide(double amountOfTime, Ball ball);
    
    //The following methods were used in printing a string rep, and no longer matter
    /***
     * Get set of tiles that this game piece currently occupies
     * @return the set of tiles that this game piece currently occupies
     */
    public Set<Vect> getTiles();
    
    /***
     * Get a char symbol to represent this game piece in its current state
     * @return a char symbol to represent this gadget in its current state
     */
    public char getSymbol();

}