package controllers.action.actions.playlistActions.orderPlaylist;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;


public class ReorderShufflePlaylist extends MenuAction implements Action {

    MenuFactory playlistsMenuFactory;
    public Playlist pl;

    public ReorderShufflePlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;

    }
    @Override
    public void run(){
        /* Validate if user can make changes*/
        String username = um.getUserName(currentUser);
        boolean validate = pm.validatePlaylistAction(pl,username);
        if (!validate){
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDUSER);
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
            next();
        }
        else{
            Playlist sorted_pl = pm.shufflePlaylist(pl,vm,username);
            String old_name = pm.getPlName(sorted_pl);
            pm.setPlName(sorted_pl,old_name + "_shuffled");
            pm.addPlaylist(sorted_pl);
            mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(sorted_pl));
            next();

        }
    }
    @Override
    public void next(){
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTORDER).run();
    }
}