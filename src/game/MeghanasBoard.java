package game;



import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import physics.Vect;


/***
 * A board is a 20Lx20L pingball playing area with
 * a name that is distinct from any other board currently being played
 * specific physical constants
 * zero or more gadgets
 * zero or more balls that move at each step, possibly moving into or out of the board
 * 4 walls that can each become transparent if joined to other boards or concrete if isolated.
 * a ball queue of balls waiting to be placed in their appropriate location on the board.
 * 
 * Board is not guaranteed to be threadsafe, but join and disjoin ????
 * @author pkalluri
 */
public class MeghanasBoard {
    //CONSTANTS
    private static final int SIDELENGTH = 20;
    public static final double DEFAULTGRAVITY = 25.0;
    public static final double DEFAULTMU1 = 0.025;
    public static final double DEFAULTMU2 = 0.025;

    //IMMUTABLE ATTRIBUTES:
    private final String name; //name of board
    private final Double gravity; //coefficient of acceleration downwards due to gravity force, in units L/(sec^2)
    private final Double mu; //coefficient of acceleration against direction of motion, due to a friction force acting per second
    private final Double mu2; //coefficient of acceleration against direction of motion, due to a friction force acting per L
    private final Map<Gadget, Set<Gadget>> gadgetsToEffects;//mapping of each gadget on the board to any *other* gadgets that react when the original gadget is triggered
    //Note: It is the trigger-effect relationships between gadgets that are immutable and are maintained by this map. The gadgets themselves may be mutable.
    private final double stepSize; //step size, in seconds
    private Gadget [][] gadgetBoard; //Has gadgets in their given (x,y) coordinates
    
    
    //MUTABLE ATTRIBUTES:
    private final Set<Ball> balls; //all balls currently on this board
    private final BlockingQueue<Ball> ballQueue;//all balls queued to be placed on this board
    //  4 walls of this board, which can be transparent or concrete
    private Wall topWall;
    private Wall bottomWall;
    private Wall leftWall;
    private Wall rightWall;
    private Set<Wall> walls;
    //  4 possible attached boards
    private MeghanasBoard topBoard;
    private MeghanasBoard bottomBoard;
    private MeghanasBoard leftBoard;
    private MeghanasBoard rightBoard;


    /***
     * Creates a new board from the given parameters
     * 
     * @param name name of board
     * @param gravity coefficient of gravity
     * @param mu coefficient of friction with respect to time
     * @param mu2 coefficient of friction with respect to distance
     * @param gadgetsToEffects a map of each gadget to a set of all *other* gadgets that act when it is triggered
     * @param stepSize length of this board's step size in seconds
     * @param balls a set of balls that start on the board
     */
    public MeghanasBoard(String name, Double gravity, Double mu, Double mu2, Map<Gadget, Set<Gadget>> gadgetsToEffects, double stepSizeInSeconds, Set<Ball> startingBalls) {
        //CONSTRUCTED FROM GIVEN PARAMETERS
        this.name = name;
        this.gravity = gravity;
        this.mu = mu;
        this.mu2 = mu2;
        this.gadgetsToEffects = gadgetsToEffects;
        this.stepSize = stepSizeInSeconds;
        this.balls = startingBalls;

        //CONSTRUCTED AS ALWAYS
        this.ballQueue = new LinkedBlockingQueue<Ball>();
        this.gadgetBoard = new Gadget[SIDELENGTH][SIDELENGTH];
        this.createGadgetBoard();
        //walls:
        this.constructWalls();
        //boards:
        this.topBoard = null;
        this.bottomBoard = null;
        this.leftBoard = null;
        this.rightBoard = null;
    }
    


/******** ACCESSOR FUNCTIONS ********/

    /***
     * Returns name of board.
     * @return name of this board
     */
    public String getName() {
        return name;
    }

