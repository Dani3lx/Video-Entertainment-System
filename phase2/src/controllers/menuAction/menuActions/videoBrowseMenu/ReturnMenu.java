package controllers.menuAction.menuActions.videoBrowseMenu;

import controllers.menuAction.menuActions.MenuAction;
import controllers.old.UserActionHandler;
import entities.User;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.UserManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class ReturnMenu implements MenuAction {
    User currentUser;
    MenuBuilder menuBuilder;
    UserActionHandler userActionHandler;
    UserManager um = UserManager.getInstance();

    public ReturnMenu(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        menuBuilder = new MenuBuilder(userPrompt, user, lp);
        currentUser = user;
        userActionHandler = new UserActionHandler(um);
    }

    public void run() {
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        if (userActionHandler.isAdmin(currentUser)) {
            menuBuilder.getMenu("admin").run();
        } else {
            menuBuilder.getMenu("nonAdmin").run();
        }
    }
}
