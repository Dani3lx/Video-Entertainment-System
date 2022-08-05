package controllers.menuAction.allUser;

import controllers.menuAction.MenuAction;
import entities.User;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class UserLogout implements MenuAction {


    MenuBuilder menuBuilder;

    public UserLogout(UserPrompt userPrompt, User user) {
        menuBuilder = new MenuBuilder(userPrompt, user);
    }

    public void run() {
        navigateMenu();
    }

    public void navigateMenu() {
        menuBuilder.getMenu("start").run();
    }
}
