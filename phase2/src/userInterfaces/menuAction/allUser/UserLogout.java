package userInterfaces.menuAction.allUser;

import entities.User;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menuAction.MenuAction;
import userInterfaces.userPrompt.UserPrompt;

public class UserLogout implements MenuAction {


    MenuBuilder menuBuilder;

    public UserLogout(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt, User user) {
        menuBuilder = new MenuBuilder(um, vm, pm, userPrompt, user);
    }

    public void run() {
        navigateMenu();
    }

    public void navigateMenu() {
        menuBuilder.getMenu("start").run();
    }
}
