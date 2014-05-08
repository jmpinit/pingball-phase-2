package game;

import java.util.Set;
import physics.Vect;


/***
 * A portal is a circular hole with diameter 1L
 * which teleports a ball to another portal gadget, possibly on a different board
 * 
 * @author pkalluri
 *
 */
public class Portal implements Gadget  {

    /***
     * Constructs portal with given name at given coordinates.
     * @param name
     * @param x
     * @param y
     */
    public Portal(String name, double x, double y) {
    }

    /**
     * Check the rep invariant.
     */
    private void checkRep(){
    }

    /**
     * @return string name representing name of portal
     */
    @Override
    public String getName(){
        return "";
    }

    /**
     * @return set of tiles taken up by portal
     */
    @Override
    public Set<Vect> getTiles(){ 
        return null;
    }

    /**
     * @return char symbol that represents this gadget on a board string
     */
    @Override
    public char getSymbol(){
        return 'a';
    }

    /**
     * @return Vect position of gadget on board
     */
    @Override
    public Vect getPosition(){
        return null;
    }


    /***
     * Calculates time until collision with this ball.
     * @param ball
     * @return a double representing the time, in seconds, to 
     * collision between the ball and the gadget
     */
    @Override
    public double timeTillCollision(Ball ball){
        return 0.0;
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
    public void progressAndCollide(double amountOfTime, Ball ball){
    }

    /**
     * Portals have no action.
     */
    @Override
    public void doAction(){
        checkRep();
        //do nothing
    }

    /***
     * Progresses this gadget by the given amountOfTime (in seconds),
     * assuming the given physical constants. Portals have no progress.
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
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }




}

