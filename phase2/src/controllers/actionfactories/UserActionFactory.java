package controllers.actionfactories;

import controllers.actions.useractions.*;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.userprompt.UserPrompt;

/**
 * A concrete action factory that creates user related actions base on type.
 */
public class UserActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    /**
     * Creates a user action factory with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public UserActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Generates and returns an action base on the type.
     *
     * @param type the type of action
     * @return an action with the given type
     */
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