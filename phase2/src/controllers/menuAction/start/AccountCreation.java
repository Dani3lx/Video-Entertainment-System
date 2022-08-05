package controllers.menuAction.start;

import controllers.UserActionHandler;
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
    public AccountCreation(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt, User user) {
        userActionHandler = new UserActionHandler(um);
        menuBuilder = new MenuBuilder(um, vm, pm, userPrompt, user);
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
