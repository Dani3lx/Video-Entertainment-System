package controllers.action.actions.videoBrowseActions;

import controllers.action.actionFactories.VideoBrowseActionFactory;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.VideosMenuFactory;

import java.util.List;

public abstract class VideoBrowseMenuAction extends MenuAction {
    protected void nextMenu(List<Video> videos, Video video, User user){
        if (videos.isEmpty()) {
            VideoBrowseActionFactory actionFactory = new VideoBrowseActionFactory(userPrompt, user, lp, mp);
            actionFactory.getAction("return").run();
        } else {
            MenuFactory menuFactory = new VideosMenuFactory(userPrompt, currentUser, lp, mp, video);
            menuFactory.getMenu(MenuEnums.VIDEOINTERACTION).run();
        }
    }

    protected Video selectVideo(List<Video> videos){
        if (!videos.isEmpty()) {
            int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEO, vm.getVideoNames(videos));
            return videos.get(result - 1);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            return null;
        }
    }
}
