package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;


/**
 * A SquareBumper is a square gadget of default size that does no actions other than reflecting balls during collisions.
 * 
 * @author jzwang
 */
public class SquareBumper implements Gadget {
    private final String name;
    private final Vect position;
    
    //based on position and dimensions
    private final List<LineSegment> boundaries; 
    private final List<Circle> corners;

    private static final char SYMBOL = '#';


    public SquareBumper(String name, double x, double y) {
        this.name = name;
        this.position = new Vect(x,y);
        
        //boundaries:
        LineSegment top = new LineSegment(x,y,x+1,y);
        LineSegment left = new LineSegment(x,y,x,y+1);
        LineSegment right = new LineSegment(x+1,y,x+1,y+1);
        LineSegment bottom = new LineSegment(x,y+1,x+1,y+1);
        Circle topRight = new Circle(x+1,y,0);
        Circle topLeft = new Circle(x,y,0);
        Circle bottomRight = new Circle(x+1,y+1,0);
        Circle bottomLeft = new Circle(x,y+1,0);
        this.boundaries = new ArrayList<LineSegment>(Arrays.asList(left,top,right,bottom));
        this.corners = new ArrayList<Circle>(Arrays.asList(topLeft,topRight,bottomRight,bottomLeft));
    }

    /**
     * Check the rep invariant.
     */
    private void checkRep() {
        assert (this.position.x() >= 0 && this.position.x() <= Board.SIDELENGTH);
        assert (this.position.y() >= 0 && this.position.y() <= Board.SIDELENGTH);
    }


    @Override
    public String getName() {
        checkRep();
        return this.name;
    }


    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Vect tile = position;
        Set<Vect> set = new HashSet<Vect>();
        set.add(tile);
        return set;
    }


    @Override
    public char getSymbol() {
        checkRep();
        return SYMBOL;
    }





    @Override
    public double getTimeTillCollision(Ball ball) {
        checkRep();
        double minTime=Double.POSITIVE_INFINITY;
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        for (LineSegment line : boundaries) {
            double collisionTime = Geometry.timeUntilWallCollision(line, ballShape, ball.getVelocity());
            if (collisionTime < minTime) {
                minTime = collisionTime;
            }
        }
        for (Circle circle : corners) {
            double cornerCollisionTime = Geometry.timeUntilCircleCollision(circle, ballShape, ball.getVelocity());
            if (cornerCollisionTime < minTime) {
                minTime = cornerCollisionTime;
            }
        }
        checkRep();
        return minTime;
    }


    @Override
    public void progressAndCollide(double amountOfTime, Ball ball) {
        checkRep();
        ball.progressIgnoringPhysicalConstants(amountOfTime);

        //find closest wall
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        LineSegment closestWall = boundaries.get(0);
        Circle closestCorner = corners.get(0);
        double minTime=Double.POSITIVE_INFINITY;
        double minCornerTime=Double.POSITIVE_INFINITY;

        for (LineSegment line : boundaries) {
            double collisionTime = Geometry.timeUntilWallCollision(line, ballShape, ball.getVelocity());
            if (collisionTime < minTime) {
                minTime = collisionTime;
                closestWall = line;
            }
        }
        for (Circle circle : corners) {
            double cornerCollisionTime = Geometry.timeUntilCircleCollision(circle, ballShape, ball.getVelocity());
            if (cornerCollisionTime < minCornerTime) {
                minCornerTime = cornerCollisionTime;
                closestCorner = circle;
            }
        }

        Vect velocity;
        if (minTime < minCornerTime) {
            velocity = physics.Geometry.reflectWall(closestWall,ball.getVelocity());
        }
        else {
            velocity = physics.Geometry.reflectCircle(closestCorner.getCenter(), ball.getPosition(), ball.getVelocity());
        }

        ball.setVelocity(velocity);
        checkRep();
    }

    /**
     * Square bumpers have no action.
     */
    @Override
    public void doAction() {
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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((corners == null) ? 0 : corners.hashCode());
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
        SquareBumper other = (SquareBumper) obj;
        if (corners == null) {
            if (other.corners != null)
                return false;
        } else if (!corners.equals(other.corners))
            return false;
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
        
        return new NetworkState(5, fields);
    }




}
