package controllers.actions.userActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.AdminManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

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
