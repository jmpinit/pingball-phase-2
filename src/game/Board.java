package game;



import java.util.HashMap;
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
 * Board is not gauranteed to be threadsafe, but join and disjoin ????
 * @author pkalluri
 */
public class Board {
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

    //MUTABLE ATTRIBUTES:
    private final Set<Ball> balls; //all balls currently on this board
    private final BlockingQueue<Ball> ballQueue;//all balls queued to be placed on this board
    private Map<Direction, Wall> walls;     //  4 walls of this board, which can be transparent or concrete
    private Map<Direction,Board> connectedBoards;     //  4 possible attached boards


    
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
    public Board(String name, Double gravity, Double mu, Double mu2, Map<Gadget, Set<Gadget>> gadgetsToEffects, double stepSizeInSeconds, Set<Ball> startingBalls) {
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
            //walls:
        this.walls = new HashMap<Direction,Wall> ();
        this.walls.put(Direction.UP, new Wall( new Vect(0,0),new Vect(SIDELENGTH,0)  )  );
        this.walls.put(Direction.DOWN, new Wall( new Vect(0,SIDELENGTH),new Vect(SIDELENGTH,SIDELENGTH)  )  );
        this.walls.put(Direction.LEFT, new Wall( new Vect(0,0),new Vect(0,SIDELENGTH)  )    );
        this.walls.put(Direction.RIGHT, new Wall( new Vect(SIDELENGTH,0),new Vect(SIDELENGTH,SIDELENGTH)  ) );
            //boards:
        this.connectedBoards = new HashMap<Direction,Board>();
        this.connectedBoards.put(Direction.UP, null); //the board in the up direction
        this.connectedBoards.put(Direction.DOWN, null); //in the down direction
        this.connectedBoards.put(Direction.LEFT, null); //in the left direction
        this.connectedBoards.put(Direction.RIGHT, null); //in the right direction
    }
    
    
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
    public String toString(){
        char[][] arrayRep = new char[SIDELENGTH+1+1][SIDELENGTH+1+1]; //square array represenation of 20x20 board + walls
        
        //fill with spaces
        for (int i = 0; i < arrayRep.length; i ++) {
            for (int j = 0; j < arrayRep.length; j ++) {
                arrayRep[i][j] = ' ';
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
        if (connectedBoards.get(Direction.UP) != null) {
            String nameToWrite = connectedBoards.get(Direction.UP).getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[0][positionToCopyTo] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }
        if (connectedBoards.get(Direction.DOWN) != null) {
            String nameToWrite = connectedBoards.get(Direction.DOWN).getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[arrayRep.length-1][positionToCopyTo] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }
        if (connectedBoards.get(Direction.LEFT) != null) {
            String nameToWrite = connectedBoards.get(Direction.LEFT).getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[positionToCopyTo][0] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }
        if (connectedBoards.get(Direction.RIGHT) != null) {
            String nameToWrite = connectedBoards.get(Direction.RIGHT).getName();
            int nextIndexToCopyFromName = 0;
            while (nextIndexToCopyFromName<nameToWrite.length() && nextIndexToCopyFromName<arrayRep.length-2) {
                int positionToCopyTo = nextIndexToCopyFromName+1;//leave one space for one '.' before starting the copying
                arrayRep[positionToCopyTo][arrayRep.length-1] = nameToWrite.charAt(nextIndexToCopyFromName);
                nextIndexToCopyFromName++;
            }
        }

        //overwrite with gadget locations
        for (GamePiece gadget : getGadgets() ) {
            for (Vect tile : gadget.getTiles()) {
                if (tile == null) System.out.println(gadget+"");
                arrayRep[(int) tile.y() + 1][(int) (tile.x() + 1)] = gadget.getSymbol();
            }
        }
        
        //overwrite with ball locations
        for (Ball ball : balls ) {
            for (Vect tile : ball.getTiles()) {
                arrayRep[(int) tile.y() + 1][(int) (tile.x() + 1)] = ball.getSymbol();
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
                if (ball.isActive()){
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
                    for (Wall wall : walls.values() ) { //check all walls
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
                    Direction sideboard = getBoardContaining(ball);
                    if (sideboard != Direction.NONE) {
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
            
            //FASTFORWARD AND COLLIDE ALL THINGS THAT ARE COLLIDING, IF ANY, IGNORING PHYSICAL CONSTANTS
            if (foundCollision) {
                collidingGamePiece.progressAndCollide(timeToFastForwardThrough, collidingBall);
                
                if (getGadgets().contains(collidingGamePiece)) { //if it's a board gadget
                    //do other actions caused by the collision
                    for (Gadget gadgetToAct : gadgetsToEffects.get(collidingGamePiece)){
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
    private Direction getBoardContaining(Ball ball) {
    	
        if (ball.getPosition().x() < 0 && connectedBoards.get(Direction.LEFT) != null) { //to left of this board
            return Direction.LEFT;
        }
        if (ball.getPosition().x() > SIDELENGTH && connectedBoards.get(Direction.RIGHT) != null) { //to right of this board
            return Direction.RIGHT;
        }
        if (ball.getPosition().y() < 0 && connectedBoards.get(Direction.UP) != null) { //above this board
            return Direction.UP;
        }
        if (ball.getPosition().y() > SIDELENGTH && connectedBoards.get(Direction.DOWN) != null) { //below this board
            return Direction.DOWN;
        }
        return Direction.NONE; //on this board
    }


    /***
     * Adds ball to board's ball queue.
     */
    private void queueBallOnCorrectBoard(Ball ball) throws InterruptedException {
        
        Direction sideboard = getBoardContaining(ball);
        Board thisBallsBoard = null;
        if (sideboard == Direction.UP) {
            ball.setPosition(new Vect (ball.getPosition().x(), ball.getPosition().y()+SIDELENGTH)   );
            thisBallsBoard = connectedBoards.get(Direction.UP);
        }
        if (sideboard == Direction.DOWN) {
            ball.setPosition(   new Vect(ball.getPosition().x(), ball.getPosition().y()-SIDELENGTH)    );
            thisBallsBoard = connectedBoards.get(Direction.DOWN);
        }
        if (sideboard == Direction.LEFT) {
            ball.setPosition(   new Vect(ball.getPosition().x()+SIDELENGTH, ball.getPosition().y())    );
            thisBallsBoard = connectedBoards.get(Direction.LEFT);
        }
        if (sideboard == Direction.RIGHT) {
            ball.setPosition(   new Vect(ball.getPosition().x()-SIDELENGTH, ball.getPosition().y())    );
            thisBallsBoard = connectedBoards.get(Direction.RIGHT);
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
    
    /***
     * Gets a set of this board's gadgets.
     * @return this board's gadgets
     */
    private Set<Gadget> getGadgets(){
        return gadgetsToEffects.keySet();
    }
    
    /***
     * Joins left wall with given board,
     * and updates the String representation of this board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the left hand side of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinLeftWallTo(Board otherBoard){
        
    	if (connectedBoards.get(Direction.LEFT) != null) { 
    		connectedBoards.get(Direction.LEFT).disjoinRight();
    	}
        connectedBoards.put(Direction.LEFT, otherBoard);
        walls.get(Direction.LEFT).setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }
    
    /***
     * Joins right wall with given board,
     * and updates the String representation of the board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the right hand side of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinRightWallTo(Board otherBoard){
    	if (connectedBoards.get(Direction.RIGHT) != null) { 
    		connectedBoards.get(Direction.RIGHT).disjoinLeft();
    	}
        connectedBoards.put(Direction.RIGHT, otherBoard);
        walls.get(Direction.RIGHT).setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }
    
    /***
     * Joins top wall with given board,
     * and updates the String representation of the board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the top of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinTopWallTo(Board otherBoard){
    	if (connectedBoards.get(Direction.UP) != null) { 
    		connectedBoards.get(Direction.UP).disjoinBottom();
    	}
        connectedBoards.put(Direction.UP, otherBoard);
        walls.get(Direction.UP).setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }
    
    /***
     * Joins bottom wall with given board,
     * and updates the String representation of the board to reflect the join,
     * such that balls can now move to the given board's ball queue by
     * exiting the bottom of this board.
     * @param otherBoard the board which is being joined
     */
    public void joinBottomWallTo(Board otherBoard){
    	if (connectedBoards.get(Direction.DOWN) != null) { 
    		connectedBoards.get(Direction.DOWN).disjoinTop();
    	}
        connectedBoards.put(Direction.DOWN, otherBoard);
        walls.get(Direction.DOWN).setTransparency(true);
        //DOES NOT DO ANYTHING THAT REQUIRES A LOCK ON THE OTHER BOARD
    }
    
    
    /***
     * Disjoins all connections to (and knowledge of) the given board
     * 
     * @param board board to disjoin from this
     */
    public void disjoin(Board board) {
        if (board == connectedBoards.get(Direction.UP)) {
            disjoinTop();
        }
        if (board == connectedBoards.get(Direction.DOWN)) {
            disjoinBottom();
        }
        if (board == connectedBoards.get(Direction.LEFT)) {
            disjoinLeft();
        }
        if (board == connectedBoards.get(Direction.RIGHT)) {
            disjoinRight();
        }
    }
    
    /***
     * Disjoins all connections to (and knowledge of) the current left board, if any
     */
    public void disjoinLeft(){
        connectedBoards.put(Direction.LEFT, null);
        walls.get(Direction.LEFT).setTransparency(false);
    }
    
    
    /***
     * Disjoins all connections to (and knowledge of) the current right board, if any
     */
    public void disjoinRight(){
        connectedBoards.put(Direction.RIGHT, null);
        walls.get(Direction.RIGHT).setTransparency(false);
    }
    
    /***
     * Disjoins all connections to (and knowledge of) the current top board, if any
     */
    public void disjoinTop(){
        connectedBoards.put(Direction.UP, null);
        walls.get(Direction.UP).setTransparency(false);
    }

    /***
     * Disjoins all connections to (and knowledge of) the current bottom board, if any
     */
    public void disjoinBottom(){
        connectedBoards.put(Direction.DOWN, null);
        walls.get(Direction.DOWN).setTransparency(false);
    }
    
    public Board getTopBoard(){
    	return connectedBoards.get(Direction.UP);
    }

    public Board getBottomBoard(){
        return connectedBoards.get(Direction.DOWN);
    }
    
    public Board getLeftBoard(){
        return connectedBoards.get(Direction.RIGHT);
    }
    
    public Board getRightBoard(){
        return connectedBoards.get(Direction.LEFT);
    }

    /***
     * Get a mapping of each direction to the Board that currently occupies that direction.
     * @return a mapping of each direction to the Board that currently occupies that direction.
     */
    public Map<Direction, Board> getConnectedBoards() {
        return connectedBoards;
    }

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
		Board other = (Board) obj;
		return (this+"").equals(other+"");
	}
    
    
    
    
    
}
