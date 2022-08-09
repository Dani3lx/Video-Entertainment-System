package controllers.action.actions.videoInteractionMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.VideoEditor;
import userInterfaces.userPrompt.UserPrompt;

public class LikeVideo extends MenuAction implements Action {
    private final Video video;
    public LikeVideo(UserPrompt userPrompt, User user, MenuPresenter mp, LanguagePresenter lp, Video video){
        this.userPrompt = userPrompt;
        this.currentUser =user;
        this.mp = mp;
        this.lp = lp;
        this.video = video;
    }
    @Override
    public void run() {
        VideoEditor ve = new VideoEditor();
        ve.likeVideo(video, currentUser);
        next();
    }

    @Override
    public void next() {

    }
}

