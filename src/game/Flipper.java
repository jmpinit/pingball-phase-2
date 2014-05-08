package game;

import java.util.HashSet;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;


public class Flipper implements Gadget {
    //Details of all flippers
    /***
     * Length, in L
     */
    private static final int LENGTH = 2;
    /***
     * Rotational speed, in degrees/second
     */
    private static final double ROTATIONALSPEED = 1080;
    private final double RIGHTANGLE = 90;
    private static final char VERTICALSYMBOL = '|';
    private static final char HORIZONTALSYMBOL = '-';

    
    //Details of this flipper
    private final String name;
    private final Vect upperLeftCornerOfBoundingBox;
    private final Vect pivotPosition; 
    /***
     * Angles measured clockwise from south in degrees
     */
    private final double maxAngle, minAngle;
    private final char ROTATINGSYMBOL;


    //Current state
    private LineSegment flipperLine;
    /***
     * Angles measured clockwise from south in degrees
     */
    private double currentAngle;
    private boolean willRotateCounterClockwiseNext;
    private boolean currentlyRotating;



    public Flipper(String leftOrRight, String name, double x, double y, double orientation) {
        this.name = name;
        this.upperLeftCornerOfBoundingBox = new Vect(x,y);
        
        if (leftOrRight.equals("left")) {
            //Left flipper
            this.pivotPosition = new Vect(this.upperLeftCornerOfBoundingBox.x(), this.upperLeftCornerOfBoundingBox.y());
            this.currentAngle = orientation;
            this.maxAngle = this.currentAngle;
            this.minAngle = (this.currentAngle - RIGHTANGLE);
            this.willRotateCounterClockwiseNext = true;
        }
        else { 
            //Right flipper
            this.pivotPosition = new Vect(this.upperLeftCornerOfBoundingBox.x() + LENGTH, this.upperLeftCornerOfBoundingBox.y());
            this.currentAngle = orientation;
            this.minAngle = this.currentAngle;
            this.maxAngle = (this.currentAngle + RIGHTANGLE);
            this.willRotateCounterClockwiseNext = false;
        }
        

        //Determine line representation
        final Vect otherEnd = new Vect( pivotPosition.x() + Math.cos(degCWFromSouthToRadCCWFromEast(currentAngle)) ,
                                        pivotPosition.y() + -1*Math.sin(degCWFromSouthToRadCCWFromEast(currentAngle)) );
        this.flipperLine = new LineSegment(pivotPosition, otherEnd);

        
        //Determine rotating symbol
        if (isWest(this.maxAngle)) { //SOUTHWEST QUAD
            this.ROTATINGSYMBOL = '/';
        }
        else if (isNorth(this.maxAngle)) { //NORTHWEST QUAD
            this.ROTATINGSYMBOL = '\\';

        }
        else if (isEast(this.maxAngle)) { //NORTHEAST QUAD
            this.ROTATINGSYMBOL = '/';
        }
        else { //SOUTHEAST QUAD
            this.ROTATINGSYMBOL = '\\';
        }

        this.currentlyRotating = false;
    }
    

