package pingball.server;

import gameParts.*;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for server side stuff
 * @author yqlu
 *
 * All the concurrency / message-passing tests below were performed manually but not automated.
 * Their results were documented in the comments as appropriate.
 *
 * Extensive partitioning strategy of things to check:
 * 
 * Single-player:
 * 	- activated correctly when no "--host" flag nor "--port" flag is provided
 *  - board constructor works correctly (correct sample board is displayed on screen)
 * 
 * Multi-player:
 *  - activated correctly when "--host" flag or "--port" flags are provided
 *  - 1 client, >1 client
 *  - server correctly keeps track of names of clients
 *  	- as more clients come on
 *  	- as clients disconnect
 *  - server correctly transmits gluing instructions (from System.in) to clients
 *  	- gluing instruction for distinct boards
 *  		- up / down
 *  		- left / right
 *  	- gluing instruction from board to itself
 *  	- gluing instruction overriding already glued boards
 *  - server correctly updates clients when other clients disconnect
 *  - gluing is undone when clients disconnect
 *  - gluing is non-persistent when same client subsequently connects
 *  - balls transfer correctly between boards
 * 
 */

public class ServerTest {

    @BeforeClass
    public static void setUpBeforeClass() {
    }

    @Before public void setUp() {
    }

    @Test public void boardConstructorWorksSinglePlayer() {
    	// see unitTests in BoardFactoryTests.
        }    
    
    @Test public void boardConstructorWorksMultiPlayer() {
    	// see unitTests in BoardFactoryTests.
        }    

    @Test public void serverRecordsSingleClientsName() {
    	// manually tested: server prints out current online named clients correctly
        }    

    @Test public void serverCorrectlyHandlesSingleDisconnect() {
    	// when single board disconnects, server prints out its name correctly.
        }    
    
    @Test public void serverRecordsManyClientsName() {
    	// manually tested: server prints out current online named clients correctly
        }    
    
    @Test public void serverCorrectlyHandlesMultipleDisconnects() {
    	// when client disconnects, other clients which are joined set their walls to transparent again.
        }    

    @Test public void serverGluesDistinctLeftRight() {
    	// 'h NAME1 NAME2' fed into System.in correctly updates boards to glue left and right.
        }    
    
    @Test public void serverGluesDistinctUpDown() {
    	// 'v NAME1 NAME2' fed into System.in correctly updates boards to glue left and right.
        }    

    @Test public void serverGluesSameBoardToItself() {
    	// 'v NAME1 NAME2' fed into System.in correctly updates top of board to glue to bottom of board.
        }    
    
    @Test public void serverGluesOverridingPrevious() {
    	// 'v NAME1 NAME2' followed by 'v NAME1 NAME3' correctly glues board1 and board3 and unglues board1 and board2.
        }    

    @Test public void serverGluesNonPersistentUponDisconnect() {
    	// When board1 and board2 are glued and then board1 disconnects and connects again,
    	// server treats board1 and board2 as unglued.
        }    
    
    @Test public void ballsTransferBetweenBoards() {
    	// see unit tests in BoardTests.
        }    
    
}