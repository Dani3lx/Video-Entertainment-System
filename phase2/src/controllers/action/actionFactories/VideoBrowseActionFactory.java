package controllers.action.actionFactories;

import controllers.action.actions.videoBrowseMenu.Return;
import entities.User;


public class VideoBrowseActionFactory implements ActionFactory {
    private final User user;

    public VideoBrowseActionFactory(User user) {
        this.user = user;
    }

    public Action getAction(String type) {
        switch (type) {
            case "return":
                return new Return(user);
            default:
                return null;
        }
    }
}