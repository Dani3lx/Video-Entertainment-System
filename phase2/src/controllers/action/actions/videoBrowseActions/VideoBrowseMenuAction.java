package controllers.action.actions.videoBrowseActions;

import controllers.action.actionFactories.VideoBrowseActionFactory;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.VideoEditor;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.VideosMenuFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class VideoBrowseMenuAction extends MenuAction {
    private void showVideoInformation(Video video, LanguagePresenter lp, MenuPresenter mp) {
        VideoEditor ve = new VideoEditor();
        String[] information = ve.returnVideoInformation(video, lp);
        mp.displayList(List.of(information));
    }

    protected void nextMenu(List<Video> videos, Video video, User user, LanguagePresenter lp, MenuPresenter mp) {
        if (videos.isEmpty()) {
            VideoBrowseActionFactory actionFactory = new VideoBrowseActionFactory(userPrompt, user, lp, mp);
            actionFactory.getAction("return").run();
        } else {
            showVideoInformation(video, lp, mp);
            MenuFactory menuFactory = new VideosMenuFactory(userPrompt, currentUser, lp, mp, video);
            menuFactory.getMenu(MenuEnums.VIDEOINTERACTION).run();
        }
    }

    protected Video selectVideo(List<Video> videos) {
        if (!videos.isEmpty()) {
            int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEO, vm.getVideoNames(videos));
            return videos.get(result - 1);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            return null;
        }
    }
}
