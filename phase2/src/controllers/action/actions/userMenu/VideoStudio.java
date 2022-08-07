package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

public class VideoStudio extends MenuAction implements Action {

    MenuFactory userMenuFactory;

    public VideoStudio(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        userMenuFactory = new UserMenuFactory(userPrompt, user, lp, mp);
    }

    @Override
    public void run() {
        next();
    }

    @Override
    public void next() {
        userMenuFactory.getMenu(MenuEnums.VIDEOSTUDIO).run();
    }
}