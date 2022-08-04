package userInterfaces.menuAction.start;

import controllers.UserActionHandler;
import entities.User;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menuAction.MenuAction;
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
    UserManager um;
    VideoManager vm;
    PlaylistManager pm;

    public UserLogin(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt) {
        userActionHandler = new UserActionHandler(um);
        this.userPrompt = userPrompt;
        this.um = um;
        this.vm = vm;
        this.pm = pm;
    }

    public void run() {
        // Takes in a username and password and tries to log in
        String username = userPrompt.getUserStringInput(lp.getRequestText("username"));
        String password = userPrompt.getUserStringInput(lp.getRequestText("password"));
        currentUser = userActionHandler.loginUser(username, password);
        navigateMenu();
    }
    public void navigateMenu(){
        menuBuilder = new MenuBuilder(um, vm, pm, userPrompt, currentUser);
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
