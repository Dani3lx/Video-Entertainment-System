package controllers.actions.videobrowseactions;

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
 * Return to user menu.
 */
public class Return extends MenuAction implements Action {
    private final MenuFactory userMenuFactory;

    /**
     * Creates a Return with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public Return(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        userMenuFactory = new UserMenuFactory(userPrompt, user, lp, mp);
        currentUser = user;
    }

    /**
     * Navigates to the next menu.
     */
    public void run() {
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        if (um.getRole(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }
    }
}
