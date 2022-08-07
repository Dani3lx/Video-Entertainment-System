package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

public class ChangePassword extends MenuAction implements Action {

    public ChangePassword(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        um.changePassword(currentUser, password);
        mp.displayAlert(LanguagePresenter.AlertTextType.CHANGEPASSWORD);
        next();
    }

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