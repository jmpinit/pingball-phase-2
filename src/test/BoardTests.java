package gameParts;

import physics.*;

import java.io.File;
import java.util.*;

import org.junit.*;
import org.junit.*;

import static org.junit.Assert.*;


/**
 * Tests for Board Class.
 * @author pkalluri
 *
 * These are the partitions:
 * 
 * For a board --
 * - no gadgets, 1 gadget, many gadgets
 * - no balls, 1 ball, many balls
 * - there exists a gadget that triggers:
 *      - no actions
 *      - one action
 *      - multiple action
 * 
 * For board.addQueuedBalls() --
 * - the queue is empty
 * - the queue has 1 ball in it
 * - the queue has many balls in it
 * 
 * For 'board.disjoin'-type methods --
 * - when there is a board currently joined there
 * - when there is no board currently joined there
 * - when there are also boards joined on some other sides
 * - when there are no boards joined on other sides
 * 
 * For 'board.join'-type methods --
 * - when there is already a board there, to be unjoined before the join is done
 * - when there is no board there yet
 * - when there are also board joined on some other sides
 * - when there are no boards joined on other sides
 * 
 * For board.step() --
 * - when stepping will cause no collisions
 * - when stepping will cause at least one ball-gadget collision, and with each type of gadget
 * - when stepping will cause at least one ball-ball collision
 * - when stepping will cause a ball to undergo >1 collision before the end of the time step
 * - when stepping will cause a ball to collide with one thing, before it would've collided with another
 * 
 * For board.toString() --
 * - after each of the above methods
 *
 * Most cross-sections will be tested; all partitions will be tested. 
 */
public class BoardTests {

    Ball ball1;
    Ball ball2;
    Ball ball3;
    SquareBumper sqb;
    CircularBumper ccb;
    TriangularBumper trib1;
    TriangularBumper trib2;
    Absorber abs;
    Flipper leftf;
    Map<GamePiece, Set<GamePiece>> actions;
    Set<Ball> balls;
    Board board;

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before public void setUp() {
        ball1 = new Ball("ball1", new Vect(4.5,5.5), new Vect(1.0,0.0));
        ball2 = new Ball("ball2", new Vect(4.0,7.0), new Vect(1.1,-0.8));
        ball3 = new Ball("ball3", new Vect(5.5,6.5), new Vect(1.0,0.0));
        sqb = new SquareBumper("sqb", 6, 5);
        ccb = new CircularBumper("ccb", 6, 5);
        trib1 = new TriangularBumper("trib", 6, 5, 0);
        trib2 = new TriangularBumper("trib", 6, 5, 180);
        abs = new Absorber("abs", 6,5,3,2);
        leftf = new Flipper("left", "leftl", 7, 6, 0);
        balls = new HashSet<Ball>();
        actions = new HashMap<GamePiece, Set<GamePiece>>();
    }

