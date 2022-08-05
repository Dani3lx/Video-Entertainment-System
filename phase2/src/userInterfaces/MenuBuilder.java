package userInterfaces;

import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.menus.*;
import userInterfaces.userPrompt.UserPrompt;

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
                return new StartMenu(userPrompt, user, lp);
            case "admin":
                return new AdminMenu(userPrompt, user, lp);
            case "nonAdmin":
                return new NonAdminMenu(userPrompt, user, lp);
            case "videoBrowse":
                return new VideoBrowseMenu(userPrompt, user, lp);
            default:
                return null;
        }
    }
}
