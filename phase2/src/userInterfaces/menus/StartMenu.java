package userInterfaces.menus;

import controllers.menuAction.menuActionFactories.StartMenuActionFactory;
import controllers.menuAction.menuActionFactories.MenuAction;
import controllers.menuAction.menuActionFactories.MenuActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class StartMenu implements Menu{
    private final UserPrompt userPrompt;
    private final MenuActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"login", "create account", "exit"});

    public StartMenu(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        factory = new StartMenuActionFactory(userPrompt, user, lp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.START, actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}
