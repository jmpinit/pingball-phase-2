package skeletonSpecs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class OuterWalls implements Gadget {

    /**
     * Thread Safety Information: OuterWalls is threadsafe because it's never directly touched; it's only accessible
     * through the Board class (which in itself is also threadsafe).
     */
    
    private final LineSegment upperBorder;
    private final LineSegment lowerBorder;
    private final LineSegment leftBorder;
    private final LineSegment rightBorder;
    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5;
    private final static int width = 20;

    private Map<String, String> wallMapping = new HashMap<String, String>();

    private String name;

    /**
     * Creates outer wall based on a board's width and height being 20L.
     */
    public OuterWalls() {

        name = "";

        upperBorder = new LineSegment(0, 0, width, 0);
        lowerBorder = new LineSegment(0, width, width, width);
        leftBorder = new LineSegment(0, 0, 0, width);
        rightBorder = new LineSegment(width, 0, width, width);

        sides.add(upperBorder);
        sides.add(lowerBorder);
        sides.add(leftBorder);
        sides.add(rightBorder);
        this.initializeWallVisibility();
    }

    /** Calculates time an inputted ball will take to hit this bumper.
     * Returns a very large value if not nearby (5 seconds).
     * @param ball to check if it's nearby
     * @return amount of time to take to trigger object based on inputted ball.
     */
    @Override
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
        return 1;
    }
    
    /**
     * @return a string representation of an outer wall as seen on the board.
     */
    @Override
    public String toString() {
        return ".";
    }

    /**
     * @return x coordinate of the top-left piece of wall. 
     * This is ALWAYS 0.
     */
    public double getX() {
        return 0.0;
    }

    /**
     * @return y coordinate of the top-left piece of wall. 
     * This is ALWAYS 0.
     */
    public double getY() {
        return 0.0;
    }

    /**
     * @return name of the gadget. 
     */
    public String getName() {
        return name;
    }

    /**
     * Makes walls aware of a board being joined to it.
     * @param nameOfWall, wall that board is being joined to
     * @param nameOfBoard, name of other board being joined
     */
    public void changeWallVisibility(String nameOfWall, String nameOfBoard) {
        if (!nameOfBoard.equals("")) {
            wallMapping.put(nameOfWall, nameOfBoard);
        }
    }

    /**
     * Helper method made for debugging purposes. Shows which walls the ball is closest to.
     * @param ball to check proximity to walls
     * @return list of walls very close to the given ball.
     */
    public List<String> getVisibility(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        LineSegment closestLine = upperBorder;
        for (LineSegment ls : sides) {
            double time = Geometry.timeUntilWallCollision(ls, ball.getCircle(), velocity);
            if (time < TIME_TO_TRIGGER) {
               closestLine = ls;
            }
        }
        List<String> ans = new ArrayList<String>();
        if (closestLine.equals(upperBorder)){
            ans.add("top");
            ans.add(wallMapping.get("top"));
        } else if (closestLine.equals(lowerBorder)){
            ans.add("bottom");
            ans.add(wallMapping.get("bottom"));
        } else if (closestLine.equals(leftBorder)){
            ans.add("left");
            ans.add(wallMapping.get("left"));
        } else if (closestLine.equals(rightBorder)){
            ans.add("right");
            ans.add(wallMapping.get("right"));
        } 
        
        return ans;
    }
    
    /**
     * Helper method that initializes the map used to keep track of walls and adjoining boards.
     */
    private void initializeWallVisibility() {
        wallMapping.put("top", "");
        wallMapping.put("bottom", "");
        wallMapping.put("right", "");
        wallMapping.put("left", "");
    }

    /**
     * Returns a string representing the type of gadget.
     */
    public String type() {
        return "outer";
    }
    
    /**
     * @return a map of the four outer walls and the name of boards they're adjoined to
     */
    public Map<String, String> getWallMapping() {
        return wallMapping;
    }

}
