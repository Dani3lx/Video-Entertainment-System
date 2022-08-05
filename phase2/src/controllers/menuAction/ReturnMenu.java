package controllers.menuAction;

import controllers.old.UserActionHandler;
import entities.User;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class ReturnMenu implements MenuAction {
    User currentUser;
    MenuBuilder menuBuilder;
    UserActionHandler userActionHandler;
    UserManager um = UserManager.getInstance();

    public ReturnMenu(UserPrompt userPrompt, User user){
        menuBuilder = new MenuBuilder(userPrompt, user);
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
