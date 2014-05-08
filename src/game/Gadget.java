package game;

/**
 * A Gadget is a game piece localized to a specific position on a specific board,
 * and having a single action that it does when told
 * and a particular way of updating itself and a ball during a collision between the two. 
 * 
 * @author pkalluri
 *
 */
public interface Gadget extends GamePiece {
    
    /***
     * Does this gadget's action.
     */
    public void doAction();

}
