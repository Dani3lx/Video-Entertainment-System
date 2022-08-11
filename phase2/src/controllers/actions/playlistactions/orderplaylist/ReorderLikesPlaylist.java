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


public class ReorderLikesPlaylist extends PlaylistOrderAction implements Action {

    private final Playlist pl;
    private MenuFactory playlistsMenuFactory;

    public ReorderLikesPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
    }

    @Override
    public void run() {
        Playlist sorted_pl = playlistOrderRun("by_rating", pl);
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(sorted_pl));
        next();
    }

    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTORDER).run();
    }
}