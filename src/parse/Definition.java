package pingball.parse;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import pingball.gadgets.Absorber;
import pingball.gadgets.Ball;
import pingball.gadgets.CircularBumper;
import pingball.gadgets.LeftFlipper;
import pingball.gadgets.RightFlipper;
import pingball.gadgets.SquareBumper;
import pingball.gadgets.TriangularBumper;
import pingball.simulation.Board;

public enum Definition {
    BOARD("board", Board.class, new Attribute[] {
        Attribute.NAME, Attribute.GRAVITY, Attribute.FRICTION1, Attribute.FRICTION2
    }),
    BALL("ball", Ball.class, new Attribute[] {
        Attribute.NAME, Attribute.PRECISE_X, Attribute.PRECISE_Y, Attribute.XVELOCITY, Attribute.YVELOCITY
    }),
    FIRE("fire", Fire.class, new Attribute[] {
        Attribute.TRIGGER, Attribute.ACTION
    }),
    SQUAREBUMPER("squareBumper", SquareBumper.class, new Attribute[] {
        Attribute.NAME, Attribute.INT_X, Attribute.INT_Y
    }),
    CIRCLEBUMPER("circleBumper", CircularBumper.class, new Attribute[] {
        Attribute.NAME, Attribute.INT_X, Attribute.INT_Y
    }),
    TRIANGLEBUMPER("triangleBumper", TriangularBumper.class, new Attribute[] {
        Attribute.NAME, Attribute.INT_X, Attribute.INT_Y, Attribute.ORIENTATION
    }),
    RIGHTFLIPPER("rightFlipper", RightFlipper.class, new Attribute[] {
        Attribute.NAME, Attribute.INT_X, Attribute.INT_Y, Attribute.ORIENTATION
    }),
    LEFTFLIPPER("leftFlipper", LeftFlipper.class, new Attribute[] {
        Attribute.NAME, Attribute.INT_X, Attribute.INT_Y, Attribute.ORIENTATION
    }),
    ABSORBER("absorber", Absorber.class, new Attribute[] {
        Attribute.NAME, Attribute.INT_X, Attribute.INT_Y, Attribute.WIDTH, Attribute.HEIGHT
    });
    
    private Class source;
    private String keyword;
    private Attribute[] attributes;
    
    Definition(String keyword, Class source, Attribute[] attributes) {
        this.keyword = keyword;
        this.source = source;
        this.attributes = attributes;
    }
    
    public Class getSource() {
        return source;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public Attribute[] getAttributes() {
        return attributes;
    }
    
    static Pattern pattern;
    static {
        String definitionRegex = "";
        for(Definition a: Definition.values()) {
            definitionRegex += a.getKeyword() + "|";
        }
        
        pattern = Pattern.compile("^(" + definitionRegex + ")");
    }
    
    static Map<String, Definition> keywordToDefinition;
    static {
        keywordToDefinition = new HashMap<String, Definition>();
        
        for(Definition a: Definition.values()) {
            keywordToDefinition.put(a.getKeyword(), a);
        }
    }
}
