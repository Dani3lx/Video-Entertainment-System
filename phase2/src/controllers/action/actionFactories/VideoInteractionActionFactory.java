package controllers.action.actionFactories;

import controllers.action.actions.videoBrowseActions.BrowseByCategory;
import controllers.action.actions.videoBrowseActions.BrowseByName;
import controllers.action.actions.videoBrowseActions.BrowseByUploader;
import controllers.action.actions.videoBrowseActions.Return;
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
    private Video video;

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
                return null;
            case "dislike video":
                return null;
            case "delete comment":
                return null;
            case "edit comment":
                return null;
            default:
                return null;
        }
    }
}