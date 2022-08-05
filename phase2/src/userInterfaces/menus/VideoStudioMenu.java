package userInterfaces.menus;

import controllers.menuAction.menuActionFactories.MenuAction;
import controllers.menuAction.menuActionFactories.MenuActionFactory;
import controllers.menuAction.menuActionFactories.PlaylistMenuActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoStudioMenu implements Menu {
    private final UserPrompt userPrompt;
    private final MenuActionFactory factory;

    private final List<String> actionList = List.of(new String[]{""});

    public VideoStudioMenu(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        factory = new PlaylistMenuActionFactory(userPrompt, user, lp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.VIDEOSTUDIO, actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}