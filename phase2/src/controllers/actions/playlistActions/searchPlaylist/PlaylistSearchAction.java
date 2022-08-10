package controllers.actions.playlistActions.searchPlaylist;

import controllers.actions.MenuAction;
import entities.Playlist;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;

import userInterfaces.userPrompt.UserPrompt;

import java.util.List;
import java.util.Objects;

public abstract class PlaylistSearchAction extends MenuAction{

    public Playlist playlistSearch(UserPrompt userPrompt, MenuPresenter mp) {
        String plname = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PLAYLIST);
        Playlist pl = pm.getPlaylistByName(plname);
        return pl;
    }

    public boolean playlistExists(Playlist pl){
        boolean found_pl;
        if (Objects.isNull(pl)) {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
            found_pl = false;
        } else {
            found_pl = true;
        }
        return found_pl;
    }

}