package userinterfaces.menus.usermenus;

import controllers.actionfactories.Action;
import controllers.actionfactories.ActionFactory;
import controllers.actionfactories.StartActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menufactories.Menu;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;

/**
 * A menu that all users can run and interact with at the start of the program.
 */
public class StartMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;
    private final List<String> actionList = List.of(new String[]{"login", "create account", "exit"});

    /**
     * Creates a start menu with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public StartMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        actionFactory = new StartActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    /**
     * Runs the start menu's actions.
     */
    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.START, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}
