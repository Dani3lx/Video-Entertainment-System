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
 * Unbans a user.
 */
public class UnbanUser extends MenuAction implements Action {

    /**
     * Creates a UnbanUser with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public UnbanUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    /**
     * Unbans a user.
     */
    @Override
    public void run() {
        AdminManager am = new AdminManager();

        // Displays all banned users and asks for input to unban a user
        mp.displayAlert(LanguagePresenter.AlertTextType.BANNEDUSERS);
        mp.displayList(am.returnUsersByBan(um.getAllUsers(), true));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.UNBANUSER);

        if (am.unbanUser(username)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.UNBANNED);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.UNBANNED);
        }
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.ADMIN).run();
    }
}
