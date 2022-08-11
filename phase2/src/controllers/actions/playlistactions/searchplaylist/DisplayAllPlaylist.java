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
import java.util.Objects;
/**
 * This class is responsible for displaying all the playlists in the database
 */
public class DisplayAllPlaylist extends PlaylistSearchAction implements Action {

    private MenuFactory playlistsMenuFactory;
    private final MenuFactory userMenuFactory;
    private boolean found_pl;
    /**
     * Creates a constructor with the given user prompt, user, language presenter, menu presenter and associated playlist.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public DisplayAllPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
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
        mp.displayAlert(LanguagePresenter.AlertTextType.ALLPLAYLISTS);
        List<String> pl_list = pm.getPlaylistNames();
        mp.displayList(pl_list);

        Playlist pl = playlistSearch(userPrompt);

        if (Objects.isNull(pl)) {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
            next();
        } else {
            found_pl = true;
            mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);
            found_pl = playlistExists(pl);
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
            next();
        }
    }
    /**
     * This method is how the program moves from running the action to a new menu display
     */
    @Override
    public void next() {
        nextMenu(found_pl, playlistsMenuFactory, userMenuFactory);
    }
}