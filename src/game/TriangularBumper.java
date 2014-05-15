package game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.Sprite;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;

/**
 * A TriangularBumper is a right-triangle-shaped gadget of default size that does no actions other than reflecting balls during collisions.
 * It comes in 4 possible orientations, with the right angle in each of the 4 corners of its bounding box.
 * 
 * @author jzwang
 * 
 */
public class TriangularBumper implements Gadget {
    public static final int STATICUID = Sprite.TriangularBumper.ID;
    private final int instanceUID;
    private final String name;
    private final Vect upperLeftCornerOfBoundingBox;
    private final double angle;
    private final double orientation;
    
    //based on position and dimensions
    private final List<LineSegment> boundaries; 
    private final List<Circle> corners;

    private static final char SYMBOL1 = '\\';
    private static final char SYMBOL2 = '/';


    /**
     * Creates triangle bumpers on the board from file information
     * @param file board file
     */
    public TriangularBumper(String name,double x, double y, double orientation) {
        this.instanceUID = NetworkProtocol.getUID();
        this.name = name;
        this.upperLeftCornerOfBoundingBox = new Vect(x,y);
        this.angle = orientation;
        this.orientation = orientation;

        //create boundaries
        LineSegment vertical = (orientation == 0||orientation == 270) ? new LineSegment(x,y,x,y+1) : new LineSegment(x+1,y,x+1,y+1);
        LineSegment horizontal = (orientation == 0||orientation == 90) ? new LineSegment(x,y,x+1,y) : new LineSegment(x,y+1,x+1,y+1);
        LineSegment hypotenuse = (orientation == 0||orientation == 180) ? new LineSegment(x,y+1,x+1,y) : new LineSegment(x,y,x+1,y+1);

        if (orientation == 0) {
            Circle firstCorner = new Circle(x,y,0);
            Circle secondCorner = new Circle(x+1,y,0);
            Circle thirdCorner = new Circle(x,y+1,0);
            corners = new ArrayList<Circle>(Arrays.asList(firstCorner,secondCorner,thirdCorner));
        }
        else if (orientation == 90) {
            Circle firstCorner = new Circle(x,y,0);
            Circle secondCorner = new Circle(x+1,y,0);
            Circle thirdCorner = new Circle(x+1,y+1,0);
            corners = new ArrayList<Circle>(Arrays.asList(firstCorner,secondCorner,thirdCorner));
        }
        else if (orientation == 180) {
            Circle firstCorner = new Circle(x+1,y,0);
            Circle secondCorner = new Circle(x+1,y+1,0);
            Circle thirdCorner = new Circle(x,y+1,0);
            corners = new ArrayList<Circle>(Arrays.asList(firstCorner,secondCorner,thirdCorner));
        }
        else {
            Circle firstCorner = new Circle(x+1,y+1,0);
            Circle secondCorner = new Circle(x,y+1,0);
            Circle thirdCorner = new Circle(x,y,0);
            corners = new ArrayList<Circle>(Arrays.asList(firstCorner,secondCorner,thirdCorner));
        }

        this.boundaries = new ArrayList<LineSegment>(Arrays.asList(horizontal,vertical,hypotenuse));
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


    @Override
    public Set<Vect> getTiles() {
        checkRep();
        Vect tile = upperLeftCornerOfBoundingBox;
        Set<Vect> set = new HashSet<Vect>();
        set.add(tile);
        return set;
    }


    @Override
    public char getSymbol() {
        checkRep();
        //gadget angles of 0, 90, 180, 270 degrees
        if (this.angle == 0) {
            return SYMBOL2;
        }
        if (this.angle == 180) {
            return SYMBOL2;
        }
        if (this.angle == 90) {
            return SYMBOL1;
        }
        else {// (this.angle == 270)
            return SYMBOL1;
        }

    }



    @Override
    public double getTimeTillCollision(Ball ball) {
        checkRep();
        double minTime=Double.POSITIVE_INFINITY;
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        for (LineSegment line : boundaries) {
            double collisionTime = physics.Geometry.timeUntilWallCollision(line, ballShape, ball.getVelocity());
            if (collisionTime < minTime) {
                minTime = collisionTime;
            }
        }

        for (Circle corner : corners) {
            double collisionTime = physics.Geometry.timeUntilCircleCollision(corner, ballShape, ball.getVelocity());
            if (collisionTime < minTime) {
                minTime = collisionTime;
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
            double collisionTime = physics.Geometry.timeUntilWallCollision(line, ballShape, ball.getVelocity());
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
     * Triangular bumpers have no action.
     */
    @Override
    public void doAction() {
        //Do nothing.
        checkRep();
    }

    @Override
    public void progress(double amountOfTime, double gravity, double mu, double mu2) {
        //Do nothing.
        checkRep();
    }

    /**
     * Check the rep invariant.
     */
    private void checkRep() {
        assert (this.angle == 0 || this.angle == 90 ||this.angle == 180 ||this.angle == 270); //angles of 0, 90, 180, 270 degrees
        assert (this.upperLeftCornerOfBoundingBox.x() >= 0 && this.upperLeftCornerOfBoundingBox.x() <= Board.SIDELENGTH);
        assert (this.upperLeftCornerOfBoundingBox.y() >= 0 && this.upperLeftCornerOfBoundingBox.y() <= Board.SIDELENGTH);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((corners == null) ? 0 : corners.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
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
        TriangularBumper other = (TriangularBumper) obj;
        if (Double.doubleToLongBits(angle) != Double
                .doubleToLongBits(other.angle))
            return false;
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
        return true;
    }


    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)upperLeftCornerOfBoundingBox.x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)upperLeftCornerOfBoundingBox.y()),
                new Field(FieldName.ANGLE, (long)orientation)
        };
        
        return new NetworkState(fields);
    }


    @Override
    public int getStaticUID() {
        return STATICUID;
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
