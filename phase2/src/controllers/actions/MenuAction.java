package controllers.actions;

import controllers.actionfactories.Action;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import usecase.runtimedatamanager.PlaylistManager;
import usecase.runtimedatamanager.UserManager;
import usecase.runtimedatamanager.VideoManager;
import userinterfaces.userprompt.UserPrompt;

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
