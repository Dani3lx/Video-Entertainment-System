package controllers.actions.videoBrowseActions;

import controllers.actionFactories.Action;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

/**
 * Browses videos by categories.
 */
public class BrowseByCategory extends VideoBrowseMenuAction implements Action {
    private List<Video> videos;
    private Video video;

    /**
     * Creates a BrowseByCategory with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public BrowseByCategory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    /**
     * Browses the video base on video categories.
     */
    @Override
    public void run() {
        List<String> categories = userPrompt.getMultipleInputs(LanguagePresenter.RequestTextType.CATEGORY);
        videos = vm.getByCategory(categories);
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
