package controllers.action.actionFactories;

import controllers.action.actions.playlistMenu.*;
import entities.User;
import controllers.action.actions.videoBrowseMenu.Return;

public class PlaylistActionFactory implements ActionFactory {
    private final User user;

    public PlaylistActionFactory(User user) {
        this.user = user;
    }

    public Action getAction(String type) {
        switch (type) {
            case "search playlist":
                return new SearchPlaylist(user);
            case "create playlist":
                return new CreatePlaylist(user);
            case "display all playlists":
                return new DisplayAllPlaylist(user);
            case "return":
                return new Return(user);
            default:
                return null;
        }
    }
}