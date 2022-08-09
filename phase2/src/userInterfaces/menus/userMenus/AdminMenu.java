package userInterfaces.menus.userMenus;

import controllers.action.actionFactories.UserActionFactory;
import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.Menu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class AdminMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;

    private final List<String> actionList = List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "create admin user", "delete user", "ban user", "unban user"});

    public AdminMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        actionFactory = new UserActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.ADMIN, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}