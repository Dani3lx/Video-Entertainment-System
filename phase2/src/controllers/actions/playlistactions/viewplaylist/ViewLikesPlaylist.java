package controllers.actions.playlistactions.viewplaylist;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.PlaylistsMenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;


public class ViewLikesPlaylist extends MenuAction implements Action {

    public Playlist pl;
    MenuFactory playlistsMenuFactory;

    public ViewLikesPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, pl);
    }

    @Override
    public void run() {
        String numlikes = pm.getRatings(pl);
        mp.displayList(List.of(numlikes));
        next();
    }

    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTVIEW).run();
    }
}