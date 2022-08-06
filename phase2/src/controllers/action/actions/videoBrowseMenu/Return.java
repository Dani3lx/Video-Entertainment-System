package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;

public class Return extends MenuAction implements Action {
    User currentUser;
    MenuBuilder menuBuilder;

    public Return(User user) {
        menuBuilder = new MenuBuilder(userPrompt, user);
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
