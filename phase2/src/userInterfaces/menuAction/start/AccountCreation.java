package userInterfaces.menuAction.start;

import controllers.UserActionHandler;
import entities.User;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menuAction.MenuAction;
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
