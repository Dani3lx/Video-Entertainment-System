package controllers.actions;

import controllers.actionFactories.Action;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.userPrompt.UserPrompt;

/**
 * A menu action.
 */
public abstract class MenuAction implements Action {
    /**
     * The current user.
     */
    protected User currentUser;

    /**
     * The user manager that stores all the users.
     */
    protected UserManager um = UserManager.getInstance();

    /**
     * The video manager that stores all the videos.
     */
    protected VideoManager vm = VideoManager.getInstance();

    /**
     * The playlist manager that stores all the playlists.
     */
    protected PlaylistManager pm = PlaylistManager.getInstance();

    /**
     * The language being used.
     */
    protected LanguagePresenter lp;

    /**
     * The menu presenter being used.
     */
    protected MenuPresenter mp;

    /**
     * The user prompt being used.
     */
    protected UserPrompt userPrompt;
}
