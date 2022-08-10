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
 * A menu that non admin users can run and interact with.
 */
public class NonAdminMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;
    private final List<String> actionList = List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "video studio"});

    /**
     * Creates a non admin menu with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public NonAdminMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        actionFactory = new UserActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    /**
     * Runs the non admin menu's actions.
     */
    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.NONADMIN, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}