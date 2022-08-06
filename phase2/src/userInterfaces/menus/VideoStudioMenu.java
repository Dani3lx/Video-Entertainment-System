package userInterfaces.menus;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import controllers.action.actionFactories.PlaylistActionFactory;
import controllers.action.actionFactories.VideoStudioActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoStudioMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory factory;

    private final List<String> actionList = List.of(new String[]{""});

    public VideoStudioMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        factory = new VideoStudioActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.VIDEOSTUDIO, actionList);
        Action action = factory.getAction(actionList.get(result - 1));
        action.run();
    }
}