package controllers.action.actionFactories;

import controllers.action.actions.startMenu.AccountCreation;
import controllers.action.actions.startMenu.ExitProgram;
import controllers.action.actions.startMenu.UserLogin;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoStudioActionFactory implements ActionFactory {
    private final User user;

    public VideoStudioActionFactory(User user) {
        this.user = user;
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