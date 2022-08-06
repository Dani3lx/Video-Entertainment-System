package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;


public class BrowseVideo extends MenuAction implements Action {

    MenuBuilder menuBuilder;

    public BrowseVideo(User user) {
        menuBuilder = new MenuBuilder(userPrompt, user);
    }

    @Override
    public void run() {
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        menuBuilder.getMenu(Menus.VIDEOBROWSE).run();
    }
}
