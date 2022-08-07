package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.VideosMenuFactory;
import userInterfaces.userPrompt.UserPrompt;


public class BrowseVideo extends MenuAction implements Action {

    MenuFactory videosMenuFactory;

    public BrowseVideo(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        videosMenuFactory = new VideosMenuFactory(userPrompt, user, lp, mp, null);
    }

    @Override
    public void run() {
        next();
    }

    @Override
    public void next() {
        videosMenuFactory.getMenu(MenuEnums.VIDEOBROWSE).run();
    }
}
