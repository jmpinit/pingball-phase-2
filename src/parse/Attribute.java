package pingball.parse;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum Attribute {
    NAME("name", String.class),
    TRIGGER("trigger", String.class),
    ACTION("action", String.class),
    WIDTH("width", Integer.class),
    HEIGHT("height", Integer.class),
    PRECISE_X("x", Double.class),
    PRECISE_Y("y", Double.class),
    INT_X("x", Integer.class),
    INT_Y("y", Integer.class),
    XVELOCITY("xVelocity", Double.class),
    YVELOCITY("yVelocity", Double.class),
    FRICTION1("friction1", Double.class),
    FRICTION2("friction2", Double.class),
    GRAVITY("gravity", Double.class),
    ORIENTATION("orientation", Integer.class);
    
    private Class type;
    private String keyword;
    
    Attribute(String keyword, Class type) {
        this.keyword = keyword;
        this.type = type;
    }
    
    public Class getType() {
        return type;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    static Pattern pattern;
    static {
        String regText = "([^\\s\\r\\n]+)";
        
        String attributeRegex = "";
        for(Attribute a: Attribute.values()) {
            attributeRegex += a.getKeyword() + "|";
        }
        
        pattern = Pattern.compile("(" + attributeRegex + ")" + "\\s*=\\s*" + regText);
    }
    
    static Map<String, Attribute> keywordToAttribute;
    static {
        keywordToAttribute = new HashMap<String, Attribute>();
        
        for(Attribute a: Attribute.values()) {
            keywordToAttribute.put(a.getKeyword(), a);
        }
    }
}