    /***
     * Returns a string representation of the board in its current state.
     * @return a string representation of the board in its current state.
     */
    @Override
    public String toString() {
        char[][] arrayRep = new char[SIDELENGTH+1+1][SIDELENGTH+1+1]; //square array representation of 20x20 board + walls

        /**
        //fill with spaces
        for (int i = 0; i < arrayRep.length; i ++) {
            for (int j = 0; j < arrayRep.length; j ++) {
                arrayRep[i][j] = ' ';
            }
        }
         **/
        
        //Fill inner board
        for (int i = 0; i < SIDELENGTH; i ++) {
            for (int j = 0; j < SIDELENGTH; j ++) {
                arrayRep[i+1][j+1] = this.stringifyGadgetAt(i, j);
            }
        }
        
        //overwrite with walls
        for (int i = 0; i < arrayRep.length; i ++) {
            arrayRep[0][i] = '.'; //top wall
            arrayRep[arrayRep.length-1][i] = '.'; //bottom wall
            arrayRep[i][0] = '.'; //left wall
            arrayRep[i][arrayRep.length-1] = '.'; //right wall
        }

        //overwrite with any currently joined boards' names
        if (topBoard != null) {
            String nameToWrite = topBoard.getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[0][positionToCopyTo] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }
        if (bottomBoard != null) {
            String nameToWrite = bottomBoard.getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[arrayRep.length-1][positionToCopyTo] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }
        if (leftBoard != null) {
            String nameToWrite = leftBoard.getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[positionToCopyTo][0] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }
        if (rightBoard != null) {
            String nameToWrite = rightBoard.getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[positionToCopyTo][arrayRep.length-1] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }
        
        /**overwrite with gadget locations
        for (GamePiece gadget : getGadgets() ) {
            for (Vect tile : gadget.getTiles()) {
                arrayRep[(int) tile.y() + 1][(int) (tile.x() + 1)] = gadget.getSymbol();
            }
        }
         **/
        
        //overwrite with ball locations
        for (Ball ball : balls ) {
            for (Vect tile : ball.getTiles()) {
                double x = tile.x() + 1;
                double y = tile.y() + 1;
                
                if(y >= 0 && y < arrayRep.length && x >=0 && x < arrayRep[0].length) {
                    arrayRep[(int)x][(int)y] = ball.getSymbol();
                }
            }
        }

        //make string representation
        String stringRep = "";
        for (int j = 0; j < arrayRep.length; j ++) {
            for (int i = 0; i < arrayRep.length; i++ ) {
                stringRep += arrayRep[j][i];
            }
            stringRep += '\n';
        }
        return stringRep;
    }

    /***
     * Gets a set of this board's gadgets.
     * @return this board's gadgets
     */
    private Set<Gadget> getGadgets() {
    	return gadgetsToEffects.keySet();
    }
    
    
/******** END ACCESSOR FUNCTIONS ********/

    
    /***
     * Progresses the board by one time step,
     * adding any queued balls,
     * then progressing,
     * enacting collisions as they occur,
     * updating the gadgets that act in response to a collided gadget's trigger,
     * and queuing balls that have moved off the board in the ball queue of their new board,
     * until the time step has ended.
     * 
     * @throws InterruptedException 
     */
    public void step() throws InterruptedException{
        addQueuedBalls();
        double timeTillEndOfStep = stepSize; //Currently at beginning of time step

        while (timeTillEndOfStep > 0) {
            //FIND TIME TO FAST FORWARD THROUGH
            double timeToFastForwardThrough = timeTillEndOfStep; //If no collisions is found, next update is to the end of this time step
            //Find earliest collision that takes place in this time step, if any
            boolean foundCollision = false;
            Ball collidingBall = null;
            GamePiece collidingGamePiece = null;
            for (Ball ball : balls) {
                if (ball.isActive()) {
                    //Look for collisions with other balls
                    for (Ball otherBall : balls) {
                        double timeTillCollision = otherBall.timeTillCollision(ball);
                        if (otherBall.isActive() &&
                                ball != otherBall &&
                                timeTillCollision< timeToFastForwardThrough) {
                            foundCollision = true;
                            collidingBall = ball;
                            collidingGamePiece = otherBall;
                            timeToFastForwardThrough = timeTillCollision;
                                }
                    }
                    //Look for collisions with gadgets
                    for (GamePiece gadget : getGadgets() ) { //check all gadgets
                        double timeTillCollision = gadget.timeTillCollision(ball);
                        if (timeTillCollision<timeToFastForwardThrough) {
                            //Next update will be this collision
                            foundCollision = true;
                            collidingBall = ball;
                            collidingGamePiece = gadget;
                            timeToFastForwardThrough = timeTillCollision;
                        }
                    }
                    //Look for collisions with walls
                    for (Wall wall : walls ) { //check all walls
                        double timeTillCollision = wall.timeTillCollision(ball);
                        if (timeTillCollision<timeToFastForwardThrough) {
                            //Next update will be this collision
                            foundCollision = true;
                            collidingBall = ball;
                            collidingGamePiece = wall;
                            timeToFastForwardThrough = timeTillCollision;
                        }
                    }
                }
            }

            //FASTFORWARD EVERYTHING THAT IS NOT COLLIDING, USING PHYSICAL CONSTANTS
            //  progress active balls that are not colliding
            Set<Ball> ballsToDeleteFromThisBoard = new HashSet<Ball>();
            for (Ball ball : balls) { 
                if (ball.isActive() && ball!=collidingBall && ball!=collidingGamePiece) {
                    ball.progress(timeToFastForwardThrough, gravity, mu, mu2);
                    //handle balls that have gone off this board
                    SideBoard sideboard = getBoardContaining(ball);
                    if (sideboard != SideBoard.NONE) {
                        ballsToDeleteFromThisBoard.add(ball); //prep it to be deleted from this board
                        queueBallOnCorrectBoard(ball); //queue it on the correct board
                    }
                }
            }
            balls.removeAll(ballsToDeleteFromThisBoard);
            //  progress gadgets that are not colliding
            for (GamePiece gadget : getGadgets() ) { 
                if (gadget!=collidingGamePiece) {
                    gadget.progress(timeToFastForwardThrough, gravity, mu, mu2);
                }
            }
            boolean me = false;

            //FASTFORWARD AND COLLIDE ALL THINGS THAT ARE COLLIDING, IF ANY, IGNORING PHYSICAL CONSTANTS
            if (foundCollision) {
                collidingGamePiece.progressAndCollide(timeToFastForwardThrough, collidingBall);

                if (getGadgets().contains(collidingGamePiece)) { //if it's a board gadget
                    GamePiece collidingGadget = collidingGamePiece;
                    //do other actions caused by the collision
                    for (Gadget gadgetToAct : gadgetsToEffects.get(collidingGadget)) {
                        me = true;
                        gadgetToAct.doAction();
                    }
                }
            }

            timeTillEndOfStep -= timeToFastForwardThrough;
        }//repeat until at end of time step
    }    


