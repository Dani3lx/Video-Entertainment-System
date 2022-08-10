package controllers.actions.playlistActions.searchPlaylist;


import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class CreatePlaylist extends MenuAction implements Action {

    private MenuFactory playlistsMenuFactory;
    private boolean check;
    public CreatePlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }
    @Override
    public void run(){
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        String username = um.getUserName(currentUser);
        /*Check if playlist name exists*/
        check =  pm.checkPlaylistByName(plname);
        if (check){
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDINPUT);
            next();
        }
        else {
            Playlist pl = new Playlist(plname,username);
            pm.addPlaylist(pl);
            mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);
            /*Adding playlist to future menus*/
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
            next();
        }
    }
    @Override
    public void next(){
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt,currentUser,lp,mp);

        if (!check){
            playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTMANAGE).run();
        }
        else{
            if (um.getRole(currentUser)){
                userMenuFactory.getMenu(MenuEnums.ADMIN).run();
            } else {
                userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
            }
        }
    }
}
