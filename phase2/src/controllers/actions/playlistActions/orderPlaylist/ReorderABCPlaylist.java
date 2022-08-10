package controllers.actions.playlistActions.orderPlaylist;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;


public class ReorderABCPlaylist extends MenuAction implements Action {

    private MenuFactory playlistsMenuFactory;
    private final Playlist pl;

    public ReorderABCPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
    }

    @Override
    public void run() {
        /* Validate if user can make changes*/
        String username = um.getUserName(currentUser);
        boolean validate = pm.validatePlaylistAction(pl, username);
        if (!validate) {
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDUSER);
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
            next();
        } else {
            Playlist sorted_pl = pm.reorderPlaylistByName(pl, username);
            String old_name = pm.getPlName(sorted_pl);
            pm.setPlName(sorted_pl, old_name + "_abc_sorted");
            pm.addPlaylist(sorted_pl);
            mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(sorted_pl));
            next();

        }
    }

    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTORDER).run();
    }
}