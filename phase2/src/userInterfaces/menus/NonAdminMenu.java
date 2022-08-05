package userInterfaces.menus;

import controllers.menuAction.menuActionFactories.UserMenuActionFactory;
import controllers.menuAction.menuActionFactories.MenuAction;
import controllers.menuAction.menuActionFactories.MenuActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class NonAdminMenu implements Menu{
    private final UserPrompt userPrompt;
    private final MenuActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "video studio"});

    public NonAdminMenu(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        factory = new UserMenuActionFactory(userPrompt, user, lp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.NONADMIN, actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}