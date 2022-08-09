package controllers.action.actions.userActions;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

public class UserLogout extends MenuAction implements Action {

    MenuFactory userMenuFactory;

    public UserLogout(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        userMenuFactory = new UserMenuFactory(userPrompt, user, lp, mp);
    }

    public void run() {
        next();
    }

    public void next() {
        userMenuFactory.getMenu(MenuEnums.START).run();
    }
}
