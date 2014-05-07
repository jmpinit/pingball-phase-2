package game;

import java.util.HashSet;
import java.util.Set;

import physics.Angle;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * A generally rectangular rotating shape with bounding box of size 2Lx2L
 * 
 * Flippers come in two different varieties, left flippers and right flippers. 
 * A left flipper begins its rotation in a counter-clockwise 
 * and a right flipper begins its rotation in a clockwise direction.
 * 
 * When a flipper's action is triggered, the flipper rotates at a constant angular velocity 
 * of 1080 degrees per second to a position 90*(Math.PI/180) degrees away from its starting position. 
 * When its action is triggered a second time, the flipper rotates back to its original 
 * position at an angular velocity of 1080 degrees per second.
 * 
 * If the flipper has a linear velocity, it must be accounted for when it 
 * comes into contact with a ball.
 * 
 * Trigger: generated whenever the ball hits it
 * Action: rotates 90*(Math.PI/180) degrees 
 * Coefficient of reflection: 0.95 
 * 
 * @author jzwang
 *
 */
public class Flipper implements Gadget {
    private final String name;
    private static final int LENGTH = 2;
    private static final double ROTATIONALSPEED = 18.8495559;      //1080 degrees per second, in radians
    private final Vect boundingBoxPosition;
    private final Vect pivotPosition;
    private final double maxAngle; //most counterclockwise
    private final double minAngle; //most clockwise
    private static final char VERTICALSYMBOL = '|';
    private static final char HORIZONTALSYMBOL = '-';
    private final char ROTATINGSYMBOL;

    private double currentAngle;
    private LineSegment flipperShape;
    private boolean isRotatingCounterclockwise;
    private boolean isRotating;

    //Note: all angles are measured in degrees, counterclockwise from 'down'



