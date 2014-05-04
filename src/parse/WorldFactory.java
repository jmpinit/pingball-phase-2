package pingball.parse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import pingball.gadgets.*;
import pingball.simulation.Board;
import pingball.simulation.Board.OverlappingGadgetException;
import pingball.simulation.World;

public class WorldFactory {
    private World world;
    private Board board;
    private List<Ball> balls;
    private List<Gadget> gadgets;
    
    public WorldFactory(File boardFile) {
        world = new World();
        balls = new ArrayList<Ball>();
        gadgets = new ArrayList<Gadget>();
        
        // read board file
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(boardFile));

            String line;
            while ((line = reader.readLine()) != null) {
                String cleaned = line.split("#")[0];
                String trimmed = cleaned.trim();
                if(trimmed.length() > 0 && trimmed.charAt(0) == '#')
                    trimmed = "";
                
                if(!trimmed.equals(""))
                    parseLine(trimmed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(reader != null) reader.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
        
        // pull it all together
        for(Gadget g: gadgets) {
            board.addGadget(g);
        }
        
        try {
            board.checkRep();
        } catch(OverlappingGadgetException e) {
            throw new InvalidBoardException("Invalid board file. Gadgets overlap");
        }
        
        for(Ball b: balls) {
            world.addBall(b);
        }
        
        world.addBoard(board);
    }
    
    public World getWorld() {
        return world;
    }

    public void parseLine(String line) {
        Matcher definitionMatcher = Definition.pattern.matcher(line);
        Matcher attributeMatcher = Attribute.pattern.matcher(line);
        
        /*
         * Identify the definition type
         */
        
        if(!definitionMatcher.find())
            throw new RuntimeException("Line must define a gadget, ball, board, or \"fire\" relationship.");
        
        String defKeyword = definitionMatcher.group(0);
        
        if(!Definition.keywordToDefinition.containsKey(defKeyword))
            throw new ParseException("Unknown definition type.", line);
        
        Definition definition = Definition.keywordToDefinition.get(defKeyword);
        
        /*
         * Prepare for reflection
         */
        
        Constructor constructor = definition.getSource().getConstructors()[0];
        Class[] argTypes = constructor.getParameterTypes();
        
        /*
         * Grab arguments for construction
         */
        List<Object> arguments = new ArrayList<Object>();
        int attributeIndex = 0;
        while(attributeMatcher.find()) {
            try {
                String attrKeyword = attributeMatcher.group(1);
                Attribute attribute = definition.getAttributes()[attributeIndex++];
                
                String rawValue = attributeMatcher.group(2);
                Object value = getParamValue(rawValue);
                
                // cast to lower precision int if necessary
                if(attribute.getType().equals(Integer.class) && value.getClass().equals(Double.class))
                    value = new Integer(((Double)value).intValue());
                
                if(!value.getClass().isAssignableFrom(attribute.getType()))
                    throw new ParseException("Attribute value has wrong type. Expecting " + attribute.getType().getName() + ".", line);
                
                arguments.add(value);
            } catch(IndexOutOfBoundsException e) {
                throw new ParseException("Malformed attribute.", line);
            }
        }
        
        /*
         * Construct
         */
        
        Object definedObject = null;
        
        if(definition != Definition.BOARD) { // board has optional parameters @1020
            int fileArgsCount = argTypes.length;
            if(argTypes[0] == World.class) fileArgsCount--;
            
            if(fileArgsCount > arguments.size()) {
                throw new ParseException("Expecting more arguments.", line);
            } else if(argTypes.length < arguments.size()){
                throw new ParseException("Too many arguments.", line);
            }
        }
        
        try {
            if(definition == Definition.BOARD) {
                Constructor boardConstructor;
                
                if(arguments.get(0).getClass() == Double.class) {
                    double[] dArgs = new double[arguments.size()];
                    for(int i = 0; i < dArgs.length; i++) {
                        dArgs[i] = (double)arguments.get(i);
                    }
                    
                    definedObject = new Board(world, dArgs);
                } else {
                    String name = (String)arguments.get(0);
                    
                    List<Object> dArgsSource = arguments.subList(1, arguments.size());
                    
                    double[] dArgs = new double[dArgsSource.size()];
                    for(int i = 0; i < dArgs.length; i++) {
                        dArgs[i] = (double)dArgsSource.get(i);
                    }
                    
                    definedObject = new Board(world, name, dArgs);
                }
            } else {
                switch(definition) {
                    case BALL:
                    case FIRE:
                        definedObject = constructor.newInstance(arguments.toArray());
                        break;
                    default:
                        arguments.add(0, world);
                        definedObject = constructor.newInstance(arguments.toArray());
                }
            }
        } catch(IllegalArgumentException e) {
            for(Object o: arguments)
                System.out.println(o.getClass());
            throw new ParseException("Argument type mismatch", line);
        } catch(InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException("Gadget constructor threw exception.");
        } catch(InstantiationException e) {
            throw new RuntimeException("Underlying class is abstract.");
        } catch(IllegalAccessException e) {
            throw new RuntimeException("Security manager prevents reflection.");
        }
        
        /*
         * Organize
         */
        
        if(definedObject instanceof Board) {
            board = (Board)definedObject;
            //System.out.println(board.toString());
        } else if(definedObject instanceof Ball) {
            balls.add((Ball)definedObject);
            //System.out.println("added Ball");
        } else if(definedObject instanceof Fire) {
            Fire connection = (Fire)definedObject;
            
            // find gadgets from name
            Gadget trigger = null;
            Gadget action = null;
            for(Gadget g: gadgets) {
                if(g.getName().equals(connection.getTrigger()))
                    trigger = g;
                if(g.getName().equals(connection.getAction()))
                    action = g;
            }
            
            if(trigger == null)
                throw new ParseException("Trigger gadget does not exist.", line);
            if(action == null)
                throw new ParseException("Action gadget does not exist.", line);
            
            trigger.registerListener(action);
        } else {
            gadgets.add((Gadget)definedObject);
            //System.out.println("added " + definedObject.getClass().getName());
        }
    }
    
    private Object getParamValue(String rawValue) {
        try {
            return Double.parseDouble(rawValue);
        } catch(NumberFormatException e) {
            return rawValue;
        }
    }
}
