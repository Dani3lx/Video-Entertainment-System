package controllers.actions.playlistActions.managePlaylist;

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


public class LikePlaylist extends MenuAction implements Action {

    MenuFactory playlistsMenuFactory;
    public Playlist pl;

    public LikePlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, pl);
    }
    @Override
    public void run(){
        pm.likePlaylist(pl); //todo will need to add current user when user is implemented
        mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);
        next();
    }
    @Override
    public void next(){
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTMANAGE).run();
    }
}