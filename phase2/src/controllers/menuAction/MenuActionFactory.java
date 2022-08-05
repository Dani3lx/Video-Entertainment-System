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
    UserManager um;
    VideoManager vm;
    PlaylistManager pm;
    private final UserPrompt userPrompt;
    User user;

    public MenuActionFactory(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt, User user) {
        this.um = um;
        this.pm = pm;
        this.vm = vm;
        this.userPrompt = userPrompt;
        this.user = user;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            case "login":
                return new UserLogin(um, vm, pm, userPrompt);
            case "create account":
                return new AccountCreation(um, vm, pm, userPrompt, user);
            case "exit":
                return new ExitProgram(um, vm, pm);
            case "logout":
                return new UserLogout(um, vm, pm, userPrompt, user);
            case "return":
                return new ReturnMenu(um, vm, pm, userPrompt, user);
            case "browse video":
                return new BrowseVideo(um, vm, pm, userPrompt, user);
            default:
                return null;
        }
    }
}
