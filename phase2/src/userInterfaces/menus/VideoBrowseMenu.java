package userInterfaces.menus;

import controllers.menuAction.menuActionFactories.VideoBrowseMenuActionFactory;
import controllers.menuAction.menuActionFactories.MenuAction;
import controllers.menuAction.menuActionFactories.MenuActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoBrowseMenu implements Menu{
    private final UserPrompt userPrompt;
    private final MenuActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"browse by name", "browse by category", "browse by uploader", "return"});

    public VideoBrowseMenu(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        factory = new VideoBrowseMenuActionFactory(userPrompt, user, lp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice("videoBrowse", actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}