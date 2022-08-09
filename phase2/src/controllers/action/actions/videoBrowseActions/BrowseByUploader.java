package controllers.action.actions.videoBrowseActions;

import controllers.action.actionFactories.Action;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class BrowseByUploader extends VideoBrowseMenuAction implements Action {
    List<Video> videos;
    Video video;
    public BrowseByUploader(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run() {
        String uploader = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.UPLOADER);
        videos = vm.getByUploader(uploader);
        video = selectVideo(videos);
        next();
    }

    @Override
    public void next() {
        nextMenu(videos, video, currentUser);
    }
}
