package userInterfaces.menuAction;

import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menuAction.MenuAction;
import userInterfaces.menuAction.UserLogin;

public class MenuActionFactory {
    UserManager um;
    VideoManager vm;
    PlaylistManager pm;

    public MenuActionFactory(UserManager um, VideoManager vm, PlaylistManager pm) {
        this.um = um;
        this.pm = pm;
        this.vm = vm;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            case "login":
                return new UserLogin(um, vm, pm);
            case "create account":
                return new AccountCreation();
            case "exit":
                return new ExitProgram(um, vm, pm);
            default:
                return null;
        }
    }
}
