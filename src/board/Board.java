package skeletonSpecs;

import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import antlr.*;
import physics.*;

/**
 * 
 *
 */
public class Board {

    /**
     * Thread Safety Information: Board is threadsafe because clients threads never modify the board; they only access it.
     */

    private final int width = 20; //horizontal dimension
    private final int height = 20; //vertical dimension
    private String name;
    
    private double gravity = 25;
    private double friction1 = 0.025;
    private double friction2 = 0.025;

    private final Gadget[][] gadgets;
    private final OuterWalls outerWall = new OuterWalls();
    
    //Various inputs from the file neccessary for other classes.
    private Set<Ball> ballsFromFile = new HashSet<Ball>();
    private HashMap<Gadget,Set<Gadget>> fireMap = new HashMap<Gadget,Set<Gadget>>();
    Set<Gadget> gadgetList= new HashSet<Gadget>();


    /** Constructs a new board with a default, predetermined size of 20 by 20 */
    public Board(double grav, String n) {
        name = n;
        gravity = grav;
        gadgets = new Gadget[width][height];
    }
    
    /** 
     * Constructs a new board with given frictions, name, and gravity values
     * @param friction1
     * @param friction2
     * @param  name
     * @param gravity
     */
    public Board(double fric1, double fric2, String n, double grav) {
        name = n;
        friction1 = fric1;
        friction2 = fric2;
        gravity = grav;
        gadgets = new Gadget[width][height];
    }
    
    /** 
     * Constructs a new board with a default, predetermined size of 20 by 20 and
     * default gravity value of 25. 
     */
    public Board() {
        name = "default";
        gadgets = new Gadget[width][height];
    }
    
