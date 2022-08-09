package controllers.action.actions.videoBrowseActions;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

public class Return extends MenuAction implements Action {
    User currentUser;
    MenuFactory userMenuFactory;

    public Return(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        userMenuFactory = new UserMenuFactory(userPrompt, user, lp, mp);
        currentUser = user;
    }

    public void run() {
        next();
    }

    @Override
    public void next() {
        if (um.getRole(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }
    }
}