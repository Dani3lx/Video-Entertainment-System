package userInterfaces.menu;

import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.List;

public class MenuBuilder {
    private final UserManager um;
    private final VideoManager vm;
    private final PlaylistManager pm;

    public MenuBuilder(UserManager um, VideoManager vm, PlaylistManager pm) {
        this.um = um;
        this.vm = vm;
        this.pm = pm;
    }

    public Menu getMenu(String type) {
        switch (type) {
            case "start":
                return new Menu(type, List.of(new String[]{"login", "createAccount", "exit"}), um, vm, pm);
            default:
                return null;
        }
    }
}
