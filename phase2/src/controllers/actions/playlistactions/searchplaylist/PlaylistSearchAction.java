package controllers.actions.playlistactions.searchplaylist;

import controllers.actions.MenuAction;
import entities.Playlist;
import presenters.language.LanguagePresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.util.Objects;

/**
 * This abstract parent class holds code that would be duplicated across this package.
 * I've organized duplicated code into methods which are the called by the actions in
 * the search playlist package.
 */
public abstract class PlaylistSearchAction extends MenuAction {
    /**
     * Searches playlist by name
     * @param userPrompt used to take user input
     * @return playlist if one exists or null if it does not exist
     */
    protected Playlist playlistSearch(UserPrompt userPrompt) {
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        return pm.getPlaylistByName(plname);
    }

    /**
     * This method checks if playlists exist
     * @param pl playlist to check whether its null or has an actual playlist
     * @return boolean to determine which menu to move to
     */
    protected boolean playlistExists(Playlist pl) {
        boolean found_pl;
        if (Objects.isNull(pl)) {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
            next();
        } else {
            found_pl = true;
        }
        return found_pl;
    }

    /**
     * Method to store the logic of moving to the next menu
     * @param check whether or not package action was successfully carried out
     * @param playlistsMenuFactory factory instance used to create playlist menus
     * @param userMenuFactory factory instance used to return to user menus
     */
    protected void nextMenu(boolean check, MenuFactory playlistsMenuFactory, MenuFactory userMenuFactory) {

        if (check) {
            playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTMANAGE).run();
        } else {
            if (um.getRole(currentUser)) {
                userMenuFactory.getMenu(MenuEnums.ADMIN).run();
            } else {
                userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
            }
        }
    }

}