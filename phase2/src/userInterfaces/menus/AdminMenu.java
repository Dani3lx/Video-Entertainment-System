package userInterfaces.menus;

import controllers.action.actionFactories.UserActionFactory;
import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class AdminMenu implements Menu{
    private final UserPrompt userPrompt;
    private final ActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "create admin user", "delete user", "ban user", "unban user"});

    public AdminMenu(UserPrompt userPrompt, User user) {
        factory = new UserActionFactory(user);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.ADMIN, actionList);
        Action action = factory.getAction(actionList.get(result - 1));
        action.run();
    }
}