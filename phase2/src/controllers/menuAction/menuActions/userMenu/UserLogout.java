package controllers.menuAction.menuActions.userMenu;

import controllers.menuAction.menuActions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class UserLogout implements MenuAction {


    MenuBuilder menuBuilder;

    public UserLogout(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        menuBuilder = new MenuBuilder(userPrompt, user, lp);
    }

    public void run() {
        navigateMenu();
    }

    public void navigateMenu() {
        menuBuilder.getMenu("start").run();
    }
}
