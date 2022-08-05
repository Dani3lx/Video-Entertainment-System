package controllers.menuAction.menuActionFactories;

import controllers.menuAction.menuActions.MenuAction;
import controllers.menuAction.menuActions.startMenu.AccountCreation;
import controllers.menuAction.menuActions.startMenu.ExitProgram;
import controllers.menuAction.menuActions.startMenu.UserLogin;
import controllers.menuAction.menuActions.userMenu.BrowseVideo;
import controllers.menuAction.menuActions.userMenu.UserLogout;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

public class UserMenuActionFactory implements MenuActionFactory{
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public UserMenuActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            case "logout":
                return new UserLogout(userPrompt, user, lp);
            case "browse video":
                return new BrowseVideo(userPrompt, user, lp);
            default:
                return null;
        }
    }
}