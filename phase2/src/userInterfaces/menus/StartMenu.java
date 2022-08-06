package userInterfaces.menus;

import controllers.action.actionFactories.StartActionFactory;
import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class StartMenu implements Menu{
    private final UserPrompt userPrompt;
    private final ActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"login", "create account", "exit"});

    public StartMenu(UserPrompt userPrompt, User user) {
        factory = new StartActionFactory(user);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.START, actionList);
        Action action = factory.getAction(actionList.get(result - 1));
        action.run();
    }
}
