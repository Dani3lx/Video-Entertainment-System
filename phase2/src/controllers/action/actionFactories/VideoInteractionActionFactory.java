package controllers.action.actionFactories;

import controllers.action.actions.videoInteractionMenu.*;

import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;


public class VideoInteractionActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;
    private final Video video;

    public VideoInteractionActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video video) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
        this.video = video;
    }

    public Action getAction(String type) {
        switch (type) {
            case "like video":
                return new LikeVideo(userPrompt, user, mp, lp, video);
            case "dislike video":
                return new DislikeVideo(userPrompt, user, mp, lp, video);
            case "delete comment":
                return new DeleteComment(userPrompt, user, lp, mp, video);
            case "edit comment":
                return new EditComment(userPrompt, user, lp, mp,video);
            case "add comment":
                return new AddComment(userPrompt,user,lp,mp,video);
            case "view comments":
                return new ViewComment(userPrompt,user, lp, mp, video);
            default:
                return null;
        }
    }
}