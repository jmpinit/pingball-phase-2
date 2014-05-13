package game;

import java.util.HashSet;
import java.util.Set;

import physics.Circle;
import physics.Geometry;
import physics.Vect;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;


/**
 * A CircularBumper is a circular gadget of default diameter that does no actions other than reflecting balls during collisions.
 * 
 * @author: jzwang
 */
public class CircularBumper implements Gadget {
    private final String name;
    private final Vect position;
    private final static double RADIUS = 0.5;
    private static final char SYMBOL = '0';

    private final Circle boundary; //based on position and dimensions




    /***
     * Consturcts circle bumper
     * @param name name of this gadget
     * @param x x-coordinate of upper left corner of bounding box
     * @param y y-coordinate of upper left corner of bounding box
     */
    public CircularBumper(String name, double x, double y) {
        this.name = name;
        this.position = new Vect(x,y);
        this.boundary = new Circle(x+RADIUS,y+RADIUS,RADIUS);

    }


    @Override
    public String getName() {
        checkRep();
        return this.name;
    }

    /**
     * 
     * @return Vect representing center of circular bumper
     */
    private Vect getCenter() {
        checkRep();
        return new Vect(position.x()+RADIUS,position.y()+RADIUS);
    }


    @Override
    public double getTimeTillCollision(Ball ball) {
        checkRep();
        Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        return Geometry.timeUntilCircleCollision(boundary, ballShape, ball.getVelocity());
    }

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


    @Override
    public void progress(double amountOfTime, double gravity, double mu, double mu2) {
        //do nothing
        checkRep();
    }

    /**
     * Check the rep invariant.
     */
    private void checkRep() {
        assert (this.position.x() >= 0 && this.position.x() <= Board.SIDELENGTH);
        assert (this.position.y() >= 0 && this.position.y() <= Board.SIDELENGTH);

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

    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)position.x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)position.y())
        };
        
        return new NetworkState(2, fields);
    }
    
    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> tiles = new HashSet<Vect>();

        Vect tile = position;
        tiles.add(tile);

        return tiles;
    }
    
    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }



}
