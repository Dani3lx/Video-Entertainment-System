package controllers.action.actionFactories;

public interface ActionFactory {
    Action getAction(String type);
}
