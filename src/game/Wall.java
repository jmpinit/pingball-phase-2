package game;

import java.util.HashSet;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;

/**
 * A Wall is a gadget that does no action except for reflecting balls during collisions IFF the Wall is currently not transparent.
 * 
 * That is: a Wall can either be concrete, in which case balls cannot pass through it,
 * or transparent, in which case balls can pass through it.
 * 
 * @author pkalluri
 *
 */
public class Wall implements Gadget {
    private final String name;
    boolean transparency;

    private final LineSegment wall; //based on end points
    
    private static final char SYMBOL = '.';

    
    public Wall(Vect oneEnd, Vect otherEnd) {
        this.name = "wall"; //all walls are named "wall"
        this.wall = new LineSegment(oneEnd.x(),oneEnd.y(),otherEnd.x(),otherEnd.y());
        this.transparency = false; //wall is initially concrete
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
     * Get set of tiles that this gadgets occupies
     * @return Set<Vect>
     */
    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> tiles = new HashSet<Vect>();
        for (int i=0; i<wall.p2().x()-wall.p1().x(); i++) {
            for (int j=0; j<wall.p2().y()-wall.p1().y();j++) {
                Vect tile = new Vect(
                        Math.min(wall.p1().x(),wall.p2().x()) +i,
                        Math.min(wall.p1().y(),wall.p2().y()) +j
                        );
                tiles.add(tile);
            }
        }
        checkRep();
        return tiles;
    }

    /***
     * Returns a char symbol to represent this gadget.
     * @return a char symbol to represent this gadget.
     */    
    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }
    
    /***
     * Get current origin
     * @return current position
     */
    private Vect getPosition() {
        checkRep();
        return this.wall.p1();
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
    public double getTimeTillCollision(Ball ball) {
        if (transparency) {
            return Double.POSITIVE_INFINITY;
        }else{
            final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
            return physics.Geometry.timeUntilWallCollision(this.wall, ballShape, ball.getVelocity());
        }
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
        
        Vect velocity = physics.Geometry.reflectWall(this.wall,ball.getVelocity());
        ball.setVelocity(velocity);
        checkRep();
    }

    /***
     * Sets transparency state to given transparency state.
     * @param transparency true iff this wall should be made transparent
     *                     false iff this wall should be made concrete
     */
    public void setTransparency(boolean transparency) {
        this.transparency = transparency;      
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
    public void progress(double amountOfTime, double gravity, double mu,
            double mu2) {
        checkRep();
        //Do nothing.  
    }
    
    private void checkRep() {
        assert (this.wall.p1().x() >= 0 && this.wall.p1().x() <=Board.SIDELENGTH);
        assert (this.wall.p2().x() >= 0 && this.wall.p2().x() <=Board.SIDELENGTH);
    }

    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)getPosition().x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)getPosition().y())
        };
        
        return new NetworkState(20, fields);
    }

}
