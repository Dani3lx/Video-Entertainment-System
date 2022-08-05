package controllers.menuAction.start;

import controllers.old.UserActionHandler;
import controllers.menuAction.MenuAction;
import entities.User;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

import java.util.Objects;

// Not a controller class, but acts as a UI controller.
public class UserLogin implements MenuAction {
    LanguagePresenter lp = new EnglishPresenter();
    UserPrompt userPrompt;
    UserActionHandler userActionHandler;
    MenuBuilder menuBuilder;
    User currentUser;
    UserManager um = UserManager.getInstance();

    public UserLogin(UserPrompt userPrompt) {
        userActionHandler = new UserActionHandler(um);
        this.userPrompt = userPrompt;
    }

    public void run() {
        // Takes in a username and password and tries to log in
        String username = userPrompt.getUserStringInput(lp.getRequestText("username"));
        String password = userPrompt.getUserStringInput(lp.getRequestText("password"));
        currentUser = userActionHandler.loginUser(username, password);
        navigateMenu();
    }
    public void navigateMenu(){
        menuBuilder = new MenuBuilder(userPrompt, currentUser);
        if (Objects.isNull(currentUser)) {
            menuBuilder.getMenu("start").run();
        }
        userActionHandler.updateUserHistory(currentUser);
        if (userActionHandler.isAdmin(currentUser)) {
            menuBuilder.getMenu("admin").run();
        } else {
            menuBuilder.getMenu("nonAdmin").run();
        }
    }
}
