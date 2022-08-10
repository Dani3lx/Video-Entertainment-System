package controllers.actionFactories;

import controllers.actions.videoStudioActions.*;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoStudioActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;
    private Video video;

    public VideoStudioActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video video) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
        this.video = video;
    }

    public Action getAction(String type) {
        switch (type) {
            case "view videos uploaded":
                return new ViewVideoUploaded(userPrompt, user, lp, mp);
            case "upload video":
                return new UploadVideo(userPrompt, user, lp, mp);
            case "delete video":
                return new DeleteVideo(userPrompt, user, lp, mp);
            case "edit title":
                return new EditTitle(userPrompt, user, lp, mp);
            case "edit categories":
                return new EditCategories(userPrompt, user, lp, mp);
            case "edit description":
                return new EditDescription(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}