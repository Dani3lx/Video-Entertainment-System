package controllers.action.actionFactories;

import controllers.action.actions.userMenu.BrowseVideo;
import controllers.action.actions.userMenu.ChangePassword;
import controllers.action.actions.userMenu.CheckLoginHistory;
import controllers.action.actions.userMenu.UserLogout;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

public class UserActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    UserPrompt userPrompt;

    public UserActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    public Action getAction(String type) {
        switch (type) {
            case "logout":
                return new UserLogout(userPrompt, user, lp, mp);
            case "browse video":
                return new BrowseVideo(userPrompt, user, lp, mp);
            case "change password":
                return new ChangePassword(userPrompt, user, lp, mp);
            case "check history":
                return new CheckLoginHistory(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}