package controllers.actionfactories;

/**
 * A user's action.
 */
public interface Action {
    /**
     * Runs the action.
     */
    void run();

    /**
     * Goes to the next action.
     */
    void next();
}
