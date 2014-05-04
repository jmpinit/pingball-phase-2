package skeletonSpecs;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class TriangularBumper implements Gadget {
    /**
     * Thread Safety Information: TriangularBumper is threadsafe because it is never altered after creation.
     */
    
    private final static double REFL_COEFF = 1.0;
    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5; //Used as placeholder value

    private final double xCoord;
    private final double yCoord;
    private final double legLength = 1.0;
    private final double hypotenuseLength = Math.sqrt(2);
    private final int orientation; // in terms of degrees
    private final String name;

    private final LineSegment leg1; // horizontal leg 
    private final LineSegment leg2; // vertical leg
    private final LineSegment hypotenuse;

    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    /**
     * Creates a 45-45-90 triangle bumper with the user-inputted parameters
     * @param xLoc, x coordinate of top-left point of square which contains the triangle
     * @param yLoc, y coordinate of top-left point of square which contains the triangle
     * @param orientation, angle triangle bumper is rotated
     * @param name
     */
    public TriangularBumper(double xLoc, double yLoc, int orientation, String name) {

        this.xCoord = xLoc;
        this.yCoord = yLoc;
        this.name = name;
        if (orientation == 0) { //Right angle is in top-left corner
            leg1 = new LineSegment(xLoc,yLoc, xLoc+legLength, yLoc);
            leg2 = new LineSegment(xLoc, yLoc, xLoc, yLoc + legLength);
            hypotenuse = new LineSegment(xLoc+legLength,yLoc,xLoc,yLoc + legLength);
        } else if (orientation == 90) { //Right angle is in top-right corner
            leg1 = new LineSegment(xLoc,yLoc, xLoc+legLength, yLoc);
            leg2 = new LineSegment(xLoc+legLength,yLoc,xLoc+legLength,yLoc+legLength);
            hypotenuse = new LineSegment(xLoc,yLoc,xLoc+legLength,yLoc+legLength);
        } else if (orientation == 180) { //Right angle is in bottom-right corner
            leg1 = new LineSegment(xLoc,yLoc+legLength,xLoc+legLength,yLoc+legLength);
            leg2 = new LineSegment(xLoc+legLength,yLoc,xLoc+legLength,yLoc+legLength);
            hypotenuse = new LineSegment(xLoc,yLoc+legLength,xLoc+legLength,yLoc);
        } else { //Right angle is in bottom-left corner
            leg1 = new LineSegment(xLoc,yLoc+legLength,xLoc+legLength,yLoc+legLength);
            leg2 = new LineSegment(xLoc,yLoc,xLoc,yLoc+legLength);
            hypotenuse = new LineSegment(xLoc,yLoc,xLoc+legLength,yLoc+legLength);
        }

        this.orientation = orientation;

        sides.add(leg1);
        sides.add(leg2);
        sides.add(hypotenuse);
    }

    /** Calculates time an inputted ball will take to hit this bumper. 
     * Returns a very large value if not nearby (5 seconds).
     * @param ball to check if it's nearby
     * @return amount of time to take to trigger object based on inputted ball.
     */
    public double trigger(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        for (LineSegment ls : sides) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity);
            if (time < TIME_TO_TRIGGER) {
                return time;
            }
        }
        return NULL;
    }

    /**
     * Called when inputted ball is less than 0.001 seconds from impacting gadget (as found out from the trigger function).
     * Handles the resulting physics of when given ball colldes with this bumper.
     */
    @Override
    public void action(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        LineSegment wall = null;
        for (LineSegment ls : sides) {
            if (Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity) < TIME_TO_TRIGGER) {
                wall = ls;
            }
        }
        double tx = Geometry.timeUntilWallCollision(wall, ball.getCircle(), velocity);
        ball.move(tx);
        Vect newDir = Geometry.reflectWall(wall, velocity, REFL_COEFF);
        newDir = new Vect(newDir.x(), -newDir.y());
        ball.changeVelocity(newDir);
        ball.move(TIME_TO_TRIGGER - tx);

    }

    /**
     * @return the reflection coefficient
     */
    @Override
    public double getReflCoeff() {
        return REFL_COEFF;
    }

    /**
     * Returns a list of the three line segments used to make this bumper: 
     * leg1 (horizontal), leg2 (vertical), and the hypotenuse 
     * @return list of all the line segments that make up the bumper
     */
    public List<LineSegment> getLineSegments() {
        List<LineSegment> ans = new ArrayList<LineSegment>();
        ans.add(leg1);
        ans.add(leg2);
        ans.add(hypotenuse);
        return ans;
    }

    /**
     * @return a string representation of a triangular bumper as seen on a board. Changes depending on orientation
     */
    @Override
    public String toString() {
        if (orientation == 0 || orientation == 180) {
            return "/";
        } else {
            return "\\";
        }
    }
    
    /**
     * @return x coordinate of the  top-left corner of bumper.
     */
    public double getX(){
        return xCoord;
    }

    /**
     * @return y coordinate of the  top-left corner of bumper.
     */
    public double getY(){
        return yCoord;
    }
    
    /**
     * @return name of the bumper
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return string representing the type of gadget.
     */
    public String type() {
        return "triangle";
    }

}
