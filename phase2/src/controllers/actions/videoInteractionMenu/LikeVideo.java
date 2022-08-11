package controllers.actions.videoInteractionMenu;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.VideoEditor;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

/**
 * Likes a video.
 */
public class LikeVideo extends MenuAction implements Action {
    private final Video video;

    /**
     * Creates a LikeVideo with the given user prompt, user, language presenter, menu presenter and video.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param video      the video
     */
    public LikeVideo(UserPrompt userPrompt, User user, MenuPresenter mp, LanguagePresenter lp, Video video) {
        this.userPrompt = userPrompt;
        this.currentUser = user;
        this.mp = mp;
        this.lp = lp;
        this.video = video;
    }

    /**
     * Likes the video.
     */
    @Override
    public void run() {
        VideoEditor ve = new VideoEditor();
        ve.likeVideo(video, currentUser);
        mp.displayAlert(LanguagePresenter.AlertTextType.LIKEVIDEO);
        next();
    }

    /**
     * Navigates to the next appropriate menu.
     */
    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (um.getRole(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }
    }
}

