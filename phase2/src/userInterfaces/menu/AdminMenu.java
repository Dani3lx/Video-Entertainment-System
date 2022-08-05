package userInterfaces.menu;

import controllers.menuAction.menuActionFactories.UserMenuActionFactory;
import controllers.menuAction.menuActions.MenuAction;
import controllers.menuAction.menuActionFactories.MenuActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class AdminMenu implements Menu{
    private final UserPrompt userPrompt;
    private final MenuActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "create admin user", "delete user", "ban user", "unban user"});

    public AdminMenu(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        factory = new UserMenuActionFactory(userPrompt, user, lp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice("Admin", actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}