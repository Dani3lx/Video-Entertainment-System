package controllers.action.actions.playlistMenu.managePlaylist;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.Playlist;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.ArrayList;
import java.util.List;


public class AddToPlaylist extends MenuAction implements Action {

    MenuFactory playlistsMenuFactory;
    public Playlist pl;

    public AddToPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, pl);
    }
    @Override
    public void run(){
        /* Validate if user can make changes*/
        String username = um.getUserName(currentUser);
        boolean validate = pm.validatePlaylistAction(pl,username);
        if (!validate){
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDUSER);
            next();
        }
        else{
            String vidname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.VIDEONAME);
            ArrayList<Video> vids = vm.getByName(vidname);
            if(vids.isEmpty()){
                mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            }
            else {
                Video vid = vids.get(0);
                pm.addToPlaylist(pl, vid);
            }
            next();
        }
    }
    @Override
    public void next(){
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTMANAGE).run();
    }
}