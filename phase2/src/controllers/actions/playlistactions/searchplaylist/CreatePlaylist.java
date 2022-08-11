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

public class CreatePlaylist extends PlaylistSearchAction implements Action {

    MenuFactory userMenuFactory;
    private MenuFactory playlistsMenuFactory;
    private boolean found_pl;

    public CreatePlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
    }

    @Override
    public void run() {
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        String username = um.getUserName(currentUser);
        /*Check if playlist name exists*/
        found_pl = pm.checkPlaylistByName(plname);
        if (found_pl) {
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDINPUT);
            next();
        } else {
            Playlist pl = new Playlist(plname, username);
            pm.addPlaylist(pl);
            mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);

            /*Adding playlist to future menus*/
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
            next();
        }
    }

    @Override
    public void next() {
        nextMenu(!found_pl, playlistsMenuFactory, userMenuFactory);
    }
}
