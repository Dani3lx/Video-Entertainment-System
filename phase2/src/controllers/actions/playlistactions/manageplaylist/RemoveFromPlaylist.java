package controllers.actions.playlistactions.manageplaylist;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.Playlist;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.PlaylistsMenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.util.ArrayList;
import java.util.List;


public class RemoveFromPlaylist extends MenuAction implements Action {

    private final MenuFactory playlistsMenuFactory;
    private final Playlist pl;

    public RemoveFromPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, pl);
    }

    @Override
    public void run() {
        /* Validate if user can make changes*/
        String username = um.getUserName(currentUser);
        boolean validate = pm.validatePlaylistAction(pl, username);
        if (!validate) {
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDUSER);
            next();
        } else {
            String vidname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.VIDEONAME);
            ArrayList<Video> vids = vm.getByName(vidname);
            if (vids.isEmpty()) {
                mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            } else {
                Video vid = vids.get(0);
                pm.deleteFromPlaylist(pl, vid);
            }
            next();
        }
    }

    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTMANAGE).run();
    }
}