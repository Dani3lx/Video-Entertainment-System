package controllers.menuAction.allUser;

import controllers.menuAction.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
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
