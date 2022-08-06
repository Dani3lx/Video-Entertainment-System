package controllers.action.actionFactories;

import controllers.action.actions.startMenu.AccountCreation;
import controllers.action.actions.startMenu.ExitProgram;
import controllers.action.actions.startMenu.UserLogin;
import entities.User;

public class StartActionFactory implements ActionFactory {
    private final User user;

    public StartActionFactory(User user) {
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