    /***
     * Get board containing this ball's origin, either this board or one of the 4 attached boards.
     * @param ball
     * @return Board that contains this ball, either this or one of the 4 attached boards
     */
    private SideBoard getBoardContaining(Ball ball) {

        if (ball.getPosition().x() < 0 && leftBoard != null) { //to left of this board
            return SideBoard.LEFT;
        }
        if (ball.getPosition().x() > SIDELENGTH && rightBoard != null) { //to right of this board
            return SideBoard.RIGHT;
        }
        if (ball.getPosition().y() < 0 && topBoard != null) { //above this board
            return SideBoard.TOP;
        }
        if (ball.getPosition().y() > SIDELENGTH && bottomBoard != null) { //below this board
            return SideBoard.BOTTOM;
        }
        return SideBoard.NONE; //on this board
    }


    /***
     * Adds ball to board's ball queue.
     */
    private void queueBallOnCorrectBoard(Ball ball) throws InterruptedException {
        SideBoard sideboard = getBoardContaining(ball);
        MeghanasBoard thisBallsBoard = null;
        if (sideboard == SideBoard.TOP) {
            ball.setPosition(new Vect (ball.getPosition().x(), ball.getPosition().y()+SIDELENGTH)   );
            thisBallsBoard = topBoard;
        }
        if (sideboard == SideBoard.BOTTOM) {
            ball.setPosition(   new Vect(ball.getPosition().x(), ball.getPosition().y()-SIDELENGTH)    );
            thisBallsBoard = bottomBoard;
        }
        if (sideboard == SideBoard.LEFT) {
            ball.setPosition(   new Vect(ball.getPosition().x()+SIDELENGTH, ball.getPosition().y())    );
            thisBallsBoard = leftBoard;
        }
        if (sideboard == SideBoard.RIGHT) {
            ball.setPosition(   new Vect(ball.getPosition().x()-SIDELENGTH, ball.getPosition().y())    );
            thisBallsBoard = rightBoard;
        }
        thisBallsBoard.ballQueue.put(ball);
    }

    /***
     * Places all queued balls in their correct location on this board.
     * Cannot be called while board's attribute balls is being edited.
     */
    private void addQueuedBalls() {
        ballQueue.drainTo(balls);
        //Note: drainTo is only guaranteed to work if balls is not being edited elsewhere
    }



/******** OUTER WALL FUNCTIONS ********/    
    
    /***
     * Joins left wall with given board,
     * and updates the String representation of this board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the left hand side of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinLeftWallTo(MeghanasBoard otherBoard) {
        if (leftBoard != null) { 
            leftBoard.disjoinRight();
        }
        leftBoard = otherBoard;
        leftWall.setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }

    /***
     * Joins right wall with given board,
     * and updates the String representation of the board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the right hand side of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinRightWallTo(MeghanasBoard otherBoard) {
        if (rightBoard != null) { 
            rightBoard.disjoinLeft();
        }
        rightBoard = otherBoard;
        rightWall.setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }

    /***
     * Joins top wall with given board,
     * and updates the String representation of the board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the top of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinTopWallTo(MeghanasBoard otherBoard) {
        if (topBoard != null) { 
            topBoard.disjoinBottom();
        }
        topBoard = otherBoard;
        topWall.setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }

    /***
     * Joins bottom wall with given board,
     * and updates the String representation of the board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the bottom of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinBottomWallTo(MeghanasBoard otherBoard) {
        if (bottomBoard != null) { 
            bottomBoard.disjoinTop();
        }
        bottomBoard = otherBoard;
        bottomWall.setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }


    /***
     * Disjoins all connections to (and knowledge of) the given board
     * 
     * @param board board to disjoin from this
     */
    public void disjoin(MeghanasBoard board) {
        if (board == topBoard) {
            disjoinTop();
        }
        if (board == bottomBoard) {
            disjoinBottom();
        }
        if (board == leftBoard) {
            disjoinLeft();
        }
        if (board == rightBoard) {
            disjoinRight();
        }
    }

