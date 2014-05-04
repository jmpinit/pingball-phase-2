package skeletonSpecs;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import physics.LineSegment;

/**
 * Testing Strategy: The constructor, trigger, action, and move methods of the
 * flippers were tested by constructing flippers in different orientations and
 * checking if the x and y coordinates of the different line segments were
 * behaving correctly Input Partioning: Orientation of Flipper: Top, Bottom,
 * Left, Right Postion of Flipper: Center, Left Edge, Right Edge, Top Edge,
 * Bottom Edge, Corners.
 */

public class LeftFlipperTest {

    private static LeftFlipper lf1;
    private static LeftFlipper lf2;
    private static LeftFlipper lf3;

    private static Ball b1;

    @BeforeClass
    public static void setUpBeforeClass() {
        lf1 = new LeftFlipper(4, 4, 3, "lf1");
        lf2 = new LeftFlipper(4, 8, 1, "lf2");
        lf3 = new LeftFlipper(8, 4, 1, "lf3");
        b1 = new Ball(5, 5, 5, 5);
    }

    @Test
    public void testConstructor() {

        assertEquals(lf2.getTop(), new LineSegment(4, 8, 6, 8));
        assertEquals(lf2.getBottom(), new LineSegment(4, 10, 6, 10));
        assertEquals(lf2.getLeft(), new LineSegment(4, 8, 4, 10));
        assertEquals(lf2.getRight(), new LineSegment(6, 8, 6, 10));

        assertEquals(lf3.getTop(), new LineSegment(8, 4, 10, 4));
        assertEquals(lf3.getBottom(), new LineSegment(8, 6, 10, 6));
        assertEquals(lf3.getLeft(), new LineSegment(8, 4, 8, 6));
        assertEquals(lf3.getRight(), new LineSegment(10, 4, 10, 6));
    }

    @Test
    public void testTrigger() {
        // Tested using board, console, and telnet
    }

    @Test
    public void testAction() {
        // Tested using board, console, and telnet
    }

    @Test
    public void testMove() {
        System.out.println(lf1.getState());
        lf1.move(0.45);
        System.out.println(lf1.getState());
        lf1.move(0.1);
        System.out.println(lf1.getState());
    }

}
