package controllers.actionFactories;

import controllers.actions.startActions.AccountCreation;
import controllers.actions.startActions.ExitProgram;
import controllers.actions.startActions.UserLogin;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

/**
 * A concrete action factory that creates start menu related actions base on type.
 */
public class StartActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    /**
     * Creates a start action factory with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public StartActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Generates and returns an action base on the type.
     *
     * @param type the type of action
     * @return an action with the given type
     */
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