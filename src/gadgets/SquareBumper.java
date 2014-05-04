package skeletonSpecs;

import java.util.ArrayList;
import java.util.List;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class SquareBumper implements Gadget {

    /**
     * Thread Safety Information: SquareBumper is threadsafe because it is never altered after creation.
     */
    private final static double REFL_COEFF = 1.0;

    private final double xCoord;
    private final double yCoord;

    //Following are constants specified by the project
    private final static double EDGE_LENGTH = 1.0;
    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5; //Used as placeholder value

    private final LineSegment topLine;
    private final LineSegment bottomLine;
    private final LineSegment leftLine;
    private final LineSegment rightLine;

    private final String name;

    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    /**
     * Creates a square bumper with the user-inputted parameters. 
     * Has a side-length of 1.0
     * @param topLeftX, x coordinate of top-left point on the square
     * @param topLeftY, y coordinate of top-left point on the square
     * @param n, name
     */
    public SquareBumper(double topLeftX, double topLeftY, String n) {
        name = n;
        xCoord = topLeftX;
        yCoord = topLeftY;
        topLine = new LineSegment(xCoord, yCoord, xCoord + EDGE_LENGTH, yCoord);
        bottomLine = new LineSegment(xCoord, yCoord + EDGE_LENGTH, xCoord + EDGE_LENGTH, yCoord + EDGE_LENGTH);
        leftLine = new LineSegment(xCoord, yCoord, xCoord, yCoord + EDGE_LENGTH);
        rightLine = new LineSegment(xCoord + EDGE_LENGTH, yCoord, xCoord + EDGE_LENGTH, yCoord + EDGE_LENGTH);

        sides.add(topLine);
        sides.add(bottomLine);
        sides.add(leftLine);
        sides.add(rightLine);
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
     * Handles the resulting physics of when given ball collides with this bumper.
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
        Vect newDir = Geometry.reflectWall(wall, velocity);
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
     * Returns a list of the lines used to make the square:
     * top line, bottom line, left line, right line
     * @return list of lines that make up this bumper
     */
    public List<LineSegment> getLineSegments() {
        List<LineSegment> ans = new ArrayList<LineSegment>();
        ans.add(topLine);
        ans.add(bottomLine);
        ans.add(leftLine);
        ans.add(rightLine);
        return ans;
    }

    /**
     * Returns a string representation of a square bumper as seen on the board.
     */
    @Override
    public String toString() {
        return "#";
    }

    /**
     * @return x coordinate of top-left corner of bumper.
     */
    public double getX() {
        return this.xCoord;
    }

    /**
     * @return y coordinate of top-left corner of bumper.
     */
    public double getY() {
        return this.yCoord;
    }

    /**
     * @return name of the bumper
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns a string representing the type of gadget.
     */
    public String type() {
        return "square";
    }

}
