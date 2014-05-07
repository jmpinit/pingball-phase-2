package test;

import static org.junit.Assert.assertEquals;
import game.Absorber;
import game.Ball;
import game.Board;
import game.CircularBumper;
import game.Flipper;
import game.GamePiece;
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
 * @author pkalluri
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

    @Test public void SampleBoard1ConstructorTest() {
        String content = null;
        try {
            content = BoardFactory.readFile("boards/sampleBoard4.pb", StandardCharsets.UTF_8);
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
        Map<GamePiece, Set<GamePiece>> actions = new HashMap<GamePiece, Set<GamePiece>>();
        actions.put(sqA, new HashSet<GamePiece>());
        actions.put(sqB, new HashSet<GamePiece>());
        actions.put(sqC, new HashSet<GamePiece>());
        actions.put(sqD, new HashSet<GamePiece>());
        actions.put(cc, new HashSet<GamePiece>());
        actions.put(tri, new HashSet<GamePiece>());
        actions.put(flipl, new HashSet<GamePiece>());
        actions.put(flipr, new HashSet<GamePiece>());
        actions.put(abs, new HashSet<GamePiece>());
        actions.get(abs).add(abs);

        Board constructedBoard = new Board("ExampleA", 20.0, Board.DEFAULTMU1, Board.DEFAULTMU2, actions, 1.0/(double)PingballServer.FRAMERATE, balls);

        assertEquals(board, constructedBoard);
    }

}
