package userInterfaces.menus.videosMenus;

import controllers.action.actionFactories.VideoBrowseActionFactory;
import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.Menu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoBrowseMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;
    private final List<String> actionList = List.of(new String[]{"browse by name", "browse by category", "browse by uploader", "return"});

    public VideoBrowseMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Video> videos) {
        actionFactory = new VideoBrowseActionFactory(userPrompt, user, lp, mp, videos);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEOBROWSE, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}