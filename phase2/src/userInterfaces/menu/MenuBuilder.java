package userInterfaces.menu;

import entities.User;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class MenuBuilder {
    private final UserManager um;
    private final VideoManager vm;
    private final PlaylistManager pm;
    private final UserPrompt userPrompt;
    private final User user;

    public MenuBuilder(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt, User user) {
        this.um = um;
        this.vm = vm;
        this.pm = pm;
        this.userPrompt = userPrompt;
        this.user = user;
    }

    public Menu getMenu(String type) {
        switch (type) {
            case "start":
                return new Menu(type, List.of(new String[]{"login", "create account", "exit"}), um, vm, pm, userPrompt, user);
            case "admin":
                return new Menu(type, List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "create admin user", "delete user", "ban user", "unban user"}), um, vm, pm, userPrompt, user);
            case "nonAdmin":
                return new Menu(type, List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "video studio"}), um, vm, pm, userPrompt, user);
            case "videoBrowse":
                return new Menu(type, List.of(new String[]{"browse by name", "browse by category", "browse by uploader", "return"}), um, vm, pm, userPrompt, user);
            default:
                return null;
        }
    }
}
