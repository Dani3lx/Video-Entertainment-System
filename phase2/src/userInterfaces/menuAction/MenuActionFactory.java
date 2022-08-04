package userInterfaces.menuAction;

import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.userPrompt.UserPrompt;

public class MenuActionFactory {
    UserManager um;
    VideoManager vm;
    PlaylistManager pm;
    private final UserPrompt userPrompt;

    public MenuActionFactory(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt) {
        this.um = um;
        this.pm = pm;
        this.vm = vm;
        this.userPrompt = userPrompt;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            case "login":
                return new UserLogin(um, vm, pm, userPrompt);
            case "create account":
                return new AccountCreation(um, vm, pm, userPrompt);
            case "exit":
                return new ExitProgram(um, vm, pm);
            default:
                return null;
        }
    }
}
