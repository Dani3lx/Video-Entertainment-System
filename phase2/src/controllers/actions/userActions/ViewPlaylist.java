package controllers.actions.userActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

/**
 * Navigates to the playlist menu.
 */
public class ViewPlaylist extends MenuAction implements Action {

    private final MenuFactory playlistsMenuFactory;

    /**
     * Creates a ViewPlaylist with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public ViewPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, user, lp, mp, null);
    }

    /**
     * Go to the playlist menu.
     */
    @Override
    public void run() {
        next();
    }

    /**
     * Go to the next menu.
     */
    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLIST).run();
    }
}
