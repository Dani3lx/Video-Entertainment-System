package userInterfaces.menus;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import controllers.action.actionFactories.PlaylistActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class PlaylistMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory factory;

    private final List<String> actionList = List.of(new String[]{""});

    public PlaylistMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        factory = new PlaylistActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.PLAYLIST, actionList);
        Action action = factory.getAction(actionList.get(result - 1));
        action.run();
    }
}