package controllers.actions.playlistactions.searchplaylist;


import controllers.actionfactories.Action;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.PlaylistsMenuFactory;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;
/**
 * This class is responsible for creating new playlists
 */
public class CreatePlaylist extends PlaylistSearchAction implements Action {

    private final MenuFactory userMenuFactory;
    private MenuFactory playlistsMenuFactory;
    private boolean found_pl;
    /**
     * Creates a constructor with the given user prompt, user, language presenter, menu presenter and associated playlist.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public CreatePlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
    }
    /**
     * This method is how the abstract factory completes the action this class is responsible for
     */
    @Override
    public void run() {
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        String username = um.getUserName(currentUser);
        /*Check if playlist name exists*/
        found_pl = pm.checkPlaylistByName(plname);
        if (found_pl) {
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDINPUT);
            next();
        } else {
            Playlist pl = new Playlist(plname, username);
            pm.addPlaylist(pl);
            mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);

            /*Adding playlist to future menus*/
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
            next();
        }
    }
    /**
     * This method is how the program moves from running the action to a new menu display
     */
    @Override
    public void next() {
        nextMenu(!found_pl, playlistsMenuFactory, userMenuFactory);
    }
}