    /** Constructs a new board with a default, predetermined size of 20 by 20 and default gravity value of 25.
     *  Adds bumpers and flippers as defined within a given file.
     * @throws IOException if file not found.
     */
    public Board(String filePath) throws IOException {
        gadgets = new Gadget[width][height];
        
        FileReader reader = new FileReader(filePath);
        CharStream stream = new ANTLRInputStream(reader);
       fileInputLexer lexer = new fileInputLexer(stream);
        lexer.reportErrorsAsExceptions();
        TokenStream tokens = new CommonTokenStream(lexer);
        
        // Feed the tokens into the parser.
        fileInputParser parser = new fileInputParser(tokens);
        parser.reportErrorsAsExceptions();
        
        ParseTree tree = parser.root();
        ((RuleContext) tree).inspect(parser);
        
        ParseTreeWalker walker = new ParseTreeWalker();
        FileListener listener = new FileListener();
        walker.walk(listener, tree);
        
        //Instantiate actual board
        Map<String,Object> boardVariables = listener.getBoardVar();
        if (boardVariables.containsKey("friction1")) {
            this.friction1 = (double) boardVariables.get("friction1");
        }
  
        if (boardVariables.containsKey("friction2")) {
            this.friction2 = (double) boardVariables.get("friction2");
        }
        
        if (boardVariables.containsKey("gravity")) {
            this.gravity = (double) boardVariables.get("gravity");
        }
        this.name = (String) boardVariables.get("name");
         
        //Pull other neccesary input from board
        ballsFromFile = listener.getBallsFromFile();
        Map<String,Set<String>> stringedFireMap = listener.getFireMap();
        gadgetList= listener.getGadgetFromFile();

        this.createFireMap(stringedFireMap);
        
        
        //Add gadgets to board
        for (Gadget g : gadgetList) {
            this.addGadget(g);
        }

    }



    
    /**
     * Returns a string representation of the board.
     * Square bumpers are denoted by "#"
     * Circle bumpers are denoted by "0"
     * Triangle bumpers are denoted by "/" for orientation 0 or 180 or "\" for orientation 90 or 270
     * Flippers are denoted by "|" when vertical or "-" when horizontal
     * Absorbers are denoted by "="
     * Outer walls are denoted by "."
     * @return string representation of board
     */
    public String toString() {
        String result = "";

        String[][] gameBoard = new String[width+2][height+2];

        for (int j = 0; j < gadgets[0].length; j++) {
            for (int i = 0; i < gadgets.length; i++) {
                Gadget g = gadgets[i][j];
                if (g != null) {
                    //Takes care of all the flipper printing since they take up four squares.
                    if (g.type().equals("flipper"))
                    {
                        if ((g.getX() == i) && (g.getY() == j)) {
                            Flipper f = (Flipper)g;
                            String orientation = f.getOrientation();

                            if (orientation.equals("top")) {
                                gameBoard[i][j] = f.toString();
                                gameBoard[i+1][j] = f.toString();
                            } else if (orientation.equals("bottom")) {
                                gameBoard[i][j+1] = f.toString();
                                gameBoard[i+1][j+1] = f.toString();
                            } else if (orientation.equals("left")) {
                                gameBoard[i][j] = f.toString();
                                gameBoard[i][j+1] = f.toString();
                            } else {
                                gameBoard[i+1][j] = f.toString();
                                gameBoard[i+1][j+1] = f.toString();          
                            }
                        }
                    } else {
                        gameBoard[i][j] = g.toString();
                    }
                }
            }
        }
        //Does outer wall toString
        gameBoard = this.wallHelper(gameBoard);
        

        
        //Finally takes the gameBoard and turns it into the returned String        
        for (int j = 0; j < gameBoard[0].length; j++) {
            for (int i = 0; i < gameBoard.length; i++) {

                //If just empty space
                if (gameBoard[i][j] == null) {
                    result += " ";
                } else {
                    result += gameBoard[i][j];
                }
            }
            result += "\n";
        }
        return result;
    }
    
    
    /**
     * Returns a string representation of the board with the balls inputted into the method.
     * Square bumpers are denoted by "#"
     * Circle bumpers are denoted by "0"
     * Triangle bumpers are denoted by "/" for orientation 0 or 180 or "\" for orientation 90 or 270
     * Flippers are denoted by "|" when vertical or "-" when horizontal
     * Absorbers are denoted by "="
     * Outer walls are denoted by "."
     * Balls are denoted by "*"
     * @param set of balls that should also be displayed on the board. Does not display any balls outside the coordinates of the board.
     * @return string representation of board
     */
    public String viewBoard(Set<Ball> balls) {
        String result = "";

        String[][] gameBoard = new String[width+2][height+2];


        for (int j = 0; j < gadgets[0].length; j++) {
            for (int i = 0; i < gadgets.length; i++) {
                Gadget g = gadgets[i][j];
                if (g != null) {
                    //Takes care of all the flipper printing since they take up four squares.
                    if (g.type().equals("flipper"))
                    {
                        if ((g.getX() == i) && (g.getY() == j)) {
                            Flipper f = (Flipper)g;
                            String orientation = f.getOrientation();

                            if (orientation.equals("top")) {
                                gameBoard[i][j] = f.toString();
                                gameBoard[i+1][j] = f.toString();
                            } else if (orientation.equals("bottom")) {
                                gameBoard[i][j+1] = f.toString();
                                gameBoard[i+1][j+1] = f.toString();
                            } else if (orientation.equals("left")) {
                                gameBoard[i][j] = f.toString();
                                gameBoard[i][j+1] = f.toString();
                            } else {
                                gameBoard[i+1][j] = f.toString();
                                gameBoard[i+1][j+1] = f.toString();          
                            }
                        }
                    } else {
                        gameBoard[i][j] = g.toString();
                    }
                }
            }
        }
        
        //Does outer wall toString
        gameBoard = this.wallHelper(gameBoard);
        

        
        
        //Show all the balls currently on the board
        for (Ball b: balls) {
            int ballX = (int) Math.floor(b.getPos().x());
            int ballY = (int) Math.floor(b.getPos().y());
            if (inBoard(ballX,ballY)) {
                gameBoard[ballX][ballY] = "*"; 
            }
        }

        
        for (int j = 0; j < gameBoard[0].length; j++) {
            for (int i = 0; i < gameBoard.length; i++) {
                //If just empty space
                if (gameBoard[i][j] == null) {
                    result += " ";
                } else {
                    result += gameBoard[i][j];
                }
            }
            result += "\n";
        }
        return result.trim();
    }
    
    
    /**
     * Returns a string representation of what's in the (x,y) location if it's a 
     * legitimate location on the board. 
     * @param x, x-coordinate of location
     * @param y, y-coordinate of location
     * @return string representation of what's in (x,y)
     */
    public String getElement(int x, int y) {
        if (inBoard(x,y)) {
            if (gadgets[x][y] != null) {
                return gadgets[x][y].toString();
            } else {
                return " ";
            }      
        }
        return "";
    }
    
