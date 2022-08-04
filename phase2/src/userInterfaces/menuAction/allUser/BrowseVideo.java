package userInterfaces.menuAction.allUser;

import entities.User;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menuAction.MenuAction;
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
