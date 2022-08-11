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

/**
 * This class is responsible for removing videos from the playlist
 */
public class RemoveFromPlaylist extends MenuAction implements Action {

    private final MenuFactory playlistsMenuFactory;
    private final Playlist pl;
    /**
     * Creates a constructor with the given user prompt, user, language presenter, menu presenter and associated playlist.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param pl         playlist associated with this action
     */
    public RemoveFromPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, pl);
    }
    /**
     * This method is how the abstract factory completes the action this class is responsible for
     */
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
                mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);
            }
            next();
        }
    }
    /**
     * This method is how the program moves from running the action to a new menu display
     */
    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTMANAGE).run();
    }
}