package skeletonSpecs;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/** Represents the RightFlipper gadget class */

public class RightFlipper extends Flipper {

    /**
     * Definition of orientation: 1: top 2: bottom 3: left 4: right
     */
    private int orientation;
    /**
     * Definition of pivot: 1: topLeft 2: topRight 3: bottomLeft 4: bottomRight
     */
    private final int pivot;

    private final static double TIME_TO_TRIGGER = 0.001;
    private final static double NULL = 5;

    private boolean initial = true;
    private double flipperAngle;
    private LineSegment topLine;
    private LineSegment leftLine;
    private LineSegment rightLine;
    private LineSegment bottomLine;

    private final List<LineSegment> sides = new ArrayList<LineSegment>();

    private LineSegment oneLineFlipper;

    private final double xLoc;
    private final double yLoc;
    private final String name;

    public RightFlipper(double x, double y, int orient, String n) {

        super(x, y);
        xLoc = x;
        yLoc = y;

        name = n;

        if (orient == 0) {
            orientation = 3;
        } else if (orient == 90) {
            orientation = 1;
        } else if (orient == 180) {
            orientation = 4;
        } else {
            orientation = 2;
        }
        changeFlipperOrientation(orientation);

        sides.add(topLine);
        sides.add(leftLine);
        sides.add(rightLine);
        sides.add(bottomLine);

        if (orientation == 1) {
            pivot = 1;
        } else if (orientation == 2) {
            pivot = 4;
        } else if (orientation == 3) {
            pivot = 3;
        } else {
            pivot = 2;
        }
    }

    @Override
    public double trigger(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        double time = Geometry.timeUntilWallCollision(oneLineFlipper, ball.getCircle(), velocity);
        if (time < TIME_TO_TRIGGER) {
            return time;
        }
        return NULL;
    }

    /**
     * Returns the orientation of the flipper; whether it is up, down, left, or
     * right
     */
    private int findFlipperOrientation() {
        if (orientation == 1) {
            if (pivot == 1) {
                return 3;
            } else {
                return 4;
            }
        } else if (orientation == 2) {
            if (pivot == 1) {
                return 3;
            } else {
                return 4;
            }
        } else if (orientation == 3) {
            if (pivot == 1) {
                return 1;
            } else {
                return 2;
            }
        } else {
            if (pivot == 2) {
                return 1;
            } else {
                return 2;
            }
        }
    }

