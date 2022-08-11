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
 * Changes user password.
 */
public class ChangePassword extends MenuAction implements Action {

    /**
     * Creates a ChangePassword with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public ChangePassword(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Changes the user password.
     */
    @Override
    public void run() {
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        um.changePassword(currentUser, password);
        mp.displayAlert(LanguagePresenter.AlertTextType.CHANGEPASSWORD);
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (um.getRole(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }
    }
}