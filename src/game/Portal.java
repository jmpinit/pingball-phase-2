package game;

import java.util.Arrays;
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


/***
 * A portal is a circular hole with default radius,
 * which teleports a ball to another portal gadget, possibly on a different board
 * 
 * @author pkalluri
 *
 */
public class Portal implements Gadget  {
    public static final int STATICUID = Sprite.Portal.ID;
    private final int instanceUID;
    private final String name;
    private final Vect position;
    private final static double RADIUS = 0.5;
    private final Circle boundary; //based on position and dimensions
    private Board sourceBoard;
    private Board targetBoard;
    private Portal targetPortal;
    private final String targetPortalName;

    
    public static final char SYMBOL = 'O';

    /***
     * Constructs portal with given name at given coordinates.
     * @param name name of portal
     * @param x x-coord of upper left corner of bounding box.
     * @param y y-coord of upper left corner of bounding box.
     */
    public Portal(String name, double x, double y, String targetPortalName) {
        this.instanceUID = NetworkProtocol.getUID();
        this.name = name;
        this.position = new Vect(x,y);
        this.boundary = new Circle(x+RADIUS,y+RADIUS,RADIUS);
        this.sourceBoard = null;
        this.targetBoard = null;
        this.targetPortal = null;
        this.targetPortalName = targetPortalName;
    }

    /**
     * Check the rep invariant.
     */
    private void checkRep(){
    }


    @Override
    public String getName(){
        checkRep();
        return name;
    }

    @Override
    public int getInstanceUID() {
        return instanceUID;
    }
    
    @Override
    public Set<Vect> getTiles(){ 
        checkRep();
        return new HashSet<Vect>(Arrays.asList(position));
    }


    @Override
    public char getSymbol(){
        checkRep();
        return SYMBOL;
    }


    @Override
    public double getTimeTillCollision(Ball ball){
        checkRep();
        if (targetPortal != null) {
            Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
            return Geometry.timeUntilCircleCollision(boundary, ballShape, ball.getVelocity());
        }
        else {
            return Double.POSITIVE_INFINITY;
        }
    }


    @Override
    public void progressAndCollide(double amountOfTime, Ball ball){
        checkRep();
        System.out.println("Progressing and colliding");
        ball.setPosition(targetPortal.getCenter());
        System.out.println("Going here: " + targetPortal.getName());
        System.out.println(targetPortal.getCenter().toString());
        ball.setActive(false);
        //velocity stays the same
        try {
            sourceBoard.queueToRemove(ball);
            targetBoard.queueToAdd(ball);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 
     * @return Vect representing center of Portal
     */
    private Vect getCenter() {
        checkRep();
        return new Vect(position.x()+RADIUS,position.y()+RADIUS);
    }
    
    /**
     * Portal's action is to do nothing.
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



    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result;
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Portal)) {
            return false;
        }
        Portal other = (Portal) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (sourceBoard == null) {
            if (other.sourceBoard != null) {
                return false;
            }
        } else if (!sourceBoard.equals(other.sourceBoard)) {
            return false;
        }
        return true;
    }

    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)position.x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)position.y())
        };
        
        return new NetworkState(fields);
    }


    @Override
    public int getStaticUID() {
        return STATICUID;
    }

    /***
     * Get target portal's name.
     * @return target portal's name
     */
    public String getTargetPortalName() {
        return targetPortalName;
    }

    /***
     * Sets the target Board to the given Board.
     * @param newBoard the given Board
     */
    public void setTargetBoard(Board newBoard) {
        this.targetBoard = newBoard;
    }

    /***
     * Sets the target Portal to the given Portal.
     * @param targetPortal the given Portal
     */
    public void setTargetPortal(Portal targetPortal) {
        this.targetPortal = targetPortal;
    }
    
    /**
     * Sets the source board to the given Board
     * @param newBoard the given Board
     */
    public void setSourceBoard(Board newBoard) {
        this.sourceBoard = newBoard;
    }

    private boolean sent = false;
    
    @Override
    public boolean hasBeenSent() {
        return sent;
    }
    
    @Override
    public void hasBeenSent(boolean v) {
        sent = v;
    }
}

