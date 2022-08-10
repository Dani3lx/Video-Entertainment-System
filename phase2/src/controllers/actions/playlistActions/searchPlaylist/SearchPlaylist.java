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
import java.util.Objects;

public class SearchPlaylist extends MenuAction implements Action {

    private boolean found_pl;
    MenuFactory playlistsMenuFactory;
    MenuFactory userMenuFactory;

    public SearchPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        userMenuFactory = new UserMenuFactory(userPrompt,currentUser,lp,mp);
    }
    @Override
    public void run(){
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        Playlist pl = pm.getPlaylistByName(plname);

        /* Check if Playlist Exists*/
        if (Objects.isNull(pl)){
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
            next();

        }
        else{found_pl=true;
            /*Adding playlist to future menus*/
            playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp,List.of(pl));
            next();
        }

    }
    @Override
    public void next(){


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
