package userInterfaces.menu;

import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class MenuBuilder {
    private final UserManager um;
    private final VideoManager vm;
    private final PlaylistManager pm;
    private final UserPrompt userPrompt;

    public MenuBuilder(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt) {
        this.um = um;
        this.vm = vm;
        this.pm = pm;
        this.userPrompt = userPrompt;
    }

    public Menu getMenu(String type) {
        switch (type) {
            case "start":
                return new Menu(type, List.of(new String[]{"login", "create account", "exit"}), um, vm, pm, userPrompt);
            case "admin":
                return new Menu(type, List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "create admin user", "delete user", "ban user", "unban user"}), um, vm, pm, userPrompt);
            case "nonAdmin":
                return new Menu(type, List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "video studio"}), um, vm, pm, userPrompt);
            default:
                return null;
        }
    }
}
