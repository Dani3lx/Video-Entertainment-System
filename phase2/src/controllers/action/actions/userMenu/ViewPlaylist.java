package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.MenuFactory;
import userInterfaces.Menus;
import userInterfaces.userPrompt.UserPrompt;


public class ViewPlaylist extends MenuAction implements Action {

    MenuFactory menuFactory;

    public ViewPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        menuFactory = new MenuFactory(userPrompt, user, lp, mp);
    }

    @Override
    public void run() {
        next();
    }

    @Override
    public void next() {
        menuFactory.getMenu(Menus.PLAYLIST).run();
    }
}
