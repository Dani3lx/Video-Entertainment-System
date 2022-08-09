package userInterfaces.menus.videosMenus;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import controllers.action.actionFactories.VideoInteractionActionFactory;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.Menu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideoInteractionMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;
    private final List<String> actionList = List.of(new String[]{"like video", "dislike video", "delete comment", "edit comment"});
    private final Video video;

    public VideoInteractionMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video video) {
        actionFactory = new VideoInteractionActionFactory(userPrompt, user, lp, mp, video);
        this.userPrompt = userPrompt;
        this.video = video;
    }

    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEOINTERACTION, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}
