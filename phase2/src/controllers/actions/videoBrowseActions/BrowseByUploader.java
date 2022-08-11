package controllers.actions.videoBrowseActions;

import controllers.actionFactories.Action;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

/**
 * Browses videos by uploader.
 */
public class BrowseByUploader extends VideoBrowseMenuAction implements Action {
    private List<Video> videos;
    private Video video;

    /**
     * Creates a BrowseByUploader with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public BrowseByUploader(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    /**
     * Browses the video base on uploader.
     */
    @Override
    public void run() {
        String uploader = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.UPLOADER);
        videos = vm.getByUploader(uploader);
        video = selectVideo(videos);
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        nextMenu(videos, video, currentUser, lp, mp);
    }
}
