package gameParts;

import java.util.Set;

import physics.Vect;


/***

 * A game piece is any component of a board in a pingball game, except the board itself.
 * This includes bumpers, flippers, absorbers, balls, and board walls.
 * 
 * @author pkalluri
 */

public interface GamePiece {    
    
    /***
     * @return a String name representing name of gadget
     */
    public String getName();
    
    /***
     * Returns a char symbol to represent this gadget.
     * @return a char symbol to represent this gadget.
     */
    public char getSymbol();
    
    /***
     * Get current origin
     * @return current position
     */
    public Vect getPosition();
    
    /***
     * Get set of tiles that this gadgets occupies
     * @return Set<Vect>
     */
    public Set<Vect> getTiles();
    
    /***
     * Progresses this gadget by the given amountOfTime (in seconds),
     * assuming the given physical constants.
     * 
     * @param amountOfTime amount of time to progress
     * @param gravity constant value of gravity
     * @param mu constant value of the friction with respect to time
     * @param mu2 constant value of the friction with respect to distance
     */
    public void progress(double amountOfTime, double gravity, double mu, double mu2);
    

    /***
     * Does this gadget's action.
     */
    //for example, a flipper rotating
    public void doAction();
    
    
    
    /***
     * Calculates time until collision with this ball.
     * @param ball
     * @return a double representing the time, in seconds, to 
     * collision between the ball and the gadget
     */
    public double timeTillCollision(Ball ball);  

    

    /***
     * Progresses this gadget by the given amountOfTime (in seconds),
     * simplifying to no physical constants/accelerations,
     * and collides given ball with this gadget,
     * by updating ball's velocity and this gadget's velocity accordingly.
     * 
     * @param amountOfTime amount of time to progress
     * @param ball ball to collide with
     */
    public void progressAndCollide(double amountOfTime, Ball ball);

}