    /***
     * Converts angle in degrees to angle in radians.
     * @param angleInDegrees
     */
    private double degCWFromSouthToRadCCWFromEast(double angleInDegrees) {
        return (360 - angleInDegrees -90) * Math.PI/180;
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
        Vect tile1 = null;
        Vect tile2 = null;

        //Determine tile1
        if (isWest(this.maxAngle)) { //SOUTHWEST QUAD
            tile1 = new Vect(this.pivotPosition.x()-1,this.pivotPosition.y());
            if (isWest(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x() - LENGTH,
                                 this.pivotPosition.y()
                                 );
            }
            else if (isSouth(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x() -1,
                                 this.pivotPosition.y() + LENGTH - 1
                                 );
            }
        }
        else if (isNorth(this.maxAngle)) { //NORTHWEST QUAD
            tile1 = new Vect(this.pivotPosition.x()-1,this.pivotPosition.y()-1);
            if (isWest(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x() - LENGTH,
                                 this.pivotPosition.y() - 1
                                 );
            }
            else if (isNorth(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x() -1,
                                 this.pivotPosition.y() - LENGTH
                                 );
            }
        }
        else if (isEast(this.maxAngle)) { //NORTHEAST QUAD
            tile1 = new Vect(this.pivotPosition.x(),this.pivotPosition.y()-1);
            if (isNorth(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x(),
                                 this.pivotPosition.y() - LENGTH
                                 );
            }
            else if (isEast(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x() + LENGTH - 1,
                                 this.pivotPosition.y() -1
                                 );
            }
        }
        else { //SOUTHEAST QUAD
            tile1 = new Vect(this.pivotPosition.x(),this.pivotPosition.y());
            if (isSouth(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x(),
                                 this.pivotPosition.y() + LENGTH - 1
                                 );
            }
            else if (isEast(this.currentAngle)) {
                tile2 = new Vect(this.pivotPosition.x() + LENGTH - 1,
                                 this.pivotPosition.y()
                                 );
            }
        }
        
        //if this is in the middle of a quadrant
        if (tile2==null) {
            Vect otherEnd = new Vect(
                    pivotPosition.x() + Math.cos(degCWFromSouthToRadCCWFromEast(currentAngle)) ,
                    pivotPosition.y() + -1*Math.sin(degCWFromSouthToRadCCWFromEast(currentAngle))
                    );
            tile2 = new Vect(
                    Math.floor(otherEnd.x()),
                    Math.floor(otherEnd.y())
                    );
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
        if (currentlyRotating) {
            return ROTATINGSYMBOL;
        }
        if (isSouth(this.currentAngle) || isNorth(this.currentAngle)) {
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
        return upperLeftCornerOfBoundingBox;
    }


    /***
     * Get rotational speed in degrees/sec CCW.
     * @return
     */
    private double getRotationalVelocity() {
        if (!currentlyRotating) {
            return 0;
        }
        else { //is rotating
            if (willRotateCounterClockwiseNext) {
                return ROTATIONALSPEED;
            }
            else {
                return -1 * ROTATIONALSPEED;
            }
        }
    }


    @Override
    public double timeTillCollision(Ball ball) {
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        //calculates collision time to possibly rotating flipper
        if (currentlyRotating) {
            return physics.Geometry.timeUntilRotatingWallCollision(
                    flipperLine,
                    pivotPosition,
                    getRotationalVelocity() * Math.PI/180,
                    ballShape,
                    ball.getVelocity());
        } else {
            return physics.Geometry.timeUntilWallCollision(flipperLine, ballShape, ball.getVelocity());
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
        ball.progressIgnoringPhysicalConstants(amountOfTime);
        progress(amountOfTime,0,0,0);

        //updates velocity based on linear velocity of flipper motion
        final Circle ballShape = new Circle(ball.getPosition(), ball.getRadius());
        Vect velocity;
        if (currentlyRotating) {
            velocity = physics.Geometry.reflectRotatingWall(
                    flipperLine,
                    pivotPosition,
                    getRotationalVelocity() * Math.PI/180,
                    ballShape,
                    ball.getVelocity(),
                    0.95); }
        else {
            velocity = physics.Geometry.reflectWall(flipperLine, ball.getVelocity(), 0.95);
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
        System.out.println(getName() + "CALLED TO ACTION!");
        currentlyRotating = true;
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
    public void progress(double amountOfTime, double gravity, double mu, double mu2) {
        //if flipper is mid-rotation
        if (currentlyRotating) {
            //update current angle
            currentAngle -= getRotationalVelocity()*amountOfTime;       
            
            System.out.println(getName() + "before correction" + currentAngle);
            
            //if done, reupdate current angle
            if (currentAngle > maxAngle) {
                currentAngle = maxAngle;
                willRotateCounterClockwiseNext = !willRotateCounterClockwiseNext;
                currentlyRotating = false;
            }
            else if (currentAngle < minAngle ) {
                currentAngle = minAngle;
                willRotateCounterClockwiseNext = !willRotateCounterClockwiseNext;
                currentlyRotating = false;
            }
               
            //update flipper line to match
            final Vect otherEnd = new Vect( pivotPosition.x() + Math.cos(degCWFromSouthToRadCCWFromEast(currentAngle)) ,
                    pivotPosition.y() + -1*Math.sin(degCWFromSouthToRadCCWFromEast(currentAngle)) );
            this.flipperLine = new LineSegment(pivotPosition, otherEnd);  
            
        }
        System.out.println(getName() + currentAngle);

    }
    
    
    private boolean isWest(double angleInDeg) {
        return angleInDeg == 90;
    }
    
    private boolean isNorth(double angleInDeg) {
        return angleInDeg == 180;
    }
    
    private boolean isSouth(double angleInDeg) {
        return angleInDeg == 0 || angleInDeg == 360;
    }
    
    private boolean isEast(double angleInDeg) {
        return angleInDeg == -90 || angleInDeg == 270;
    }


}
