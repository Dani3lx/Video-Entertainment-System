package controllers.actions.videoBrowseActions;

import controllers.actionFactories.Action;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class BrowseByName extends VideoBrowseMenuAction implements Action {
    List<Video> videos;
    Video video;
    public BrowseByName(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run() {
        String videoName = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.VIDEONAME);
        videos = vm.getByName(videoName);
        video = selectVideo(videos);
        next();
    }

    @Override
    public void next() {
        nextMenu(videos, video, currentUser, lp, mp);
    }
}
