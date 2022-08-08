package controllers.action.actionFactories;

import controllers.action.actions.startMenu.AccountCreation;
import controllers.action.actions.startMenu.ExitProgram;
import controllers.action.actions.startMenu.UserLogin;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

public class StartActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    public StartActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    public Action getAction(String type) {
        switch (type) {
            case "login":
                return new UserLogin(userPrompt, lp, mp);
            case "create account":
                return new AccountCreation(userPrompt, user, lp, mp);
            case "exit":
                return new ExitProgram();
            default:
                return null;
        }
    }
}