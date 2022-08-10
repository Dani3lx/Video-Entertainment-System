package controllers.actions.playlistActions.searchPlaylist;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;
import java.util.Objects;

public class DisplayAllPlaylist extends PlaylistSearchAction implements Action {

    MenuFactory playlistsMenuFactory;
    private boolean found_pl;

    public DisplayAllPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run() {
        mp.displayAlert(LanguagePresenter.AlertTextType.ALLPLAYLISTS);
        List<String> pl_list = pm.getPlaylistNames();
        mp.displayList(pl_list);

        Playlist pl = playlistSearch(userPrompt,mp);
        found_pl = playlistExists(pl);
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp,List.of(pl));
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);

        if (found_pl) {
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