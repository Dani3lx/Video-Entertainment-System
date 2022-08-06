package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.MenuAction;
import entities.User;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;

import java.util.List;

public class CheckLoginHistory extends MenuAction implements Action {

    public CheckLoginHistory(User user){
        currentUser = user;
    }

    @Override
    public void run() {
        List<String> history = um.getHistory(currentUser);
        mp.displayList(history);
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        MenuBuilder menuBuilder = new MenuBuilder(userPrompt, currentUser);
        if (um.getRole(currentUser)) {
            menuBuilder.getMenu(Menus.ADMIN).run();
        } else {
            menuBuilder.getMenu(Menus.NONADMIN).run();
        }
    }
}
