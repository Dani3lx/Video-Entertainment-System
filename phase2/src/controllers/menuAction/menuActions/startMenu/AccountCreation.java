package controllers.menuAction.menuActions.startMenu;

import controllers.old.UserActionHandler;
import controllers.menuAction.menuActionFactories.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.UserManager;
import userInterfaces.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class AccountCreation implements MenuAction {
    UserPrompt userPrompt;
    UserActionHandler userActionHandler;
    MenuBuilder menuBuilder;
    UserManager um = UserManager.getInstance();
    public AccountCreation(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        userActionHandler = new UserActionHandler(um);
        menuBuilder = new MenuBuilder(userPrompt, user, lp);
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
