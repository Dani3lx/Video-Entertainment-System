package controllers.menuAction.menuActions.userMenu;

import controllers.menuAction.menuActions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class BrowseVideo implements MenuAction {

    MenuBuilder menuBuilder;

    public BrowseVideo(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        menuBuilder = new MenuBuilder(userPrompt, user, lp);
    }

    @Override
    public void run() {
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        menuBuilder.getMenu("videoBrowse").run();
    }
}
