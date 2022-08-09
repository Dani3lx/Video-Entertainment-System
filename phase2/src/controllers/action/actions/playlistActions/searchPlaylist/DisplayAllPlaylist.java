package controllers.action.actions.playlistMenu.searchPlaylist;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DisplayAllPlaylist extends MenuAction implements Action {

    MenuFactory playlistsMenuFactory;
    private boolean found_pl;
    public DisplayAllPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }
    @Override
    public void run(){
        mp.displayAlert(LanguagePresenter.AlertTextType.ALLPLAYLISTS);
        List<String> pl_list = pm.getPlaylistNames();
        mp.displayList(pl_list);

        /*User Will Type Playlist name*/
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        Playlist pl = pm.getPlaylistByName(plname);

        if (Objects.isNull(pl)){

            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
        }
        else {found_pl=true;}
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, List.of(pl));
        next();
    }
    @Override
    public void next(){
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt,currentUser,lp,mp);

        if (found_pl){
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