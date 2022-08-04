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
public class UserLogin implements MenuAction{
    LanguagePresenter lp = new EnglishPresenter();
    UserPrompt mc = new TerminalUserPrompt();
    UserActionHandler userActionHandler;

    UserManager um;
    VideoManager vm;
    PlaylistManager pm;

    public UserLogin(UserManager um, VideoManager vm, PlaylistManager pm) {
        userActionHandler = new UserActionHandler(um);
    }

    public void run(){
        // Takes in a username and password and tries to log in
        String username = mc.getUserStringInput(lp.getRequestText("username"));
        String password = mc.getUserStringInput(lp.getRequestText("password"));
        User currentUser = userActionHandler.loginUser(username, password);
        if (Objects.isNull(currentUser)) {
            new MenuBuilder(um, vm , pm).getMenu("start").run();
        }
        if (userActionHandler.isAdmin(currentUser)) {
            System.out.println("go to admin menu");
        } else {
            System.out.println("go to non admin menu");
        }
    }
}
