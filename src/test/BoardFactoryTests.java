package test;

import static org.junit.Assert.assertEquals;
import game.Absorber;
import game.Ball;
import game.Board;
import game.CircularBumper;
import game.Flipper;
import game.Gadget;
import game.Portal;
import game.SquareBumper;
import game.TriangularBumper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import physics.Vect;
import server.PingballServer;
import boardfile.BoardFactory;


/**
 * Tests for BoardFactory Class.
 * @author mvuyyuru
 *
 * Tests for the sample boards that the correct gadgets
 * are created by parsing the file and they possess the right properties.
 * 
 * Tests for behavior of the board itself (collisions, gravity, etc)
 * are handled by BoardTests.
 *
 */

public class BoardFactoryTests {			
	
    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before public void setUp() {
    }

      public void SampleBoard1ConstructorTest() {
    	String content = null;
        try {
            content = BoardFactory.readFile("boards/staffBoards/sampleBoard1.pb", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board board = BoardFactory.parse(content);
        
        Ball ballc = new Ball("BallC", new Vect(0.5, 0.5), new Vect(2.5, 2.5));
        Set<Ball> balls = new HashSet<Ball>();
        balls.add(ballc);
        
        SquareBumper sqA = new SquareBumper("SquareA", 0, 2);
        SquareBumper sqB = new SquareBumper("SquareB", 1, 2);
        SquareBumper sqC = new SquareBumper("SquareC", 2, 2);
        SquareBumper sqD = new SquareBumper("SquareD", 3, 2);
        SquareBumper sqE = new SquareBumper("SquareE", 4, 2);
        SquareBumper sqF = new SquareBumper("SquareF", 5, 2);
        SquareBumper sqG = new SquareBumper("SquareG", 6, 2);
        SquareBumper sqH = new SquareBumper("SquareH", 7, 2);

        SquareBumper sqI = new SquareBumper("SquareI", 12, 2);
        SquareBumper sqJ = new SquareBumper("SquareJ", 13, 2);
        SquareBumper sqK = new SquareBumper("SquareK", 14, 2);
        SquareBumper sqL = new SquareBumper("SquareL", 15, 2);
        SquareBumper sqM = new SquareBumper("SquareM", 16, 2);
        SquareBumper sqN = new SquareBumper("SquareN", 17, 2);
        SquareBumper sqO = new SquareBumper("SquareO", 18, 2);
        SquareBumper sqP = new SquareBumper("SquareP", 19, 2);
        
        CircularBumper ccA = new CircularBumper("CircleA", 4, 3);
        CircularBumper ccB = new CircularBumper("CircleB", 5, 4);
        CircularBumper ccC = new CircularBumper("CircleC", 6, 5);
        CircularBumper ccD = new CircularBumper("CircleD", 7, 6);
        CircularBumper ccE = new CircularBumper("CircleE", 12, 6);
        CircularBumper ccF = new CircularBumper("CircleF", 13, 5);
        CircularBumper ccG = new CircularBumper("CircleG", 14, 4);
        CircularBumper ccH = new CircularBumper("CircleH", 15, 3);
        
        TriangularBumper triA = new TriangularBumper("TriA", 8, 9, 270);
        TriangularBumper triB = new TriangularBumper("TriB", 11, 9, 180);

        Flipper flipL1 = new Flipper("left", "FlipL1", 8, 2, 0);
        Flipper flipR1 = new Flipper("right", "FlipR", 11, 2, 0);
        Flipper flipL2 = new Flipper("left", "FlipL", 8, 7, 0);
        Flipper flipR2 = new Flipper("right", "FlipR", 11, 7, 0);
        
        Absorber abs = new Absorber("abs", 0, 19, 20, 1);
        
        Map<Gadget, Set<Gadget>> actions = new HashMap<Gadget, Set<Gadget>>();
        actions.put(sqA, new HashSet<Gadget>());
        actions.put(sqB, new HashSet<Gadget>());
        actions.put(sqC, new HashSet<Gadget>());
        actions.put(sqD, new HashSet<Gadget>());
        actions.put(sqE, new HashSet<Gadget>());
        actions.put(sqF, new HashSet<Gadget>());
        actions.put(sqG, new HashSet<Gadget>());
        actions.put(sqH, new HashSet<Gadget>());
        actions.put(sqI, new HashSet<Gadget>());
        actions.put(sqJ, new HashSet<Gadget>());
        actions.put(sqK, new HashSet<Gadget>());
        actions.put(sqL, new HashSet<Gadget>());
        actions.put(sqM, new HashSet<Gadget>());
        actions.put(sqN, new HashSet<Gadget>());
        actions.put(sqO, new HashSet<Gadget>());
        actions.put(sqP, new HashSet<Gadget>());
        
        
        actions.put(ccA, new HashSet<Gadget>());
        actions.put(ccB, new HashSet<Gadget>());
        actions.put(ccC, new HashSet<Gadget>());
        actions.put(ccD, new HashSet<Gadget>());
        actions.put(ccE, new HashSet<Gadget>());
        actions.put(ccF, new HashSet<Gadget>());
        actions.put(ccG, new HashSet<Gadget>());
        actions.put(ccH, new HashSet<Gadget>());

        actions.put(triA, new HashSet<Gadget>());
        actions.put(triB, new HashSet<Gadget>());

        actions.put(flipL1, new HashSet<Gadget>());
        actions.put(flipR1, new HashSet<Gadget>());
        actions.put(flipL2, new HashSet<Gadget>());
        actions.put(flipR2, new HashSet<Gadget>());
        
        actions.put(abs, new HashSet<Gadget>());
        actions.get(abs).add(abs);
        
        Board constructedBoard = new Board("ExampleA", 20.0, 0.020,0.020, actions, 1.0/(double)PingballServer.FRAMERATE, balls);
        assertEquals(board, constructedBoard);
        
    }
    
    @Test public void SampleBoard2ConstructorTest() {
    	String content = null;
        try {
            content = BoardFactory.readFile("boards/staffBoards/sampleBoard2-1.pb", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board board = BoardFactory.parse(content);
        
        Ball ballc = new Ball("BallC", new Vect(0.5, 0.5), new Vect(2.5, 2.5));
        Set<Ball> balls = new HashSet<Ball>();
        balls.add(ballc);
        
        SquareBumper sqA = new SquareBumper("SquareA", 0, 2);
        SquareBumper sqB = new SquareBumper("SquareB", 1, 2);
        SquareBumper sqC = new SquareBumper("SquareC", 2, 2);
        SquareBumper sqD = new SquareBumper("SquareD", 3, 2);
        SquareBumper sqE = new SquareBumper("SquareE", 4, 2);
        SquareBumper sqF = new SquareBumper("SquareF", 5, 2);
        SquareBumper sqG = new SquareBumper("SquareG", 6, 2);
        SquareBumper sqH = new SquareBumper("SquareH", 7, 2);
        SquareBumper sqI = new SquareBumper("SquareI", 8, 2);
        SquareBumper sqJ = new SquareBumper("SquareJ", 9, 2);
        SquareBumper sqK = new SquareBumper("SquareK", 10, 2);
        SquareBumper sqL = new SquareBumper("SquareL", 11, 2);
        SquareBumper sqM = new SquareBumper("SquareM", 12, 2);
        SquareBumper sqN = new SquareBumper("SquareN", 13, 2);
        SquareBumper sqO = new SquareBumper("SquareO", 14, 2);
        SquareBumper sqP = new SquareBumper("SquareP", 15, 2);
        
        CircularBumper ccA = new CircularBumper("CircleA", 10, 3);
        CircularBumper ccB = new CircularBumper("CircleB", 11, 4);
        CircularBumper ccC = new CircularBumper("CircleC", 12, 5);
        CircularBumper ccD = new CircularBumper("CircleD", 13, 6);
        CircularBumper ccE = new CircularBumper("CircleE", 14, 7);
        CircularBumper ccF = new CircularBumper("CircleF", 15, 8);
        
        TriangularBumper triA = new TriangularBumper("TriA", 17, 11, 270);
        TriangularBumper triB = new TriangularBumper("TriB", 18, 12, 270);

        Flipper flipL1 = new Flipper("left", "FlipL1", 16, 2, 0);
        Flipper flipL2 = new Flipper("left", "FlipL2", 16, 9, 0);
        
        Absorber abs = new Absorber("abs", 0, 19, 20, 1);
        
        Map<Gadget, Set<Gadget>> actions = new HashMap<Gadget, Set<Gadget>>();
        actions.put(sqA, new HashSet<Gadget>());
        actions.put(sqB, new HashSet<Gadget>());
        actions.put(sqC, new HashSet<Gadget>());
        actions.put(sqD, new HashSet<Gadget>());
        actions.put(sqE, new HashSet<Gadget>());
        actions.put(sqF, new HashSet<Gadget>());
        actions.put(sqG, new HashSet<Gadget>());
        actions.put(sqH, new HashSet<Gadget>());
        actions.put(sqI, new HashSet<Gadget>());
        actions.put(sqJ, new HashSet<Gadget>());
        actions.put(sqK, new HashSet<Gadget>());
        actions.put(sqL, new HashSet<Gadget>());
        actions.put(sqM, new HashSet<Gadget>());
        actions.put(sqN, new HashSet<Gadget>());
        actions.put(sqO, new HashSet<Gadget>());
        actions.put(sqP, new HashSet<Gadget>());
        
        
        actions.put(ccA, new HashSet<Gadget>());
        actions.put(ccB, new HashSet<Gadget>());
        actions.put(ccC, new HashSet<Gadget>());
        actions.put(ccD, new HashSet<Gadget>());
        actions.put(ccE, new HashSet<Gadget>());
        actions.put(ccF, new HashSet<Gadget>());


        actions.put(triA, new HashSet<Gadget>());
        actions.put(triB, new HashSet<Gadget>());

        actions.put(flipL1, new HashSet<Gadget>());
        actions.put(flipL2, new HashSet<Gadget>());
        
        actions.put(abs, new HashSet<Gadget>());
        actions.get(abs).add(abs);
        
        Board constructedBoard = new Board("ExampleA", 20.0, 0.020,0.020, actions, 1.0/(double)PingballServer.FRAMERATE, balls);
        assertEquals(board, constructedBoard);
        
    }
    
    @Test public void SampleBoard3ConstructorTest() {
    	String content = null;
        try {
            content = BoardFactory.readFile("boards/staffBoards/sampleBoard3.pb", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board board = BoardFactory.parse(content);
        
        Ball balla = new Ball("BallA", new Vect(1.8, 4.5), new Vect(10.4, 10.3));
        Ball ballb = new Ball("BallB)", new Vect(10,13),new Vect(-3.4,-2.3));
        Set<Ball> balls = new HashSet<Ball>();
        balls.add(balla);
        balls.add(ballb);
        
        SquareBumper sq = new SquareBumper("Square", 0, 10);
        SquareBumper sqB = new SquareBumper("SquareB", 1, 10);
        SquareBumper sqC = new SquareBumper("SquareC", 2, 10);
        SquareBumper sqD = new SquareBumper("SquareD", 3, 10);
        SquareBumper sqE = new SquareBumper("SquareE", 4, 10);
        SquareBumper sqF = new SquareBumper("SquareF", 5, 10);
        SquareBumper sqG = new SquareBumper("SquareG", 6, 10);
        SquareBumper sqH = new SquareBumper("SquareH", 7, 10);
        
        CircularBumper cc = new CircularBumper("Circle", 4, 3);

        
        TriangularBumper tri = new TriangularBumper("Tri", 19, 3, 90);

        Flipper flipL = new Flipper("left", "FlipL", 10, 7, 0);
        Flipper flipR = new Flipper("right", "FlipR", 12, 7, 0);

        
        Absorber abs = new Absorber("abs", 10, 17, 10, 2);
        
        Map<Gadget, Set<Gadget>> actions = new HashMap<Gadget, Set<Gadget>>();
        actions.put(sq, new HashSet<Gadget>());
        actions.put(sqB, new HashSet<Gadget>());
        actions.put(sqC, new HashSet<Gadget>());
        actions.put(sqD, new HashSet<Gadget>());
        actions.put(sqE, new HashSet<Gadget>());
        actions.put(sqF, new HashSet<Gadget>());
        actions.put(sqG, new HashSet<Gadget>());
        actions.put(sqH, new HashSet<Gadget>());
        
        actions.put(cc, new HashSet<Gadget>());
        
        actions.put(tri, new HashSet<Gadget>());

        actions.put(flipL, new HashSet<Gadget>());
        actions.put(flipR, new HashSet<Gadget>());
        
        actions.put(abs, new HashSet<Gadget>());
        actions.get(abs).add(abs);
        
        Board constructedBoard = new Board("ExampleA", 10.0, Board.DEFAULTMU1,Board.DEFAULTMU2, actions, 1.0/(double)PingballServer.FRAMERATE, balls);
        assertEquals(board, constructedBoard);
        
    }    
    @Test public void SampleBoard4ConstructorTest() {
        String content = null;
        try {
            content = BoardFactory.readFile("boards/staffBoards/sampleBoard4.pb", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board board = BoardFactory.parse(content);

        Ball ballc = new Ball("BallC", new Vect(1.8, 4.5), new Vect(10.4, 10.3));
        Set<Ball> balls = new HashSet<Ball>();
        balls.add(ballc);


        SquareBumper sqA = new SquareBumper("Square", 0, 10);
        SquareBumper sqB = new SquareBumper("SquareB", 1, 10);
        SquareBumper sqC = new SquareBumper("SquareC", 2, 10);
        SquareBumper sqD = new SquareBumper("SquareD", 3, 10);

        CircularBumper cc = new CircularBumper("Circle", 4, 3);
        TriangularBumper tri = new TriangularBumper("Tri", 19, 3, 90);

        Flipper flipl = new Flipper("left", "FlipL", 10, 7, 0);
        Flipper flipr = new Flipper("right", "FlipR", 12, 7, 0);

        Absorber abs = new Absorber("abs", 10, 17, 10, 2);
        Map<Gadget, Set<Gadget>> actions = new HashMap<Gadget, Set<Gadget>>();
        actions.put(sqA, new HashSet<Gadget>());
        actions.put(sqB, new HashSet<Gadget>());
        actions.put(sqC, new HashSet<Gadget>());
        actions.put(sqD, new HashSet<Gadget>());
        actions.put(cc, new HashSet<Gadget>());
        actions.put(tri, new HashSet<Gadget>());
        actions.put(flipl, new HashSet<Gadget>());
        actions.put(flipr, new HashSet<Gadget>());
        actions.put(abs, new HashSet<Gadget>());
        actions.get(abs).add(abs);

        Board constructedBoard = new Board("ExampleA", 20.0, Board.DEFAULTMU1, Board.DEFAULTMU2, actions, 1.0/(double)PingballServer.FRAMERATE, balls);
        assertEquals(board, constructedBoard);
    }
    
    @Test public void SimplePortalBoardConstructorTest() {
        String content = null;
        try {
            content = BoardFactory.readFile("boards/phase2features/simplePortal.pb", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board board = BoardFactory.parse(content);
        Board constructedBoard = null;
        
        Ball ball = new Ball("Ball", new Vect(15, 1.5), new Vect(0.0, 0.5));
        Set<Ball> balls = new HashSet<Ball>();
        balls.add(ball);

        Flipper flipl = new Flipper("left", "FlipL", 4, 14, 90);
        Flipper flipr = new Flipper("right", "FlipR", 6, 11, 0);
        
        Portal alpha = new Portal("Alpha",15,16, "Beta");
        Portal beta = new Portal("Beta",6,4,"Alpha");
        
        Absorber abs = new Absorber("abs", 0, 19, 20, 1);
        
        Map<Gadget, Set<Gadget>> actions = new HashMap<Gadget, Set<Gadget>>();
        actions.put(flipl, new HashSet<Gadget>());
        actions.put(flipr, new HashSet<Gadget>());
        actions.put(abs, new HashSet<Gadget>());
        actions.get(abs).add(abs);
        actions.put(alpha, new HashSet<Gadget>());
        actions.put(beta, new HashSet<Gadget>());

        constructedBoard = new Board("sampleBoard", 20.0, Board.DEFAULTMU1, Board.DEFAULTMU2, actions, 1.0/(double)PingballServer.FRAMERATE, balls);
        assertEquals(board, constructedBoard);

    }
    
    @Test public void FlipperBoardConstructorTest() {
        String content = null;
        try {
            content = BoardFactory.readFile("boards/phase2features/KeyFlippers.pb", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board board = BoardFactory.parse(content);
        
        Ball ball = new Ball("Ball", new Vect(0.5, 0.5), new Vect(2.5, 2.5));
        Set<Ball> balls = new HashSet<Ball>();
        balls.add(ball);
        
        SquareBumper sq = new SquareBumper("Square", 15, 12);
        
        CircularBumper ccA = new CircularBumper("CircleA", 14, 6);
        CircularBumper ccB = new CircularBumper("CircleB", 14, 8);
        
        TriangularBumper triA = new TriangularBumper("TriA", 19, 7, 90);

        Flipper flipL1 = new Flipper("left", "FlipL1", 4, 14, 90);
        Flipper flipR1 = new Flipper("right", "FlipR", 6, 11, 0);
        
        Absorber abs = new Absorber("abs", 0, 19, 20, 1);
        
        Map<Gadget, Set<Gadget>> actions = new HashMap<Gadget, Set<Gadget>>();
        actions.put(sq, new HashSet<Gadget>());  
        actions.put(ccA, new HashSet<Gadget>());
        actions.put(ccB, new HashSet<Gadget>());
        actions.put(triA, new HashSet<Gadget>());
        actions.put(flipL1, new HashSet<Gadget>());
        actions.put(flipR1, new HashSet<Gadget>());
        
        actions.put(abs, new HashSet<Gadget>());
        actions.get(abs).add(abs);
        
        Board constructedBoard = new Board("ExampleA", 20.0, 0.020,0.020, actions, 1.0/(double)PingballServer.FRAMERATE, balls);
        assertEquals(board, constructedBoard);
        
    }

    @Test public void AllKeysBoardConstructorTest() {
        String content = null;
        try {
            content = BoardFactory.readFile("boards/phase2features/allkeys.pb", StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Board board = BoardFactory.parse(content);
        
        Ball ball = new Ball("Ball", new Vect(0.5, 0.5), new Vect(2.5, 2.5));
        Set<Ball> balls = new HashSet<Ball>();
        balls.add(ball);
   
        Flipper flipL1 = new Flipper("left", "FlipL1", 4, 14, 90);
        Flipper flipR1 = new Flipper("right", "FlipR", 6, 11, 0);
        
        
        Map<Gadget, Set<Gadget>> actions = new HashMap<Gadget, Set<Gadget>>();
        actions.put(flipL1, new HashSet<Gadget>());
        actions.put(flipR1, new HashSet<Gadget>());
        
        
        Board constructedBoard = new Board("ExampleA", 20.0, 0.020,0.020, actions, 1.0/(double)PingballServer.FRAMERATE, balls);
        assertEquals(board, constructedBoard);
    }
}
