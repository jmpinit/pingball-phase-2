package game;

import java.util.HashSet;
import java.util.Set;

import client.Sprite;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import server.NetworkProtocol;
import server.NetworkProtocol.NetworkState;
import server.NetworkProtocol.NetworkState.Field;
import server.NetworkProtocol.NetworkState.FieldName;


/***
 * A Flipper is a rectangular gadget which
 * reflects balls during collisions
 * and is capable of swinging back and forth, tracing a 90 degree arc.
 * A Flipper's action is to complete 1 new 90 degree arc.
 * @author pkalluri
 *
 */
public class Flipper implements Gadget {
    //Details of all flippers
    /***
     * Length, in L
     */
    public static final int LENGTH = 2;
    /***
     * Rotational speed, in degrees/second
     */
    private static final double ROTATIONALSPEED = 1080;
    private final double RIGHTANGLE = 90;
    private static final char VERTICALSYMBOL = '|';
    private static final char HORIZONTALSYMBOL = '-';
    public static final int STATICUID = Sprite.Flipper.ID;
    
    //Details of this flipper
    private final int instanceUID;
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
        this.instanceUID = NetworkProtocol.getUID();
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
     * Converts angle in degrees clockwise from South
     * to angle in radians counterclockwise from East.
     * @param angleInDegrees
     */
    private double degCWFromSouthToRadCCWFromEast(double angleInDegrees) {
        return (360 - angleInDegrees - 90) * Math.PI/180;
    }
    

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getInstanceUID() {
        return instanceUID;
    }

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



    /***
     * Get rotational velocity, in degrees/sec CCW.
     * @return otational velocity, in degrees/sec CCW.
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
    public double getTimeTillCollision(Ball ball) {
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
     * If not already flipping, tells the flipper to begin flipping at the next time step,
     * i.e. tracing a 90 degree arc.
     */
    @Override
    public void doAction() {
        currentlyRotating = true;
    }

    
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
    }
    
    
    /***
     * Check if angle is equivalent to the direction West.
     * @param angleInDeg angle in degrees clockwise from South
     * @return true iff angle is equivalent to the direction West.
     */
    private boolean isWest(double angleInDeg) {
        return angleInDeg == 90;
    }
    
    /***
     * Check if angle is equivalent to the direction North.
     * @param angleInDeg angle in degrees clockwise from South
     * @return true iff angle is equivalent to the direction North.
     */
    private boolean isNorth(double angleInDeg) {
        return angleInDeg == 180;
    }
    
    /***
     * Check if angle is equivalent to the direction South.
     * @param angleInDeg angle in degrees clockwise from South
     * @return true iff angle is equivalent to the direction South.
     */
    private boolean isSouth(double angleInDeg) {
        return angleInDeg == 0 || angleInDeg == 360;
    }
    
    /***
     * Check if angle is equivalent to the direction East.
     * @param angleInDeg angle in degrees clockwise from South
     * @return true iff angle is equivalent to the direction East.
     */
    private boolean isEast(double angleInDeg) {
        return angleInDeg == -90 || angleInDeg == 270;
    }


    @Override
    public NetworkState getState() {
        Field[] fields = new Field[] {
                new Field(FieldName.X, (long)upperLeftCornerOfBoundingBox.x()), // TODO more precision (multiply by constant)
                new Field(FieldName.Y, (long)upperLeftCornerOfBoundingBox.y()),
                new Field(FieldName.ANGLE, (long)currentAngle)
        };
        
        return new NetworkState(fields);
    }
    
    @Override
    public int getStaticUID() {
        return STATICUID;
    }

}
