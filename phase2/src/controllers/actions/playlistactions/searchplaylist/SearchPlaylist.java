package controllers.actions.playlistactions.searchplaylist;

import controllers.actionfactories.Action;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.PlaylistsMenuFactory;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;
import java.util.Objects;

public class SearchPlaylist extends PlaylistSearchAction implements Action {

    MenuFactory playlistsMenuFactory;
    MenuFactory userMenuFactory;
    private boolean found_pl;

    public SearchPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
    }

    @Override
    public void run() {
        Playlist pl = playlistSearch(userPrompt);

        if (Objects.isNull(pl)) {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
            System.out.println("check1");
            next();
        } else {
            found_pl = true;
            found_pl = playlistExists(pl);
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
            next();
        }


    }

    @Override
    public void next() {

        nextMenu(found_pl, playlistsMenuFactory, userMenuFactory);
    }
}
