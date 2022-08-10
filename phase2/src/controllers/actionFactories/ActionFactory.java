package controllers.actionFactories;

/**
 * An abstract action factory that creates actions base on type.
 */
public interface ActionFactory {
    /**
     * Generates and returns an action base on the type.
     *
     * @param type the type of action
     * @return an action with the given type
     */
    Action getAction(String type);
}
