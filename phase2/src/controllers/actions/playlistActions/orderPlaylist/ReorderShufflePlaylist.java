package controllers.actions.playlistActions.orderPlaylist;

import controllers.actionFactories.Action;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;


public class ReorderShufflePlaylist extends PlaylistOrderAction implements Action {

    private final Playlist pl;
    private MenuFactory playlistsMenuFactory;

    public ReorderShufflePlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
    }

    @Override
    public void run() {
        Playlist sorted_pl = playlistOrderRun("shuffle", pl);
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(sorted_pl));
        next();
    }

    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTORDER).run();
    }
}