package controllers.action.actionFactories;

import controllers.action.actions.startMenu.AccountCreation;
import controllers.action.actions.startMenu.ExitProgram;
import controllers.action.actions.startMenu.UserLogin;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoStudioActionFactory implements ActionFactory {
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public VideoStudioActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
    }

    public Action getAction(String type) {
        switch (type) {
            case "login":
                return new UserLogin();
            case "create account":
                return new AccountCreation(user);
            case "exit":
                return new ExitProgram();
            default:
                return null;
        }
    }
}