    /**
     * Returns the gadget at the specified location iff coordinates are in the board or denote an outer wall.
     * Returns null otherwise.
     * @param x-coordinate
     * @param y-coordinate
     * @return gadget at coordinates (x,y)
     */
    public Gadget getGadget(int x, int y) {
        if (inBoard(x,y)) {
            if (gadgets[x][y] != null) {
                return gadgets[x][y];
            }
        
        } else if (x == -1 || y == -1 || x == 20 || y == 20) {
            return this.outerWall;
        }
      return null;
    }
    
    /**
     * Changes visibility of outer walls depending 
     */
    public void changeOuterVisibility(String wallName, String otherBoard) {
        this.outerWall.changeWallVisibility(wallName, otherBoard);
    }
    
/********** Accessors for any instance variables **********/
    
    /**
     * Returns the size of the board 
     * @return size of the board
     */
    public double getSize() {
        return width;
    }
    
    /**
     * Returns the friction co-efficient mu
     * @return friction1
     */
    public double getMuOne() {
        return friction1;
    }
    
    /**
     * Returns the friction co-efficient mu1
     * @return friction2
     */
    public double getMuTwo() {
        return friction2;
    }
    
    /**
     * Returns gravity
     * @return gravity
     */
    public double getGravity() {
        return gravity;
    }
    
    
    /**
     * Returns name of the board in a String.
     * @return name of the board
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of balls specified in the file constructor
     * @return list of balls
     */
    public Set<Ball> getBallsFromFile() {
        return ballsFromFile;
    }
    
    /**
     * Returns information obtained by parsing the 'fire' lines in a Board file.
     * @return a map where Gadget A is mapped to all the gadgets that fire upon A being triggered.
     */
    public Map<Gadget,Set<Gadget>> getFireMap() {
        return fireMap;
    }
    
    
/********** Various helper functions **********/
    
    
    
    /**
     * Takes a string representation of the board and adds the outer walls to it.
     * If the board is joined to another, it adds the name of the conjoined board to the respective
     * outer wall. 
     * @param the working String board array to be editted 
     * @return String array representation of the board
     */
    public String[][] wallHelper(String[][] gameBoard) {
        Map<String, String> wallMapping = outerWall.getWallMapping();
        
        //Adding outer walls for toString purposes
            
        //Check top wall 
        String wall = this.wallFormat("top", wallMapping);
        wall = wall.substring(0,gameBoard.length);
        for (int i = 0; i < wall.length(); i++)
            gameBoard[i][0] = ""+ wall.charAt(i);

        //Check bottom wall 
        wall = this.wallFormat("bottom", wallMapping);
        wall = wall.substring(0,gameBoard.length);
        for (int i = 0; i < wall.length(); i++)
            gameBoard[i][gameBoard[i].length-1] = ""+ wall.charAt(i);
        
        //Check left wall 
         wall = this.wallFormat("left", wallMapping);
        wall = wall.substring(0,gameBoard.length);
        for (int i = 0; i < wall.length(); i++)
            gameBoard[0][i] = ""+ wall.charAt(i);
        
        //Check right wall 
         wall = this.wallFormat("right", wallMapping);
        wall = wall.substring(0,gameBoard.length);
        for (int i = 0; i < wall.length(); i++)
            gameBoard[gameBoard.length-1][i] = ""+ wall.charAt(i);
        
        return gameBoard;
  
    }
    
    /** Helper function to display the outer walls. 
     * Handles formatting the name for the wall.
     */
    private String wallFormat (String side, Map<String, String> wallMapping) {
        String name = wallMapping.get(side);
        int stringBoardWidth = width+2;
        if (name.length() > stringBoardWidth) {
            name = name.substring(0,width);
        } else {
            while (name.length() < stringBoardWidth) {
                name += ".";
            } 
        }
        name = "." + name;
        name +=".";
        return name;
    }
    
    
    /**
     * Adds a gadget-type to its proper location on the board.
     * Location is determined by the coordinates of the gadget itself.
     * @param toAdd, new gadget to add to board
     */
    public void addGadget(Gadget toAdd) {
        if (toAdd.type().equals("absorber")) {
            this.addAbsorber((Absorber) toAdd);
        } else if (toAdd.type().equals("flipper")){
            this.addFlipper((Flipper) toAdd);
        } else {
            int x = (int) Math.floor(toAdd.getX());
            int y = (int) Math.floor(toAdd.getY());
            if (inBoard(x, y)) {
                gadgets[x][y] = toAdd;
            }
        }

        
    }
    
