package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;

public class UserLogout extends MenuAction implements Action {

    MenuBuilder menuBuilder;

    public UserLogout(User user) {
        menuBuilder = new MenuBuilder(userPrompt, user);
    }

    public void run() {
        navigateMenu();
    }

    public void navigateMenu() {
        menuBuilder.getMenu(Menus.START).run();
    }
}
