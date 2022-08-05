package controllers.menuAction;

import controllers.menuAction.allUser.BrowseVideo;
import controllers.menuAction.start.AccountCreation;
import controllers.menuAction.start.ExitProgram;
import entities.User;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import controllers.menuAction.allUser.UserLogout;
import controllers.menuAction.start.UserLogin;
import userInterfaces.userPrompt.UserPrompt;

public class MenuActionFactory {
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public MenuActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            case "login":
                return new UserLogin(userPrompt, lp);
            case "create account":
                return new AccountCreation(userPrompt, user, lp);
            case "exit":
                return new ExitProgram();
            case "logout":
                return new UserLogout(userPrompt, user, lp);
            case "return":
                return new ReturnMenu(userPrompt, user, lp);
            case "browse video":
                return new BrowseVideo(userPrompt, user, lp);
            default:
                return null;
        }
    }
}
