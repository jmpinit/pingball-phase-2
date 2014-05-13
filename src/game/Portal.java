package game;

import java.util.Set;

import physics.Vect;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;


/***
 * A portal is a circular hole with default radius,
 * which teleports a ball to another portal gadget, possibly on a different board
 * 
 * @author pkalluri
 *
 */
public class Portal implements Gadget  {
    private static final double RADIUS = .5;

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


    @Override
    public String getName(){
        return "";
    }


    @Override
    public Set<Vect> getTiles(){ 
        return null;
    }


    @Override
    public char getSymbol(){
        return 'a';
    }


    @Override
    public double getTimeTillCollision(Ball ball){
        return 0.0;
    }


    @Override
    public void progressAndCollide(double amountOfTime, Ball ball){
    }

    /**
     * Portal tells sister portal to release the captured ball on its board at the beginning of the next time step.
     */
    @Override
    public void doAction(){
        checkRep();
        //do nothing
    }


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

    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)getPosition().x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)getPosition().y())
        };
        
        return new NetworkState(4, fields);
    }




}

