package client;

public class PingKeyEvent {
    private char key;
    private boolean state;
    
    public PingKeyEvent(char key, boolean state) {
        this.key = key;
        this.state = state;
    }
    
    public char getKey() {
        return key;
    }
    
    public boolean getState() {
        return state;
    }
}
