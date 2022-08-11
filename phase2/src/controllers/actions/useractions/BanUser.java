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
 * Ban a user.
 */
public class BanUser extends MenuAction implements Action {

    /**
     * Creates a BanUser with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public BanUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    /**
     * Bans a user.
     */
    @Override
    public void run() {
        AdminManager am = new AdminManager();

        // Displays all unbanned users and asks for input to ban a user
        mp.displayAlert(LanguagePresenter.AlertTextType.UNBANNEDUSERS);
        mp.displayList(am.returnUsersByBan(um.getAllUsers(), false));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.BANUSER);

        if (am.banUser(currentUser, username)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.BANNED);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.BANNED);
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
