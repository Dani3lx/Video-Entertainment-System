package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.MenuAction;
import controllers.old.UserActionHandler;
import entities.User;
import usecase.runtimeDataManager.UserManager;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;

public class Return extends MenuAction implements Action {
    User currentUser;
    MenuBuilder menuBuilder;
    UserActionHandler userActionHandler;
    UserManager um = UserManager.getInstance();

    public Return(User user) {
        menuBuilder = new MenuBuilder(userPrompt, user);
        currentUser = user;
        userActionHandler = new UserActionHandler(um);
    }

    public void run() {
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        if (userActionHandler.isAdmin(currentUser)) {
            menuBuilder.getMenu(Menus.ADMIN).run();
        } else {
            menuBuilder.getMenu(Menus.NONADMIN).run();
        }
    }
}
