package controllers.actionfactories;

import controllers.actions.playlistactions.ReturnPL;
import controllers.actions.playlistactions.ReturnPLsub;
import controllers.actions.playlistactions.manageplaylist.*;
import controllers.actions.playlistactions.orderplaylist.ReorderABCPlaylist;
import controllers.actions.playlistactions.orderplaylist.ReorderLikesPlaylist;
import controllers.actions.playlistactions.orderplaylist.ReorderShufflePlaylist;
import controllers.actions.playlistactions.searchplaylist.CreatePlaylist;
import controllers.actions.playlistactions.searchplaylist.DisplayAllPlaylist;
import controllers.actions.playlistactions.searchplaylist.SearchPlaylist;
import controllers.actions.playlistactions.viewplaylist.ChangeNamePlaylist;
import controllers.actions.playlistactions.viewplaylist.ViewLikesPlaylist;
import controllers.actions.playlistactions.viewplaylist.ViewVidsPlaylist;
import controllers.actions.videobrowseactions.Return;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;

/**
 * A concrete action factory that creates playlist related actions base on type.
 */
public class PlaylistActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;
    private final List<Playlist> playlists;

    /**
     * Creates a playlist action factory with the given user prompt, user, language presenter, menu presenter, and playlists.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param playlists  a list of playlists
     */
    public PlaylistActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> playlists) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
        this.playlists = playlists;
    }

    /**
     * Generates and returns an action base on the type.
     *
     * @param type the type of action
     * @return an action with the given type
     */
    public Action getAction(String type) {
        switch (type) {
            /* Find Playlist Actions*/
            case "search playlist":
                return new SearchPlaylist(userPrompt, user, lp, mp);
            case "create new playlist":
                return new CreatePlaylist(userPrompt, user, lp, mp);
            case "display all playlists":
                return new DisplayAllPlaylist(userPrompt, user, lp, mp);
            /*Playlist Management Actions*/
            case "view playlist":
                return new ViewPlaylist(userPrompt, user, lp, mp, playlists);
            case "add videos to playlist":
                return new AddToPlaylist(userPrompt, user, lp, mp, playlists);
            case "remove videos from playlist":
                return new RemoveFromPlaylist(userPrompt, user, lp, mp, playlists);
            case "reorder playlist":
                return new ReorderPlaylist(userPrompt, user, lp, mp, playlists);
            case "like playlist":
                return new LikePlaylist(userPrompt, user, lp, mp, playlists);
            /*View Playlist Actions*/
            case "view videos in playlist":
                return new ViewVidsPlaylist(userPrompt, user, lp, mp, playlists);
            case "view likes":
                return new ViewLikesPlaylist(userPrompt, user, lp, mp, playlists);
            case "change playlist name":
                return new ChangeNamePlaylist(userPrompt, user, lp, mp, playlists);
            /*Reorder Playlist Actions*/
            case "reorder playlist alphabetically":
                return new ReorderABCPlaylist(userPrompt, user, lp, mp, playlists);
            case "reorder playlist by likes":
                return new ReorderLikesPlaylist(userPrompt, user, lp, mp, playlists);
            case "shuffle playlist":
                return new ReorderShufflePlaylist(userPrompt, user, lp, mp, playlists);
            case "return to user menu":
                return new Return(userPrompt, user, lp, mp);
            case "return to playlist search":
                return new ReturnPL(userPrompt, user, lp, mp);
            case "return to playlist menu":
                return new ReturnPLsub(userPrompt, user, lp, mp, playlists);
            default:
                return null;
        }
    }
}