package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

/**
 *
 * TESTING. NOT TO BE USED.
 *
 */
public class SelectVideo extends MenuAction implements Action {

    List<Video> videos;

    public SelectVideo(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    public void getVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public void run() {
        List<String> videoNames = vm.getVideoNames(videos);
        mp.displayList(videoNames);
        next();
    }

    @Override
    public void next() {
    }
}