    /***
     * Creates flippers on the board from file.
     * @param file board file
     */
    public Flipper(String leftOrRight, String name, double x, double y, double orientation) {
        this.name = name;
        this.boundingBoxPosition = new Vect(x,y);

        this.currentAngle = 0;
        if (orientation == 90) {
            this.currentAngle = 270 * Math.PI / 180;
        } else if (orientation == 270) {
            this.currentAngle = 90 * Math.PI / 180;
        } else if (orientation == 180) {
            this.currentAngle = 180 * Math.PI / 180;
        }
        if (leftOrRight.equals("left")) {
            this.minAngle = this.currentAngle;
            if (this.minAngle != 270 * Math.PI / 180) {
                this.maxAngle = this.currentAngle + 90*(Math.PI/180);
            } else {
                this.maxAngle = 0;
            }
            this.isRotatingCounterclockwise = true; //left starts with rotating counterclckwise
        }
        else { //leftOrRight equals right
            this.maxAngle = this.currentAngle;
            if (this.maxAngle != 0) {
                this.minAngle = this.currentAngle - 90*(Math.PI/180);
            } else {
                this.minAngle = 270 * (Math.PI/180);
            }
            this.isRotatingCounterclockwise = false; //right starts with rotating clockwise
        }

        //find pivot
        if (this.maxAngle == 0*(Math.PI/180)) {
            this.pivotPosition = new Vect(x + LENGTH,y);
            ROTATINGSYMBOL = '/';
            if (currentAngle == 0*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x(),pivotPosition.y() + LENGTH);
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }
            if (currentAngle == 270*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x()-LENGTH,pivotPosition.y());
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }

        }
        else if (this.maxAngle == 90*(Math.PI/180)) {
            this.pivotPosition = new Vect(x,y);
            ROTATINGSYMBOL = '\\';
            if (currentAngle == 90*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x()+LENGTH,pivotPosition.y());
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }
            if (currentAngle == 0*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x(),pivotPosition.y()+LENGTH);
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }



        }
        else if (this.maxAngle == 180*(Math.PI/180)) {
            this.pivotPosition = new Vect(x,y+LENGTH);
            ROTATINGSYMBOL = '/';
            if (currentAngle == 180*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x(),pivotPosition.y() - LENGTH);
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }
            if (currentAngle == 90*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x()+LENGTH,pivotPosition.y());
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }
        }
        else { //this.maxAngle == 270*(Math.PI/180)
            this.pivotPosition = new Vect(x+LENGTH,y+LENGTH);
            ROTATINGSYMBOL = '\\';
            if (currentAngle == 180*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x(),pivotPosition.y() - LENGTH);
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }
            if (currentAngle == 270*(Math.PI/180)) {
                final Vect otherEnd = new Vect(pivotPosition.x()-LENGTH,pivotPosition.y());
                flipperShape = new LineSegment(pivotPosition, otherEnd);
            }
        }

        this.isRotating = false;
    }

    /**
     * @return String name of gadget
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * @return Set<Vect> of board tiles taken up by gadget
     */
    @Override
    public Set<Vect> getTiles() {
        Set<Vect> tiles = new HashSet<Vect>();
        Vect tile1;
        Vect tile2 = null;

        //find pivot
        if (this.maxAngle == 0) {
            tile1 = new Vect(pivotPosition.x()-1,pivotPosition.y());
            if (currentAngle == 0) {
                tile2 = new Vect(pivotPosition.x()-1,pivotPosition.y()+1);
            }
            if (currentAngle == 270*(Math.PI/180)) {
                tile2 = new Vect(pivotPosition.x()-LENGTH,pivotPosition.y());
            }
        }
        else if (this.maxAngle == 90*(Math.PI/180)) {
            tile1 = pivotPosition;
            if (currentAngle == 90*(Math.PI/180)) {
                tile2 = new Vect(pivotPosition.x()+1,pivotPosition.y());
            }
            if (currentAngle == 0) {
                tile2 = new Vect(pivotPosition.x(),pivotPosition.y()+1);
            }
        }
        else if (this.maxAngle == 180*(Math.PI/180)) {
            tile1 = new Vect(pivotPosition.x(),pivotPosition.y()-1);
            if (currentAngle == 180*(Math.PI/180)) {
                tile2 = new Vect(pivotPosition.x(),pivotPosition.y()-LENGTH);
            }
            if (currentAngle == 90*(Math.PI/180)) {
                tile2 = new Vect(pivotPosition.x()+1,pivotPosition.y()-1);
            }
        }
        else { //this.maxAngle == 270*(Math.PI/180)
            tile1 = new Vect(pivotPosition.x()-1,pivotPosition.y()-1);
            if (currentAngle == 270*(Math.PI/180)) {
                tile2 = new Vect(pivotPosition.x()-LENGTH,pivotPosition.y()-1);
            }
            if (currentAngle == 180*(Math.PI/180)) {
                tile2 = new Vect(pivotPosition.x()-1,pivotPosition.y()-LENGTH);
            }
        }


        if (tile2 == null) { //flipper's in da middle!
            final double otherEndX = pivotPosition.x() + LENGTH * Math.sin(currentAngle);
            final double otherEndY = pivotPosition.y() + LENGTH * Math.cos(currentAngle);

            tile2 = new Vect (Math.floor(otherEndX), Math.floor(otherEndY));
        }

        tiles.add(tile1);
        tiles.add(tile2);

        return tiles;
    }

    /**
     * @return char symbol that represents this gadget on a board
     */
    @Override
    public char getSymbol() {
        if (isRotating) {
            return ROTATINGSYMBOL;

        }
        if (this.currentAngle == 0 || this.currentAngle == 180*(Math.PI/180)) {
            return VERTICALSYMBOL;
        }
        else {
            return HORIZONTALSYMBOL;
        }       
    }

    /**
     * @return Vect pivot point of flipper
     */
    @Override
    public Vect getPosition() {
        return boundingBoxPosition;
    }


    private double getRotationalVelocity() {
        if (!isRotating) {
            return 0;
        }
        else { //is rotating
            if (isRotatingCounterclockwise) {
                return ROTATIONALSPEED;
            }
            else {// is not RotatingCounterclockwise
                return -1 * ROTATIONALSPEED;
            }
        }
    }


    @Override
    public double timeTillCollision(Ball ball) {
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        //calculates collision time to possibly rotating flipper
        if (isRotating) {
            double collisionTime = physics.Geometry.timeUntilRotatingWallCollision(
                    flipperShape,
                    pivotPosition,
                    getRotationalVelocity(),
                    ballShape,
                    ball.getVelocity());
            return collisionTime;
        } else {
            double collisionTime = physics.Geometry.timeUntilWallCollision(flipperShape, ballShape, ball.getVelocity());
            return collisionTime;
        }
    }

    /**
     * Simulates a collision with a flipper by updating the velocity of the
     * ball by the coefficient of reflection, 0.95.
     * 
     * Also takes into account any linear velocity of flipper motion when colliding 
     * with a ball and updating ball velocity.
     * 
     * @param timeBeforeCollision the time, in milliseconds, before collision between
     * the ball and the flipper
     * @param ball ball on board
     */
    @Override
    public void progressAndCollide(double amountOfTime, Ball ball) {
        progress(amountOfTime,0,0,0);
        ball.progressIgnoringPhysicalConstants(amountOfTime);

        //updates velocity based on linear velocity of flipper motion
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        Vect velocity;
        if (isRotating) {
            velocity = physics.Geometry.reflectRotatingWall(
                    flipperShape,
                    pivotPosition,
                    getRotationalVelocity(),
                    ballShape,
                    ball.getVelocity(),
                    0.95); }
        else {
            velocity = physics.Geometry.reflectWall(flipperShape, ball.getVelocity(), 0.95);
        }
        ball.setVelocity(velocity);
    }

    /**
     * Rotate the gadget 90*(Math.PI/180) degrees at a rate of 1080 degrees per second.
     * The left gadget rotates counter-clockwise, and the right gadget 
     * rotates clockwise.
     */
    @Override
    public void doAction() {
        isRotating = true;
    }

    /**
     * Progresses this gadget by the given amountOfTime (in milliseconds),
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
        //if flipper is mid-rotation
        if (isRotating) {
            this.currentAngle += getRotationalVelocity()*amountOfTime ;
            this.currentAngle = (this.currentAngle >= 2 * Math.PI) ? this.currentAngle - 2 * Math.PI : this.currentAngle;
            this.currentAngle = (this.currentAngle < 0) ? this.currentAngle + 2 * Math.PI : this.currentAngle;
            flipperShape = physics.Geometry.rotateAround(flipperShape, pivotPosition, new Angle(-getRotationalVelocity()*amountOfTime));
            //checks if done
            if (!isRotatingCounterclockwise && ((this.minAngle == 0 && this.currentAngle > 3 * Math.PI / 2) || (this.currentAngle < this.minAngle && this.minAngle != 0))) {
                double tmpmin = (minAngle == 0) ? 2*Math.PI : minAngle;
                double diff = Math.abs(this.currentAngle - tmpmin);
                flipperShape = physics.Geometry.rotateAround(flipperShape, pivotPosition, new Angle(diff));
                this.currentAngle += diff;
                this.currentAngle = (this.currentAngle >= 2 * Math.PI) ? this.currentAngle - 2 * Math.PI : this.currentAngle;
                this.currentAngle = (this.currentAngle < 0) ? this.currentAngle + 2 * Math.PI : this.currentAngle;
                isRotating = false;
                isRotatingCounterclockwise = !isRotatingCounterclockwise;
            } else if (isRotatingCounterclockwise && ((this.maxAngle == 0 && currentAngle < Math.PI / 2) || (this.maxAngle != 0 && currentAngle > maxAngle))) {
                double tmpmax = (maxAngle == 0) ? 2*Math.PI : maxAngle;
                double diff = Math.abs(this.maxAngle - this.currentAngle);
                flipperShape = physics.Geometry.rotateAround(flipperShape, pivotPosition, new Angle(-diff));
                this.currentAngle -= diff;
                this.currentAngle = (this.currentAngle >= 2 * Math.PI) ? this.currentAngle - 2 * Math.PI : this.currentAngle;
                this.currentAngle = (this.currentAngle < 0) ? this.currentAngle + 2 * Math.PI : this.currentAngle;
                isRotating = false;
                isRotatingCounterclockwise = !isRotatingCounterclockwise;
            }
        }

    }


}
