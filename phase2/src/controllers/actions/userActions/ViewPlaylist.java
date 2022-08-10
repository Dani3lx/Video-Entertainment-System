package controllers.actions.userActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.PlaylistsMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;


public class ViewPlaylist extends MenuAction implements Action {

    MenuFactory playlistsMenuFactory;

    public ViewPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, user, lp, mp, null);
    }

    @Override
    public void run() {
        next();
    }

    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLIST).run();
    }
}
