package controllers.action.actionFactories;

import controllers.action.actions.userMenu.*;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

public class UserActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

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
            case "view playlist":
                return new ViewPlaylist(userPrompt, user, lp, mp);
            case "video studio":
                return new VideoStudio(userPrompt, user, lp, mp);
            case "create admin user":
                return new CreateAdminUser(userPrompt, user, lp, mp);
            case "delete user":
                return new DeleteUser(userPrompt, user, lp, mp);
            case "ban user":
                return new BanUser(userPrompt, user, lp, mp);
            case "unban user":
                return new UnbanUser(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}