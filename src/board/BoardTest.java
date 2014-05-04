package skeletonSpecs;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests that board objects equal the board files they were created from.
 * Aren't running on didit due to source permissions.
 * @category no_didit
 */

public class BoardTest {

    private static Board boardFile1;
    private static Board boardFile2;
    private static Board boardFile3;
    private static Board boardFile4;
    private static Board shruthi;
    private static Board uma;
    private static Board megs;


    private final String SQUARE_BUMPER = "#";
    private final String CIRCLE_BUMPER = "0";
    private final String TRIANGLE_BUMPER_0 = "/";
    private final String TRIANGLE_BUMPER_90 = "\\";
    private final String VERTICAL_FLIPPER = "|";
    private final String HORIZONTAL_FLIPPER = "_";
    private final String OUTER_WALL = ".";
    private final String ABSORBER = "=";

    @BeforeClass
    public static void setup() throws IOException {


        boardFile1 = new Board("boards/sampleBoard1.pb");
        boardFile2 = new Board("boards/sampleBoard2-1.pb");
        boardFile3 = new Board("boards/sampleBoard3.pb");
        boardFile4 = new Board("boards/sampleBoard4.pb");
        shruthi    = new Board("boards/userSampleA.pb");
        uma        = new Board("boards/userSampleB.pb");
        megs       = new Board("boards/userSampleC.pb");


    }


