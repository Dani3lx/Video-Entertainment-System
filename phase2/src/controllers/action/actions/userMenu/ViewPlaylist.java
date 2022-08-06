package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;
import userInterfaces.userPrompt.UserPrompt;


public class ViewPlaylist extends MenuAction implements Action {

    MenuBuilder menuBuilder;

    public ViewPlaylist(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        menuBuilder = new MenuBuilder(userPrompt, user, lp, mp);
    }

    @Override
    public void run() {
        next();
    }

    @Override
    public void next() {
        menuBuilder.getMenu(Menus.PLAYLIST).run();
    }
}
