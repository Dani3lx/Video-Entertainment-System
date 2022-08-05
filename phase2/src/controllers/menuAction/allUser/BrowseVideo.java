package controllers.menuAction.allUser;

import controllers.menuAction.MenuAction;
import entities.User;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class BrowseVideo implements MenuAction {

    MenuBuilder menuBuilder;

    public BrowseVideo(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt, User user) {
        menuBuilder = new MenuBuilder(um, vm, pm, userPrompt, user);
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
