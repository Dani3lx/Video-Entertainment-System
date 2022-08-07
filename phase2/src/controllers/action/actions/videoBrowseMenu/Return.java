package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.MenuFactory;
import userInterfaces.Menus;
import userInterfaces.userPrompt.UserPrompt;

public class Return extends MenuAction implements Action {
    User currentUser;
    MenuFactory menuFactory;

    public Return(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        menuFactory = new MenuFactory(userPrompt, user, lp, mp);
        currentUser = user;
    }

    public void run() {
        next();
    }

    @Override
    public void next() {
        if (um.getRole(currentUser)) {
            menuFactory.getMenu(Menus.ADMIN).run();
        } else {
            menuFactory.getMenu(Menus.NONADMIN).run();
        }
    }
}
