package controllers.action.actionFactories;

import entities.User;

public class PlaylistActionFactory implements ActionFactory {
    private final User user;

    public PlaylistActionFactory(User user) {
        this.user = user;
    }

    public Action getAction(String type) {
        switch (type) {
            default:
                return null;
        }
    }
}