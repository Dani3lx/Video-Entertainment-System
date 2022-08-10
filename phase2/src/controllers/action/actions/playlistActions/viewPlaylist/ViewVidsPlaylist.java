package controllers.action.actions.playlistActions.viewPlaylist;

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

import java.util.ArrayList;
import java.util.List;


public class ViewVidsPlaylist extends MenuAction implements Action {

    MenuFactory playlistsMenuFactory;
    public Playlist pl;

    public ViewVidsPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> pl){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.pl = pl.get(0);
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, pl);
    }


    @Override
    public void run(){
        String plname = pm.getPlName(pl);
        ArrayList<String> vidname = pm.namesInPlaylist(plname,vm);
        mp.displayList(vidname);
        next();
    }
    @Override
    public void next(){
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLISTVIEW).run();
    }
}