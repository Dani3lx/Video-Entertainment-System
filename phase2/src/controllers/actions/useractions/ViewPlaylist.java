package controllers.actions.useractions;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.PlaylistsMenuFactory;
import userinterfaces.userprompt.UserPrompt;

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
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLIST).run();
    }
}
