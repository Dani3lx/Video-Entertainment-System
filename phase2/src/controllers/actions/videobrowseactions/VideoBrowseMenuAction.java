package controllers.actions.videobrowseactions;

import controllers.actionfactories.VideoBrowseActionFactory;
import controllers.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import usecase.VideoEditor;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.VideosMenuFactory;

import java.util.List;

/**
 * An action associated to video browsing.
 */
public abstract class VideoBrowseMenuAction extends MenuAction {
    /*
     * Displays the video information to the user.
     *
     * @param video the video being displayed
     * @param lp    the program's language presenter
     * @param mp    the program's menu presenter
     */
    private void showVideoInformation(Video video, LanguagePresenter lp, MenuPresenter mp) {
        VideoEditor ve = new VideoEditor();
        String[] information = ve.returnVideoInformation(video, lp);
        mp.displayList(List.of(information));
    }

    /**
     * Navigates to the appropriate menu.
     * Goes to the user menu if videos is empty.
     * Goes to the video interaction menu if not empty.
     *
     * @param videos the list of videos
     * @param video  a video
     * @param user   the current user
     * @param lp     the program's language presenter
     * @param mp     the program's menu presenter
     */
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

    /**
     * Select and return a video from videos.
     *
     * @param videos list of videos
     * @return a video that the user selects
     */
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
