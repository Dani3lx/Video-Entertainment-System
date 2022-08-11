package controllers.actionfactories;


import controllers.actions.videointeractionmenu.*;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.userprompt.UserPrompt;

/**
 * A concrete action factory that creates video interaction related actions base on type.
 */
public class VideoInteractionActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;
    private final Video video;

    /**
     * Creates a video interaction action factory with the given user prompt, user, language presenter, menu presenter and video.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param video      a video
     */
    public VideoInteractionActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video video) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
        this.video = video;
    }

    /**
     * Generates and returns an action base on the type.
     *
     * @param type the type of action
     * @return an action with the given type
     */
    public Action getAction(String type) {
        switch (type) {
            case "like video":
                return new LikeVideo(userPrompt, user, mp, lp, video);
            case "dislike video":
                return new DislikeVideo(userPrompt, user, mp, lp, video);
            case "delete comment":
                return new DeleteComment(userPrompt, user, lp, mp, video);
            case "edit comment":
                return new EditComment(userPrompt, user, lp, mp, video);
            case "add comment":
                return new AddComment(userPrompt, user, lp, mp, video);
            case "view comments":
                return new ViewComment(userPrompt, user, lp, mp, video);
            default:
                return null;
        }
    }
}