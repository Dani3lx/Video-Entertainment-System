package userInterfaces.menus.userMenus;

import controllers.actionFactories.UserActionFactory;
import controllers.actionFactories.Action;
import controllers.actionFactories.ActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.Menu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

/**
 * A menu that admin users can run and interact with.
 */
public class AdminMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;

    // list of actions the menu can perform
    private final List<String> actionList = List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "create admin user", "delete user", "ban user", "unban user"});

    /**
     * Creates an admin menu with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public AdminMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        actionFactory = new UserActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    /**
     * Runs the admin menu's actions.
     */
    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.ADMIN, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}