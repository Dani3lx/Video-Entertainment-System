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

public class DisplayAllPlaylist extends PlaylistSearchAction implements Action {

    MenuFactory playlistsMenuFactory;
    private boolean found_pl;
    MenuFactory userMenuFactory;

    public DisplayAllPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        userMenuFactory = new UserMenuFactory(userPrompt,currentUser,lp,mp);
    }

    @Override
    public void run() {
        mp.displayAlert(LanguagePresenter.AlertTextType.ALLPLAYLISTS);
        List<String> pl_list = pm.getPlaylistNames();
        mp.displayList(pl_list);

        Playlist pl = playlistSearch(userPrompt);
        found_pl = playlistExists(pl);
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp,List.of(pl));
        next();
    }

    @Override
    public void next() {
        nextMenu(found_pl,playlistsMenuFactory,userMenuFactory);
    }
}