    /**
     * Helper function for addGadget that deals with adding flippers to the board.
     * Flippers are unique since they take four squares on a board. Location is determined by the coordinates of the gadget itself.
     * @param toAdd, new gadget to add to board
     */
    private void addFlipper(Flipper toAdd) {
        int x = (int) Math.floor(toAdd.getX());
        int y = (int) Math.floor(toAdd.getY());
        gadgets[x][y]     = toAdd;
        gadgets[x+1][y]   = toAdd;
        gadgets[x][y+1]   = toAdd;
        gadgets[x+1][y+1] = toAdd;
    }
    
    /**
     * Helper function for addGadget that deals with adding absorbers to the board.
     * Flippers are unique since they have a length and width. Starting location is determined by the coordinates of the gadget itself.
     * @param toAdd, new gadget to add to board
     */
    private void addAbsorber(Absorber toAdd) {
        int width = toAdd.getWidth();
        int height = toAdd.getHeight();
        int x = (int) Math.floor(toAdd.getX());
        int y = (int) Math.floor(toAdd.getY());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                    gadgets[x+i][y+j] = toAdd;
                
            }
        }      
    }
    
    /**
     * Takes the String-mapped input from the file and creates a map. Uses the names of the gadget to create the map.
     * This created map is such that Gadget A is mapped to all the gadgets that fire upon A being triggered.
     */
    private void createFireMap(Map<String,Set<String>> stringMap) {   
        for (String key : stringMap.keySet()) {
            Gadget keyGadget = this.findGadget(key);
            if (keyGadget != null) {
                Iterator iter = stringMap.get(key).iterator();
                Set<Gadget> values = new HashSet<Gadget>();
                while (iter.hasNext()) {
                    Gadget toAdd = this.findGadget((String) iter.next());
                    values.add(toAdd);
                }
                
                fireMap.put(keyGadget, values);
                
                //It's an absorber, check if it triggers itself
                if (keyGadget.toString().equals("=")) {
                    Absorber abs = (Absorber) checkAbsorber(values,(Absorber)keyGadget);
                    
                    //Find and delete duplicate absorber
                    String absName = abs.getName();

                    Iterator i = gadgetList.iterator();
                    while(i.hasNext()) {
                        Gadget g = (Gadget) i.next();
                        String name = g.getName();
                        if (name.equals(absName)) {
                            i.remove();
                        }
                    }
                    //Add in our new absorber!
                    gadgetList.add(abs);

                }
                
            }

        }      
    }
    
    /**
     * Sets the absorber to self-triggering if found in a map denoting gadget relations.
     * Returns the modified absorber.
     * @param values - All the gadgets related to the Absorber key
     * @param key - Absorber we're checking to see if self-triggering
     */
    private Gadget checkAbsorber(Set<Gadget> values, Absorber key) {
        String keyName = key.getName();
        Iterator iter = values.iterator();
        while(iter.hasNext()) {
            Gadget currentVal = (Gadget) iter.next();
            if (currentVal.getName().equals(keyName)) {
                key.setTriggerable(true);
            }
        }
        return key;
    }
    
    /**
     * Searches for a Gadget by name, returns if found. Returns null otherwise.
     * @param name of Gadget to find
     * @return the Gadget with specified name
     */
    private Gadget findGadget(String name) {
        HashSet<Gadget> gadgets = (HashSet<Gadget>) gadgetList;
        for (Gadget gadget : gadgets) {
            if (gadget.getName().equals(name)) {
                return gadget;
            }
        }
        return null;
    }
    
    /**
     * Checks that a given (x,y) coordinate is within the bounds of the board. 
     * Does not take into account outer walls.
     * @param x-coordinate
     * @param y-coordinate
     * @return true or false depending if in board
     */
    public boolean inBoard(int x, int y) {
        return (x >= 0 && x < (width) && y >= 0 && y < (height));
    }
    
    
    public void checkRep() {
        assert (gadgets.length == width);
    }
    
}
