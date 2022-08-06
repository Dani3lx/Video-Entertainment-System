package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;
import userInterfaces.userPrompt.UserPrompt;

public class Return extends MenuAction implements Action {
    User currentUser;
    MenuBuilder menuBuilder;

    public Return(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        menuBuilder = new MenuBuilder(userPrompt, user, lp, mp);
        currentUser = user;
    }

    public void run() {
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        if (um.getRole(currentUser)) {
            menuBuilder.getMenu(Menus.ADMIN).run();
        } else {
            menuBuilder.getMenu(Menus.NONADMIN).run();
        }
    }
}
