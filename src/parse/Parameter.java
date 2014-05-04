package pingball.parse;

import java.util.Comparator;

public class Parameter<T> {
    private String name;
    private T value;
    private int pos;
    
    public Parameter(String name, T value, int pos) {
        this.name = name;
        this.value = value;
        this.pos = pos;
    }
    
    public String getName() { return name; }
    public T getValue() { return value; }
    public int getPos() { return pos; }
}

@SuppressWarnings("rawtypes")
// only working with attribute whose type is invariant
// (pos is int)
class ParameterComparator implements Comparator<Parameter> {
    @Override
    public int compare(Parameter p1, Parameter p2) {
        return (new Integer(p1.getPos())).compareTo(p2.getPos());
    }
}