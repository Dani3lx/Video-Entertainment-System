package controllers.actions.playlistActions.searchPlaylist;

import controllers.actions.MenuAction;
import entities.Playlist;
import presenters.language.LanguagePresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.Objects;

public abstract class PlaylistSearchAction extends MenuAction{

    protected Playlist playlistSearch(UserPrompt userPrompt) {
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        return pm.getPlaylistByName(plname);
    }

    protected boolean playlistExists(Playlist pl){
        boolean found_pl;
        if (Objects.isNull(pl)) {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
        } else {
            found_pl = true;
        }
        return found_pl;
    }

    protected void nextMenu(boolean check, MenuFactory playlistsMenuFactory, MenuFactory userMenuFactory){
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