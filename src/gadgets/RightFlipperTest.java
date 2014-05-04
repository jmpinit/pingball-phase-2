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

public class RightFlipperTest {

    private static RightFlipper rf1;
    private static RightFlipper rf2;
    private static RightFlipper rf3;

    private static Ball b1;

    @BeforeClass
    public static void setUpBeforeClass() {
        rf1 = new RightFlipper(4, 4, 1, "rf1");
        rf2 = new RightFlipper(4, 8, 1, "rf2");
        rf3 = new RightFlipper(8, 4, 1, "rf3");
        b1 = new Ball(5, 5, 5, 5);
    }

    @Test
    public void testConstructor() {
        assertEquals(rf1.getTop(), new LineSegment(4, 4, 6, 4));
        assertEquals(rf1.getBottom(), new LineSegment(4, 6, 6, 6));
        assertEquals(rf1.getLeft(), new LineSegment(4, 4, 4, 6));
        assertEquals(rf1.getRight(), new LineSegment(6, 4, 6, 6));

        assertEquals(rf2.getTop(), new LineSegment(4, 8, 6, 8));
        assertEquals(rf2.getBottom(), new LineSegment(4, 10, 6, 10));
        assertEquals(rf2.getLeft(), new LineSegment(4, 8, 4, 10));
        assertEquals(rf2.getRight(), new LineSegment(6, 8, 6, 10));

        assertEquals(rf3.getTop(), new LineSegment(8, 4, 10, 4));
        assertEquals(rf3.getBottom(), new LineSegment(8, 6, 10, 6));
        assertEquals(rf3.getLeft(), new LineSegment(8, 4, 8, 6));
        assertEquals(rf3.getRight(), new LineSegment(10, 4, 10, 6));
    }

    @Test
    public void testTrigger() {
        // assertEquals(false, rf1.trigger(b1));
    }

    @Test
    public void testAction() {

    }
}
