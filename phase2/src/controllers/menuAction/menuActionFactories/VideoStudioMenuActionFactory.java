package controllers.menuAction.menuActionFactories;

import controllers.menuAction.menuActions.startMenu.AccountCreation;
import controllers.menuAction.menuActions.startMenu.ExitProgram;
import controllers.menuAction.menuActions.startMenu.UserLogin;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoStudioMenuActionFactory implements MenuActionFactory{
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public VideoStudioMenuActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp) {
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
            default:
                return null;
        }
    }
}