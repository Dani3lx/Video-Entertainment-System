package controllers.menuAction;

import controllers.menuAction.allUser.BrowseVideo;
import controllers.menuAction.start.AccountCreation;
import controllers.menuAction.start.ExitProgram;
import entities.User;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import controllers.menuAction.allUser.UserLogout;
import controllers.menuAction.start.UserLogin;
import userInterfaces.userPrompt.UserPrompt;

public class MenuActionFactory {
    private final UserPrompt userPrompt;
    User user;

    public MenuActionFactory(UserPrompt userPrompt, User user) {
        this.userPrompt = userPrompt;
        this.user = user;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            case "login":
                return new UserLogin(userPrompt);
            case "create account":
                return new AccountCreation(userPrompt, user);
            case "exit":
                return new ExitProgram();
            case "logout":
                return new UserLogout(userPrompt, user);
            case "return":
                return new ReturnMenu(userPrompt, user);
            case "browse video":
                return new BrowseVideo(userPrompt, user);
            default:
                return null;
        }
    }
}
