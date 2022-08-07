package userInterfaces.menus.videosMenus;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import controllers.action.actionFactories.VideoStudioActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.Menu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoStudioMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;

    private final List<String> actionList = List.of(new String[]{"view videos uploaded", "upload video", "delete video",
            "edit title", "edit categories", "edit description"});

    public VideoStudioMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        actionFactory = new VideoStudioActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.VIDEOSTUDIO, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}