    /**
     * Tests that a board is properly initialized from sampleBoard1.pb
     * @throws IOException 
     */
    @Test
    public void testBoardFromFile1() throws IOException {
        
         
        // Check for all square bumpers
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(0, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(1, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(2, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(3, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(4, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(5, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(6, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(7, 2));

        assertEquals(SQUARE_BUMPER, boardFile1.getElement(12, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(13, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(14, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(15, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(16, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(17, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(18, 2));
        assertEquals(SQUARE_BUMPER, boardFile1.getElement(19, 2));

        // Check for all circle bumpers
        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(4, 3));
        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(5, 4));
        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(6, 5));
        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(7, 6));

        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(12, 6));
        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(13, 5));
        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(14, 4));
        assertEquals(CIRCLE_BUMPER, boardFile1.getElement(15, 3));

        // Check for all triangle bumpers
        assertEquals(TRIANGLE_BUMPER_90, boardFile1.getElement(8, 9));
        assertEquals(TRIANGLE_BUMPER_0, boardFile1.getElement(11, 9));

        // Check for all flippers
        assertEquals(VERTICAL_FLIPPER, boardFile1.getElement(8, 2));
        assertEquals(VERTICAL_FLIPPER, boardFile1.getElement(10, 2));
        assertEquals(VERTICAL_FLIPPER, boardFile1.getElement(8, 7));
        assertEquals(VERTICAL_FLIPPER, boardFile1.getElement(10, 7));

        // Check for all absorbers
        for (int i = 0; i < 20; i++) {
            assertEquals(ABSORBER, boardFile1.getElement(i, 19));
        }
        
        
    }

    /**
     * Tests that a board is properly initialized from sampleBoard2-1
     */
    @Test
    public void testBoardFromFile2() {
        // Check for all square bumpers
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(0, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(1, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(2, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(3, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(4, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(5, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(6, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(7, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(12, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(13, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(14, 2));
        assertEquals(SQUARE_BUMPER, boardFile2.getElement(15, 2));

        // Check for all circle bumpers
        assertEquals(CIRCLE_BUMPER, boardFile2.getElement(10, 3));
        assertEquals(CIRCLE_BUMPER, boardFile2.getElement(11, 4));
        assertEquals(CIRCLE_BUMPER, boardFile2.getElement(12, 5));
        assertEquals(CIRCLE_BUMPER, boardFile2.getElement(13, 6));
        assertEquals(CIRCLE_BUMPER, boardFile2.getElement(14, 7));
        assertEquals(CIRCLE_BUMPER, boardFile2.getElement(15, 8));

        // Check for all triangle bumpers
        assertEquals(TRIANGLE_BUMPER_90, boardFile2.getElement(17, 11));
        assertEquals(TRIANGLE_BUMPER_90, boardFile2.getElement(18, 12));

        // Check for all flippers
        // TODO: Check for initialization orientation of flippers
        assertEquals(VERTICAL_FLIPPER, boardFile2.getElement(16, 2));
        assertEquals(VERTICAL_FLIPPER, boardFile2.getElement(16, 9));

        // Check for all absorbers
        for (int i = 0; i < 20; i++) {
            assertEquals(ABSORBER, boardFile2.getElement(i, 19));
        }
    }

    /**
     * Tests that a board is properly initialized from sampleBoard3
     */
    @Test
    public void testBoardFromFile3() {
        // Check for all square bumpers
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(0, 10));
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(1, 10));
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(2, 10));
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(3, 10));
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(4, 10));
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(5, 10));
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(6, 10));
        assertEquals(SQUARE_BUMPER, boardFile3.getElement(7, 10));

        // Check for all circle bumpers
        assertEquals(CIRCLE_BUMPER, boardFile3.getElement(4, 3));

        // Check for all triangle bumpers
        assertEquals(TRIANGLE_BUMPER_90, boardFile3.getElement(19, 3));

        // Check for all flippers
        assertEquals(VERTICAL_FLIPPER, boardFile3.getElement(10, 7));
        assertEquals(VERTICAL_FLIPPER, boardFile3.getElement(12, 7));

        // Check for all absorbers
        for (int i = 10; i < 20; i++) {
            for (int j = 17; j < 19; j++)
                assertEquals(ABSORBER, boardFile3.getElement(i, j));
        }
    }

    /**
     * Tests that a board is properly initialized from sampleBoard4
     */
    @Test
    public void testBoardFromFile4() {
        // Check for all square bumpers
        assertEquals(SQUARE_BUMPER, boardFile4.getElement(0, 10));
        assertEquals(SQUARE_BUMPER, boardFile4.getElement(1, 10));
        assertEquals(SQUARE_BUMPER, boardFile4.getElement(2, 10));
        assertEquals(SQUARE_BUMPER, boardFile4.getElement(3, 10));

        // Check for all circle bumpers
        assertEquals(CIRCLE_BUMPER, boardFile4.getElement(4, 3));

        // Check for all triangle bumpers
        assertEquals(TRIANGLE_BUMPER_90, boardFile4.getElement(19, 3));

        // Check for all flippers
        assertEquals(VERTICAL_FLIPPER, boardFile4.getElement(10, 7));
        assertEquals(VERTICAL_FLIPPER, boardFile4.getElement(12, 7));

        // Check for all absorbers
        for (int i = 10; i < 20; i++) {
            for (int j = 17; j < 19; j++)
                assertEquals(ABSORBER, boardFile4.getElement(i, j));
        } 
    }
    
    /**
     * Tests that a board is properly initialized from userSampleA
     */
    @Test
    public void testBoardFromUserSampleA() {
        //Checks for all square bumpers
        assertEquals(SQUARE_BUMPER, shruthi.getElement(2, 4));
        assertEquals(SQUARE_BUMPER, shruthi.getElement(3, 4));
        assertEquals(SQUARE_BUMPER, shruthi.getElement(4, 5));
        assertEquals(SQUARE_BUMPER, shruthi.getElement(5, 5));
        
        assertEquals(SQUARE_BUMPER, shruthi.getElement(13, 5));
        assertEquals(SQUARE_BUMPER, shruthi.getElement(14, 5));
        assertEquals(SQUARE_BUMPER, shruthi.getElement(15, 4));
        assertEquals(SQUARE_BUMPER, shruthi.getElement(16, 4));

        //Checks for all circle bumpers
        assertEquals(CIRCLE_BUMPER, shruthi.getElement(5, 7));
        assertEquals(CIRCLE_BUMPER, shruthi.getElement(12, 7));

        //Checks for all triangles
        assertEquals(TRIANGLE_BUMPER_0, shruthi.getElement(5, 14));
        assertEquals(TRIANGLE_BUMPER_0, shruthi.getElement(6, 13));
        assertEquals(TRIANGLE_BUMPER_0, shruthi.getElement(7, 12));

        assertEquals(TRIANGLE_BUMPER_90, shruthi.getElement(15, 14));
        assertEquals(TRIANGLE_BUMPER_90, shruthi.getElement(14, 13));
        assertEquals(TRIANGLE_BUMPER_90, shruthi.getElement(13, 12));

        // Check for all absorbers
        for (int i = 8; i < 12; i++) {
            for (int j = 11; j < 12; j++)
                assertEquals(ABSORBER, shruthi.getElement(i, j));
        } 
    }
    
    /**
     * Tests that a board is properly initialized from userSampleB
     */
    @Test
    public void testBoardFromUserSampleB() {
        //Checks for all square bumpers
        assertEquals(SQUARE_BUMPER, uma.getElement(4, 4));
        assertEquals(SQUARE_BUMPER, uma.getElement(4, 5));
        assertEquals(SQUARE_BUMPER, uma.getElement(4, 6));
        assertEquals(SQUARE_BUMPER, uma.getElement(4, 7));
        assertEquals(SQUARE_BUMPER, uma.getElement(15, 12));

        //Checks for all circle bumpers
        assertEquals(CIRCLE_BUMPER, uma.getElement(14, 6));
        assertEquals(CIRCLE_BUMPER, uma.getElement(19, 7));
        assertEquals(CIRCLE_BUMPER, uma.getElement(14, 8));
        
        // Check for all flippers
        assertEquals(HORIZONTAL_FLIPPER, uma.getElement(4, 14));
        assertEquals(VERTICAL_FLIPPER, uma.getElement(6, 11));

        // Check for all absorbers
        for (int i = 0; i < 20; i++) {
            assertEquals(ABSORBER, uma.getElement(i, 19));
        }
    }
    
    /**
     * Tests that a board is properly initialized from userSampleC
     */
    @Test
    public void testBoardFromUserSampleC() {
        //Checks for all square bumpers
        assertEquals(SQUARE_BUMPER, megs.getElement(13, 7));
        assertEquals(SQUARE_BUMPER, megs.getElement(14, 6));
        assertEquals(SQUARE_BUMPER, megs.getElement(14, 8));
        assertEquals(SQUARE_BUMPER, megs.getElement(15, 5));
        assertEquals(SQUARE_BUMPER, megs.getElement(15, 9));
        assertEquals(SQUARE_BUMPER, megs.getElement(16, 4));
        assertEquals(SQUARE_BUMPER, megs.getElement(16, 10));

        //Checks for all circle bumpers
        assertEquals(CIRCLE_BUMPER, megs.getElement(5, 7));
        assertEquals(CIRCLE_BUMPER, megs.getElement(4, 6));
        assertEquals(CIRCLE_BUMPER, megs.getElement(4, 8));
        assertEquals(CIRCLE_BUMPER, megs.getElement(3, 5));
        assertEquals(CIRCLE_BUMPER, megs.getElement(3, 9));
        assertEquals(CIRCLE_BUMPER, megs.getElement(2, 4));
        assertEquals(CIRCLE_BUMPER, megs.getElement(2, 10));
    
        // Check for all absorbers
        for (int i = 4; i < 15; i++) {
            for (int j = 14; j < 16; j++)
                assertEquals(ABSORBER, megs.getElement(i, j));
        }
    }
}
