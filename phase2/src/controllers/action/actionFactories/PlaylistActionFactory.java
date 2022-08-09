package controllers.action.actionFactories;

import controllers.action.actions.playlistMenu.*;
import controllers.action.actions.playlistMenu.searchPlaylist.CreatePlaylist;
import controllers.action.actions.playlistMenu.searchPlaylist.DisplayAllPlaylist;
import controllers.action.actions.playlistMenu.searchPlaylist.SearchPlaylist;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;
import controllers.action.actions.videoBrowseMenu.Return;

public class PlaylistActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;
    private final List<Playlist> playlists;

    public PlaylistActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> playlists) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
        this.playlists = playlists;
    }

    public Action getAction(String type) {
        switch (type) {
          /* Find Playlist Actions*/
            case "search playlist":
                return new SearchPlaylist(userPrompt,user,lp,mp,null);
            case "create new playlist":
                return new CreatePlaylist(userPrompt,user,lp,mp,null);
            case "display all playlists":
                return new DisplayAllPlaylist(userPrompt,user,lp,mp,null);
          /*Playlist Management Actions*/
            case "view playlist":
                return new ViewPlaylist(userPrompt,user,lp,mp,playlists);
            case "add videos to playlist":
                return new AddToPlaylist(userPrompt,user,lp,mp,playlists);
            case "remove videos from playlist":
                return new RemoveFromPlaylist(userPrompt,user,lp,mp,playlists);
            case "reorder playlist":
                return new ReorderPlaylist(userPrompt,user,lp,mp,playlists);
            case "like playlist":
                return new LikePlaylist(userPrompt,user,lp,mp,playlists);
            /*View Playlist Actions*/
            case "view videos in playlist":
                return new ViewVidsPlaylist(userPrompt,user,lp,mp,playlists);
            case "view likes":
                return new ViewLikesPlaylist(userPrompt,user,lp,mp,playlists);
            case "change playlist name":
                return new ChangeNamePlaylist(userPrompt,user,lp,mp,playlists);
            /*Reorder Playlist Actions*/
            case "reorder playlist alphabetically":
                return new ReorderABCPlaylist(userPrompt,user,lp,mp,playlists);
            case "reorder playlist by likes":
                return new ReorderLikesPlaylist(userPrompt,user,lp,mp,playlists);
            case "shuffle playlist":
                return new RearderShufflePlaylist(userPrompt,user,lp,mp,playlists);
            case "return to user menu":
                return new Return(user);
            case "return to playlist search":
                return
            case "return to playlist menu":
                return
            default:
                return null;
        }
    }
}