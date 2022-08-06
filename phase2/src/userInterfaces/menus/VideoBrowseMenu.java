package userInterfaces.menus;

import controllers.action.actionFactories.VideoBrowseActionFactory;
import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoBrowseMenu implements Menu{
    private final UserPrompt userPrompt;
    private final ActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"browse by name", "browse by category", "browse by uploader", "return"});

    public VideoBrowseMenu(UserPrompt userPrompt, User user) {
        factory = new VideoBrowseActionFactory(user);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.VIDEOBROWSE, actionList);
        Action action = factory.getAction(actionList.get(result - 1));
        action.run();
    }
}