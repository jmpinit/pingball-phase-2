package game;

import java.util.HashSet;
import java.util.Set;

import client.Sprite;
import physics.Circle;
import physics.Geometry;
import physics.Vect;
import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;


/**
 * A CircularBumper is a circular gadget of default diameter that does no actions other than reflecting balls during collisions.
 * 
 * @author: jzwang
 */
public class CircularBumper implements Gadget {
    public static final int STATICUID = Sprite.CircularBumper.ID;
    private final int instanceUID;
    private final String name;
    private final Vect boundingBoxPosition;
    public final static double RADIUS = 0.5;
    
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
        this.boundingBoxPosition = new Vect(x,y);
        this.boundary = new Circle(x+RADIUS,y+RADIUS,RADIUS);
        this.instanceUID = NetworkProtocol.getUID();

    }


    @Override
    public String getName() {
        checkRep();
        return this.name;
    }

    @Override
    public int getInstanceUID() {
        return instanceUID;
    }
    
    /**
     * 
     * @return Vect representing center of circular bumper
     */
    private Vect getCenter() {
        checkRep();
        return new Vect(boundingBoxPosition.x()+RADIUS,boundingBoxPosition.y()+RADIUS);
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
        assert (this.boundingBoxPosition.x() >= 0 && this.boundingBoxPosition.x() <= Board.SIDELENGTH);
        assert (this.boundingBoxPosition.y() >= 0 && this.boundingBoxPosition.y() <= Board.SIDELENGTH);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result
            + ((boundingBoxPosition == null) ? 0 : boundingBoxPosition.hashCode());
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
        if (boundingBoxPosition == null) {
            if (other.boundingBoxPosition != null)
                return false;
        } else if (!boundingBoxPosition.equals(other.boundingBoxPosition))
            return false;
        return true;
    }

    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)boundingBoxPosition.x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)boundingBoxPosition.y())
        };
        
        return new NetworkState(fields);
    }
    
    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Set<Vect> tiles = new HashSet<Vect>();

        Vect tile = boundingBoxPosition;
        tiles.add(tile);

        return tiles;
    }
    
    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }

    @Override
    public int getStaticUID() {
        return STATICUID;
    }

}
