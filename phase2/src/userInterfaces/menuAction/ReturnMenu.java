package userInterfaces.menuAction;

import controllers.PlaylistMenuActions;
import controllers.UserActionHandler;
import entities.User;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menuAction.MenuAction;
import userInterfaces.userPrompt.UserPrompt;

public class ReturnMenu implements MenuAction {
    User currentUser;
    MenuBuilder menuBuilder;
    UserActionHandler userActionHandler;

    public ReturnMenu(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt, User user){
        menuBuilder = new MenuBuilder(um, vm, pm, userPrompt, user);
        currentUser = user;
        userActionHandler = new UserActionHandler(um);
    }

    public void run(){
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        if (userActionHandler.isAdmin(currentUser)) {
            menuBuilder.getMenu("admin").run();
        } else {
            menuBuilder.getMenu("nonAdmin").run();
        }
    }
}
