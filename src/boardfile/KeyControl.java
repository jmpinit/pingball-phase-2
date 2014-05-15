package boardfile;

import game.Gadget;

import java.awt.event.KeyEvent;

/**
 * Simple class that contains information about keyboard keys and how they affect gadgets.
 * @author meghana
 *
 */
public class KeyControl {
    private String key; //Keyboard key that actions are linked to
    private Gadget gadget; //Gadget that activates when key is pressed
    private int eventType; //Whether gadget is activated when key is pressed or released.
    
    /**
     * Creates a new KeyControl object with inputted parameters
     * @param key, keyboard key that actions are dependent on
     * @param g, Gadget that is affected by key
     * @param keyUp, true if action occurs when key is released
     */
    public KeyControl (String key, Gadget g, boolean keyUp) {
        this.key = key;
        this.gadget = g;
        if (keyUp) {
            eventType = KeyEvent.KEY_RELEASED;
        } else {
            eventType = KeyEvent.KEY_PRESSED;
        }
    }
    
    /**
     * Gives String representation of the keyboard key
     * @return key
     */
    public String getKey() {
        return key;
    }
    
    /**
     * Gives Gadget affiliated to key for actions
     * @return Gadget g
     */
    public Gadget getGadget() {
        return gadget;
    }
    
    /**
     * Gives the KeyEvent type, as specified by java.awt.event.KeyEvent
     * @return
     */
    public int getEventType() {
        return eventType;
    }
    
    /**
     * Returns true if gadget is activated when key is initially pressed/goes down.
     * @return true if is key pressed event type
     */
    public boolean isKeyPressed() {
        return (eventType == KeyEvent.KEY_PRESSED);
    }
    
    /**
     * Returns true if gadget is activated when key is released/comes up.
     * @return true if is key released event type
     */
    public boolean isKeyReleased() {
        return (eventType == KeyEvent.KEY_RELEASED);
    }
    
    /**
     * Checks for equality between passed in object
     * @param obj, Object to check for equality with
     * @return true if two objects are equal
     */
    @Override public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass())
            return false;
        KeyControl other = (KeyControl) obj;
        if (this.key.equals(other.getKey()))
            if (this.eventType == other.getEventType())
                if (this.gadget.equals(other.getGadget()))
                    return true;
        return false;
    }
    
    @Override public String toString() {
        return this.getKey()+":"+this.gadget.getName();
    }

}
