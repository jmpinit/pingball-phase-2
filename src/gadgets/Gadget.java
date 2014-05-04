package skeletonSpecs;

/*
 * Gadget superclass OR interface, have lots of implementing/extending gadget classes.
 * For now, has been implemented as an interface
 */

/**
 * Thread safety: Synchronize most* methods to ensure no more than one Ball is
 * accessing them at one time. In cases where there are multiple balls
 * interacting with the gadget at once, the higher-level program using the
 * gadget will run both actions separately.
 * 
 * *Some objects, such as a Square bumper, can safely interact with multiple
 * objects, so they may not have to synchronize their action methods. In fact,
 * the only one I can see needs a reasonable amount of synchronicity is the
 * flipper.
 * 
 */
public interface Gadget {
    // instance variables should include the following plus any other
    // class-unique variables

    // private final double reflCoeff - the reflection coefficient of said
    // gadget
    // private boolean triggered - lets you know if the gadget is in the process
    // of performing an action.

    /**
     * Decides whether the gadget has been triggered
     * 
     * @param ball
     *            the Ball object inside this.containingSquare that may be
     *            interacting with the gadget
     * @return the time before the trigger will occur (returns some ridiculous
     *         number if irrelevant)
     */
    public double trigger(Ball ball);

    /**
     * Performs the action required once a trigger has occurred. This could
     * involve changing the velocity/angle/position of the ball. (NOTE: The
     * freezing may be necessary when multiple balls are interacting with one
     * gadget: it is just there to serve as a reminder.)
     * 
     * @param ball
     *            the Ball object inside this.containingSquare that is
     *            interacting with the gadget
     */
    public void action(Ball ball);

    /**
     * Gets the reflection coefficient of the gadget.
     * 
     * @return this.reflCoeff
     */
    public double getReflCoeff();

    /**
     * @return x coordinate of the upper-leftmost point of the gadget
     */
    public double getX();

    /**
     * @return y coordinate of the upper-leftmost point of the gadget
     */
    public double getY();

    /**
     * @return name of the gadget
     */
    public String getName();

    /**
     * @return type of the gadget
     */
    public String type();

}
