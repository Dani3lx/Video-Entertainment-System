package controllers.actions.playlistactions.orderplaylist;

import controllers.actionfactories.Action;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.PlaylistsMenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;
/**
 * This class is responsible for reordering videos in a playlist in random order
 */

public class ReorderShufflePlaylist extends PlaylistOrderAction implements Action {

    private final Playlist pl;
    private MenuFactory playlistsMenuFactory;
    /**
     * Creates a constructor with the given user prompt, user, language presenter, menu presenter and associated playlist.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param pl         playlist associated with this action
     */
    public ReorderShufflePlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
    }
    /**
     * This method is how the abstract factory completes the action this class is responsible for
     */
    @Override
    public void run() {
        Playlist sorted_pl = playlistOrderRun("shuffle", pl);
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(sorted_pl));
        next();
    }
    /**
     * This method is how the program moves from running the action to a new menu display
     */
    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTORDER).run();
    }
}