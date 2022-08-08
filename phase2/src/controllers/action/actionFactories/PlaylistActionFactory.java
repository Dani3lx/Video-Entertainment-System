package controllers.action.actionFactories;

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

    public PlaylistActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> playlists) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    public Action getAction(String type) {
        switch (type) {
//            case "search playlist":
//                return new SearchPlaylist(user);
//            case "create playlist":
//                return new CreatePlaylist(user);
//            case "display all playlists":
//                return new DisplayAllPlaylist(user);
//            case "return":
//                return new Return(user);
            default:
                return null;
        }
    }
}