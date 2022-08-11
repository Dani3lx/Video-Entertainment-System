package controllers.actions.useractions;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import usecase.runtimedatamanager.AdminManager;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.UserPrompt;

/**
 * Deletes a user.
 */
public class DeleteUser extends MenuAction implements Action {

    private boolean self_delete;

    /**
     * Creates a DeleteUser with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public DeleteUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    /**
     * Deletes a user.
     */
    @Override
    public void run() {
        AdminManager am = new AdminManager();

        // Displays all users and asks for a username as input to delete the user
        mp.displayAlert(LanguagePresenter.AlertTextType.ALLUSERS);
        mp.displayList(am.returnUsers(um.getAllUsers()));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DELETEUSER);

        if (am.validateUserName(currentUser, username)) {
            self_delete = true;
        }

        if (am.deleteUser(username)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.DELETEUSER);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.DELETEUSER);
        }
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (self_delete) {
            userMenuFactory.getMenu(MenuEnums.START).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        }
    }
}
