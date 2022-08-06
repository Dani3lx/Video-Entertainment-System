package controllers.action.actionFactories;

import controllers.action.actions.userMenu.BrowseVideo;
import controllers.action.actions.userMenu.ChangePassword;
import controllers.action.actions.userMenu.UserLogout;
import entities.User;

public class UserActionFactory implements ActionFactory {
    private final User user;

    public UserActionFactory(User user) {
        this.user = user;
    }

    public Action getAction(String type) {
        switch (type) {
            case "logout":
                return new UserLogout(user);
            case "browse video":
                return new BrowseVideo(user);
            case "change password":
                return new ChangePassword(user);
            default:
                return null;
        }
    }
}