    @Test public void WallDirectCollisionTest(){
        balls.add(ball1);
        ball1.setVelocity(new Vect(-1.0, 0.0));
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 100; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 1,
                ball1.getPosition().y() - 5.5);
        // ball bounces directly off Wall in opp direction
        assertTrue(diff.length() < 1e-10);
    }

    @Test public void TwoBallsCollisionTest(){
        ball1 = new Ball("ball1", new Vect(2.5, 5.2), new Vect(7.3, 0.12));
        ball2 = new Ball("ball2", new Vect(5.2, 2.5), new Vect(0.12, 7.3));
        balls.add(ball1);
        balls.add(ball2);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 5.146446609406737,
                ball1.getPosition().y() - 18.396446609406738);
        // balls bounces obliquely off each other
        assertTrue(diff.length() < 1e-10);
        diff = new Vect(ball2.getPosition().x() - 18.396446609406738,
                ball2.getPosition().y() - 5.146446609406737);
        assertTrue(diff.length() < 1e-10);
    }    


    @Test public void WallObliqueCollisionTest(){
        balls.add(ball2);
        ball2.setVelocity(new Vect(-1.1, -0.8));
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 100; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball2.getPosition().x() - 2,
                ball2.getPosition().y() - 3);
        // ball bounces obliquely off Wall
        assertTrue(diff.length() < 1e-10);
    }    

    @Test public void SquareBumperDirectCollisionTest(){
        actions.put(sqb, new HashSet<GamePiece>());
        balls.add(ball1);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 4.5,
                ball1.getPosition().y() - 5.5);
        // ball bounces directly off SquareBumper in opp direction
        assertTrue(diff.length() < 1e-10);
    }

    @Test public void SquareBumperObliqueCollisionTest(){
        actions.put(sqb, new HashSet<GamePiece>());
        balls.add(ball2);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball2.getPosition().x() - 4.75,
                ball2.getPosition().y() - 5.0);
        // ball bounces obliquely off closest edge of SquareBumper
        assertTrue(diff.length() < 1e-10);
    }

    @Test public void CircleBumperDirectCollisionTest(){
        actions.put(ccb, new HashSet<GamePiece>());
        balls.add(ball1);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 4.5,
                ball1.getPosition().y() - 5.5);
        // ball bounces directly off CircleBumper in opp direction
        assertTrue(diff.length() < 1e-10);
    }

    @Test public void CircularBumperObliqueCollisionTest(){
        actions.put(ccb, new HashSet<GamePiece>());
        balls.add(ball2);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball2.getPosition().x() - 4.581250516798634,
                ball2.getPosition().y() - 5.620302421582046);
        // ball bounces obliquely off CircleBumper in random direction
        assertTrue(diff.length() < 1e-10);
    }    

    @Test public void TriangularBumperLegCollisionTest(){
        actions.put(trib1, new HashSet<GamePiece>());
        balls.add(ball1);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 4.5,
                ball1.getPosition().y() - 5.5);
        // ball bounces directly off leg of CircleBumper in opp direction
        assertTrue(diff.length() < 1e-10);
    }    

    @Test public void TriangularBumperHypotenuseCollisionTest(){
        actions.put(trib2, new HashSet<GamePiece>());
        balls.add(ball1);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 6.146446609406726,
                ball1.getPosition().y() - 4.646446609406735);
        // ball bounces directly off hypotenuse of CircleBumper to turn 90 degrees
        assertTrue(diff.length() < 1e-10);
    }    

    @Test public void AbsorberCollisionNoTriggerTest(){
        actions.put(abs, new HashSet<GamePiece>());
        balls.add(ball1);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 8.75,
                ball1.getPosition().y() - 6.75);
        // ball stays indefinitely in bottom right corner of absorber
        assertTrue(diff.length() < 1e-10);
    }    

    @Test public void AbsorberCollisionSelfTriggerTest(){
        actions.put(abs, new HashSet<GamePiece>());
        actions.get(abs).add(abs);
        balls.add(ball1);
        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 8.75,
                ball1.getPosition().y() - 1.25);
        // ball is shot out of absorber and bounces up and down indefinitely
        assertTrue(diff.length() < 1e-10);
    }    

    @Test public void AbsorberCollisionTriggerByOtherGadgetTest(){
        SquareBumper sqb2 = new SquareBumper("sqb2", 10, 15);
        actions.put(sqb2, new HashSet<GamePiece>());

        actions.put(abs, new HashSet<GamePiece>());
        actions.get(sqb2).add(abs);
        balls.add(new Ball("ball2", new Vect(3.0, 15.5), new Vect(1.0, 0.0)));
        balls.add(ball1);

        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 8.75,
                ball1.getPosition().y() - 6.75);
        // ball is deactivated and stuck inside Absorber
        assertTrue(diff.length() < 1e-10);
        for (int i = 0; i < 87; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        diff = new Vect(ball1.getPosition().x() - 8.75,
                ball1.getPosition().y() - 1.75);
        // once ball2 hits the square bumper, abs is activated and releases ball upwards
        assertTrue(diff.length() < 1e-10);
    }        

    @Test public void FlipperCollisionTest(){
        actions.put(leftf, new HashSet<GamePiece>());
        balls.add(ball3);

        board = new Board("board", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball3.getPosition().x() - 5.5625,
                ball3.getPosition().y() - 6.5);
        // ball bounces off flipper back in other direction
        assertTrue(diff.length() < 1e-10);
    }    

    @Test public void GravityAndFrictionTest(){
        balls.add(ball1);
        board = new Board("board", Board.DEFAULTGRAVITY, Board.DEFAULTMU1, Board.DEFAULTMU2,
                actions, 0.05, balls);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 6.285096824423805,
                ball1.getPosition().y() - 16.438896255810622);
        assertTrue(diff.length() < 1e-10);
        for (int i = 0; i < 50; i++){
            try {
                board.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        diff = new Vect(ball1.getPosition().x() - 7.196172097846488,
                ball1.getPosition().y() - 18.041691389536073);
        // ball accelerates towards the ground and bounces back up
        assertTrue(diff.length() < 1e-10);

    }    

    @Test public void JoinTwoBoardsAndTransferBallsTest(){
        Set<Ball> balls1 = new HashSet<Ball>();
        ball1.setPosition(new Vect(18, 5));
        balls1.add(ball1);
        Board board1 = new Board("board1", 0.0, 0.0, 0.0,
                actions, 0.05, balls1);
        Board board2 = new Board("board2", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        board1.joinRightWallTo(board2);
        board2.joinLeftWallTo(board1);
        for (int i = 0; i < 50; i++){
            try {
                board1.step();
                board2.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        assertFalse(balls1.contains(ball1));
        assertTrue(balls.contains(ball1));
        assertEquals(board1, board2.getLeftBoard());
        assertEquals(board2, board1.getRightBoard());

        // ball has transfered from board1 to board2
    }

    @Test public void DisjoinBoardsThatWerentJoinedTest(){
        Set<Ball> balls1 = new HashSet<Ball>();
        ball1.setPosition(new Vect(18, 5));
        balls1.add(ball1);
        Board board1 = new Board("board1", 0.0, 0.0, 0.0,
                actions, 0.05, balls1);
        Board board2 = new Board("board2", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        board1.disjoin(board2);
        board2.disjoin(board1);
        board1.joinRightWallTo(board2);
        board2.joinLeftWallTo(board1);
        for (int i = 0; i < 50; i++){
            try {
                board1.step();
                board2.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertFalse(balls1.contains(ball1));
        assertTrue(balls.contains(ball1)); // ball has transfered from board1 to board2
        assertEquals(board1, board2.getLeftBoard());
        assertEquals(board2, board1.getRightBoard());
    }



    @Test public void QueueMultipleBallsTest(){
        Set<Ball> balls1 = new HashSet<Ball>();
        ball1.setPosition(new Vect(18, 5));
        ball2 = new Ball("ball2", new Vect(18.0, 10.0), new Vect(1.0,0.0));
        balls1.add(ball1);
        balls1.add(ball2);
        Board board1 = new Board("board1", 0.0, 0.0, 0.0,
                actions, 0.05, balls1);
        Board board2 = new Board("board2", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        board1.joinRightWallTo(board2);
        board2.joinLeftWallTo(board1);
        for (int i = 0; i < 50; i++){
            try {
                board1.step();
                board2.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        assertFalse(balls1.contains(ball1));
        assertFalse(balls1.contains(ball2));
        assertTrue(balls.contains(ball1)); // both balls has transfered from board1 to board2
        assertTrue(balls.contains(ball2));
        assertEquals(board1, board2.getLeftBoard());
        assertEquals(board2, board1.getRightBoard());

    }    	

    @Test public void JoinMultipleSidesOfBoards(){
        Board board1 = new Board("board1", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        Board board2 = new Board("board2", 0.0, 0.0, 0.0,
                actions, 0.05, balls);

        board1.joinTopWallTo(board2);
        board2.joinBottomWallTo(board1);

        board1.joinBottomWallTo(board2);
        board2.joinTopWallTo(board1);

        assertEquals(board1, board2.getTopBoard());
        assertEquals(board2, board1.getTopBoard());
        assertEquals(board2, board1.getBottomBoard());
        assertEquals(board1, board2.getBottomBoard());
    }            

    @Test public void JoinOverwritingExistingJoin(){
        Board board1 = new Board("board1", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        Board board2 = new Board("board2", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        Board board3 = new Board("board3", 0.0, 0.0, 0.0,
                actions, 0.05, balls);


        board1.joinRightWallTo(board2);
        board2.joinLeftWallTo(board1);

        board1.joinRightWallTo(board3);
        board3.joinRightWallTo(board1);

        assertNotEquals(board2, board1.getRightBoard());
        assertNotEquals(board1, board2.getLeftBoard()); //TODO
        assertEquals(board3, board1.getRightBoard());
        assertEquals(board1, board3.getRightBoard());
    }            




    @Test public void JoinAndDisjoinBoardFromItself(){
        Board board1 = new Board("board1", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        ball1.setPosition(new Vect(18.0, 10.0));
        balls.add(ball1);
        board1.joinRightWallTo(board1);
        board1.joinLeftWallTo(board1);
        for (int i = 0; i < 50; i++){
            try {
                board1.step();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Vect diff = new Vect(ball1.getPosition().x() - 0.5,
                ball1.getPosition().y() - 10.0);
        // ball exits right wall and re-enters same board through left wall
        assertTrue(diff.length() < 1e-10);
        board1.disjoin(board1);

        assertNotEquals(board1, board1.getRightBoard());
        assertNotEquals(board1, board1.getLeftBoard());


    }            

    @Test public void UnjoinBoardsAndThenJoinThemDifferently(){
        Board board1 = new Board("board1", 0.0, 0.0, 0.0,
                actions, 0.05, balls);
        Board board2 = new Board("board2", 0.0, 0.0, 0.0,
                actions, 0.05, balls);

        board1.joinRightWallTo(board2);
        board2.joinLeftWallTo(board1);

        board1.disjoin(board2);
        board2.disjoin(board1);

        board1.joinTopWallTo(board2);
        board2.joinBottomWallTo(board1);

        assertNotEquals(board2, board1.getRightBoard());
        assertNotEquals(board1, board2.getLeftBoard());
        assertEquals(board2, board1.getTopBoard());
        assertEquals(board1, board2.getBottomBoard());
    }            

}
