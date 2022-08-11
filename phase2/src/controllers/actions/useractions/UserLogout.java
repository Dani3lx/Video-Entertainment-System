package controllers.actions.useractions;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.UserPrompt;

/**
 * Logs a user out.
 */
public class UserLogout extends MenuAction implements Action {

    private final MenuFactory userMenuFactory;

    /**
     * Creates a UserLogout with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public UserLogout(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        userMenuFactory = new UserMenuFactory(userPrompt, user, lp, mp);
    }

    /**
     * Logs out user.
     */
    public void run() {
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    public void next() {
        userMenuFactory.getMenu(MenuEnums.START).run();
    }
}
