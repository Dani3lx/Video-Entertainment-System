package controllers.menuAction.start;

import controllers.old.UserActionHandler;
import controllers.menuAction.MenuAction;
import entities.User;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class AccountCreation implements MenuAction {
    UserPrompt userPrompt;
    UserActionHandler userActionHandler;
    MenuBuilder menuBuilder;
    UserManager um = UserManager.getInstance();
    public AccountCreation(UserPrompt userPrompt, User user) {
        userActionHandler = new UserActionHandler(um);
        menuBuilder = new MenuBuilder(userPrompt, user);
        this.userPrompt = userPrompt;
    }
    public void run(){
        System.out.println("Create account");
        navigateMenu();
    }
    public void navigateMenu(){
        menuBuilder.getMenu("nonAdmin").run();
    }
}
