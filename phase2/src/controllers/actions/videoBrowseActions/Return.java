package controllers.actions.videoBrowseActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

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