    /***
     * Disjoins all connections to (and knowledge of) the current left board, if any
     */
    public void disjoinLeft() {
        leftBoard = null;
        leftWall.setTransparency(false);
    }


    /***
     * Disjoins all connections to (and knowledge of) the current right board, if any
     */
    public void disjoinRight() {
        rightBoard = null;
        rightWall.setTransparency(false);
    }

    /***
     * Disjoins all connections to (and knowledge of) the current top board, if any
     */
    public void disjoinTop() {
        topBoard = null;
        topWall.setTransparency(false);
    }

    /***
     * Disjoins all connections to (and knowledge of) the current bottom board, if any
     */
    public void disjoinBottom() {
        bottomBoard = null;
        bottomWall.setTransparency(false);
    }

    public MeghanasBoard getTopBoard() {
        return topBoard;
    }

    public MeghanasBoard getBottomBoard() {
        return bottomBoard;
    }

    public MeghanasBoard getRightBoard() {
        return rightBoard;
    }

    public MeghanasBoard getLeftBoard() {
        return leftBoard;
    }


/********  END OUTER WALL FUNCTIONS ********/    

    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((balls == null) ? 0 : balls.hashCode());
        result = prime
            * result
            + ((gadgetsToEffects == null) ? 0 : gadgetsToEffects.hashCode());
        result = prime * result + ((gravity == null) ? 0 : gravity.hashCode());
        result = prime * result + ((mu == null) ? 0 : mu.hashCode());
        result = prime * result + ((mu2 == null) ? 0 : mu2.hashCode());
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
        MeghanasBoard other = (MeghanasBoard) obj;
        return (this+"").equals(other+"");
    }
    
/******** HELPER FUNCTIONS ********/
    
    private void constructWalls() {
        this.topWall = new Wall( new Vect(0,0),new Vect(SIDELENGTH,0)  );
        this.bottomWall = new Wall( new Vect(0,SIDELENGTH),new Vect(SIDELENGTH,SIDELENGTH)  );
        this.leftWall = new Wall( new Vect(0,0),new Vect(0,SIDELENGTH)  );
        this.rightWall = new Wall( new Vect(SIDELENGTH,0),new Vect(SIDELENGTH,SIDELENGTH)  );
        this.walls = new HashSet<Wall>();
        walls.add(topWall);
        walls.add(bottomWall);
        walls.add(leftWall);
        walls.add(rightWall);
    }
    
    private void createGadgetBoard() {
    	for (GamePiece gadget : getGadgets() ) {
    		for (Vect tile : gadget.getTiles()) {
    			gadgetBoard[(int) tile.y()][(int) tile.x()] = (Gadget) gadget;

    		}
    	}
    }
    
    /**
     * Returns a symbol representation of gadget at specified coordinates
     * @param x, x-coordinate to look for gadget in
     * @param y, y-coordinate to look for gadget in
     * @return gadget at coordinates (x,y) or null otherwise
     */
    public char getGadgetSymbolAt(int x, int y) {
    	if (x >= 0 && x < gadgetBoard.length && y >= 0 && y < gadgetBoard.length) {
    		if (gadgetBoard[y][x] != null) {
    			//NOTE: Coordinates are switched since they're switched when inputted
    			return gadgetBoard[y][x].getSymbol();
    		}
    	}
    	return ' ';
    }

    /**
     * Returns string representation of whatever element exists at given coordinates
     * NOTE: Does not look for balls or outer walls.
     * @param x, x-coordinate to look for element in
     * @param y, y-coordinate to look for element in
     * @return string representation of coordinates (x,y).
     */
    private char stringifyGadgetAt(int x, int y) {
    	if (x >= 0 && x < gadgetBoard.length && y >= 0 && y < gadgetBoard.length) {
    		Gadget g = gadgetBoard[x][y];
    		if (g != null) {
    			if (y==2 && x ==11 )
    			{
    				System.out.println(g.getSymbol());
    			}
    			return g.getSymbol();
    		}
    	}
    	return ' ';
    }

/******** END HELPER FUNCTIONS ********/




}
