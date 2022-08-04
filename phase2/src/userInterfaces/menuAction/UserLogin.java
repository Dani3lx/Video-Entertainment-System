package userInterfaces.menuAction;

import controllers.UserActionHandler;
import entities.User;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;
import userInterfaces.userPrompt.TerminalUserPrompt;

import java.util.Objects;

// Not a controller class, but acts as a UI controller.
public class UserLogin implements MenuAction {
    LanguagePresenter lp = new EnglishPresenter();
    UserPrompt userPrompt;
    UserActionHandler userActionHandler;
    MenuBuilder menuBuilder;
    User currentUser;

    public UserLogin(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt) {
        userActionHandler = new UserActionHandler(um);
        menuBuilder = new MenuBuilder(um, vm, pm, userPrompt);
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
        if (Objects.isNull(currentUser)) {
            menuBuilder.getMenu("start").run();
        }
        if (userActionHandler.isAdmin(currentUser)) {
            menuBuilder.getMenu("admin").run();
        } else {
            menuBuilder.getMenu("nonAdmin").run();
        }
    }
}
