package controllers.actions.playlistActions.searchPlaylist;

import controllers.actionFactories.Action;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;
import java.util.Objects;
/**
 * This class is responsible for searching playlists by name
 */
public class SearchPlaylist extends PlaylistSearchAction implements Action {

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
    public SearchPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
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
        Playlist pl = playlistSearch(userPrompt);

        if (Objects.isNull(pl)) {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
            next();
        } else {
            found_pl = true;
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