    /**
     * Changes the orientation of the flipper to up, down, left, or right
     */
    private void changeFlipperOrientation(int orientation) {
        if (orientation == 1) {
            oneLineFlipper = new LineSegment(xLoc, yLoc, xLoc + 2, yLoc);
            topLine = new LineSegment(xLoc, yLoc, xLoc + 2, yLoc);
            leftLine = new LineSegment(xLoc, yLoc, xLoc, yLoc + 0.5);
            rightLine = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 0.5);
            bottomLine = new LineSegment(xLoc, yLoc + 0.5, xLoc + 2, yLoc + 0.5);
        } else if (orientation == 2) {
            oneLineFlipper = new LineSegment(xLoc, yLoc + 2, xLoc + 2, yLoc + 2);
            topLine = new LineSegment(xLoc, yLoc + 1.5, xLoc + 2, yLoc + 1.5);
            leftLine = new LineSegment(xLoc, yLoc + 1.5, xLoc, yLoc + 2);
            rightLine = new LineSegment(xLoc + 2, yLoc + 1.5, xLoc + 2, yLoc + 2);
            bottomLine = new LineSegment(xLoc, yLoc + 2, xLoc + 2, yLoc + 2);
        } else if (orientation == 3) {
            oneLineFlipper = new LineSegment(xLoc, yLoc, xLoc, yLoc + 2);
            topLine = new LineSegment(xLoc, yLoc, xLoc + 0.5, yLoc);
            leftLine = new LineSegment(xLoc, yLoc, xLoc, yLoc + 2);
            rightLine = new LineSegment(xLoc + 0.5, yLoc, xLoc + 0.5, yLoc + 2);
            bottomLine = new LineSegment(xLoc, yLoc + 2, xLoc + 0.5, yLoc + 2);
        } else {
            oneLineFlipper = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 2);
            topLine = new LineSegment(xLoc + 1.5, yLoc, xLoc + 2, yLoc);
            leftLine = new LineSegment(xLoc + 1.5, yLoc, xLoc + 1.5, yLoc + 2);
            rightLine = new LineSegment(xLoc + 2, yLoc, xLoc + 2, yLoc + 2);
            bottomLine = new LineSegment(xLoc + 1.5, yLoc + 2, xLoc + 2, yLoc + 2);
        }
    }

    /**
     * Moves the right flipper for a certain time period
     * 
     * @time time for flipper to be moving
     */
    @Override
    public void move(double time) {
        boolean change = false;
        double FLIPPER_ANGULAR_VELOCITY = 1080;
        double angleToBeRotated = time * FLIPPER_ANGULAR_VELOCITY;
        // * Math.PI / 180.0
        while (angleToBeRotated > 360) {
            angleToBeRotated = angleToBeRotated - 360;
        }
        if (initial == true) {
            if (flipperAngle + angleToBeRotated < 90) {
                flipperAngle = flipperAngle + angleToBeRotated;
            } else {
                angleToBeRotated = 90 - flipperAngle;
                flipperAngle = 90;
                change = true;
                // initial = false;
            }
        } else {
            if (flipperAngle - angleToBeRotated > 0) {
                flipperAngle = flipperAngle - angleToBeRotated;
            } else {
                angleToBeRotated = flipperAngle;
                flipperAngle = 0;
                change = true;
                // initial = true;
            }
        }
        Vect pivotPoint;
        if (pivot == 1) {
            pivotPoint = new Vect(xLoc, yLoc);
        } else if (pivot == 2) {
            pivotPoint = new Vect(xLoc + 2, yLoc);
        } else if (pivot == 3) {
            pivotPoint = new Vect(xLoc, yLoc + 2);
        } else {
            pivotPoint = new Vect(xLoc + 2, yLoc + 2);
        }
        System.out.println(getState());
        if (initial == false) {
            oneLineFlipper = Geometry.rotateAround(oneLineFlipper, pivotPoint, new Angle(0 - angleToBeRotated * Math.PI
                    / 180.0));
        } else {
            oneLineFlipper = Geometry.rotateAround(oneLineFlipper, pivotPoint, new Angle(angleToBeRotated * Math.PI
                    / 180.0));
        }
        if (change) {
            initial = !initial;
        }
        System.out.println(getState());
    }

    @Override
    public void action(Ball ball) {
        Vect velocity = ball.getFlippedVelocity();
        LineSegment wall = null;
        // for (LineSegment ls : sides) {
        if (Geometry.timeUntilWallCollision(oneLineFlipper, ball.getCircle(), velocity) < TIME_TO_TRIGGER) {
            wall = oneLineFlipper;
        }
        // }
        double tx = Geometry.timeUntilWallCollision(wall, ball.getCircle(), velocity);
        ball.move(tx);
        Vect newDir = Geometry.reflectWall(wall, velocity, 0.95);
        newDir = new Vect(newDir.x(), -newDir.y());
        ball.changeVelocity(newDir);
        ball.move(TIME_TO_TRIGGER - tx);
        if (initial == true) {
            initial = false;
        } else {
            initial = true;
        }
        moveFlipper();
        // changeFlipperOrientation(findFlipperOrientation());
    }

    public void moveFlipper() {
        if (orientation == 1) {
            if (pivot == 1) {
                orientation = 3;
            } else {
                orientation = 4;
            }
        } else if (orientation == 2) {
            if (pivot == 3) {
                orientation = 3;
            } else {
                orientation = 4;
            }
        } else if (orientation == 3) {
            if (pivot == 1) {
                orientation = 1;
            } else {
                orientation = 2;
            }
        } else {
            if (pivot == 2) {
                orientation = 1;
            } else {
                orientation = 2;
            }
        }
        changeFlipperOrientation(orientation);
    }

    /** Returns the angle of the current flipper */
    public double getState() {
        return flipperAngle;
    }

    @Override
    public String toString() {
        if (orientation == 1 || orientation == 2) {
            return "_";
        } else {
            return "|";
        }
    }

    /**
     * Returns a string describing the orientation of the flipper.
     * 
     * @return orientation of flipper
     */
    public String getOrientation() {
        if (orientation == 1) {
            return "top";
        } else if (orientation == 2) {
            return "bottom";
        } else if (orientation == 3) {
            return "left";
        } else {
            return "right";
        }
    }

    /** Returns the name of the gadget */
    public String getName() {
        return name;
    }

    /**
     * Returns a string representing the type of gadget.
     */
    public String type() {
        return "flipper";
    }

}
