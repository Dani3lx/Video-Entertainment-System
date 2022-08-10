package controllers.actions.userActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
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
