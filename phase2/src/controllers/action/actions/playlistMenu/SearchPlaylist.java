package controllers.action.actions.playlistMenu;

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

import java.util.Objects;

public class SearchPlaylist extends MenuAction implements Action {

    private boolean found_pl = true;
    MenuFactory playlistsMenuFactory;

    public SearchPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, user, lp, mp,null);
    }
    @Override
    public void run(){
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        Playlist pl = pm.getPlaylistByName(plname);
        /* Check if Playlist Exists*/
        if (Objects.isNull(pl)){
            mp.displayError(LanguagePresenter.ErrorTextType.NOTFOUND);
            found_pl = false;
        }
        next();
    }
    @Override
    public void next(){
        if (found_pl){
            playlistsMenuFactory.getMenu(MenuEnums.PLAYLIST).run();
        }
        else{

        }
    }
}
