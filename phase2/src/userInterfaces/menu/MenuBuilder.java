package userInterfaces.menu;

import entities.User;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class MenuBuilder {
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public MenuBuilder(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
    }

    public Menu getMenu(String type) {
        switch (type) {
            case "start":
                return new Menu(type, List.of(new String[]{"login", "create account", "exit"}), userPrompt, user, lp);
            case "admin":
                return new Menu(type, List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "create admin user", "delete user", "ban user", "unban user"}), userPrompt, user, lp);
            case "nonAdmin":
                return new Menu(type, List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "video studio"}), userPrompt, user, lp);
            case "videoBrowse":
                return new Menu(type, List.of(new String[]{"browse by name", "browse by category", "browse by uploader", "return"}), userPrompt, user, lp);
            case "videoStudio":
                return new Menu(type, List.of(new String[]{"view all video uploaded"}), userPrompt, user, lp);
            default:
                return null;
        }
    }
}
