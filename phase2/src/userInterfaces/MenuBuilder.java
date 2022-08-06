package userInterfaces;

import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.menus.*;
import userInterfaces.userPrompt.UserPrompt;

public class MenuBuilder {
    private final User user;
    private final UserPrompt userPrompt;


    public MenuBuilder(UserPrompt userPrompt, User user) {
        this.user = user;
        this.userPrompt = userPrompt;
    }

    public Menu getMenu(Menus type) {
        switch (type) {
            case START:
                return new StartMenu(userPrompt, user);
            case ADMIN:
                return new AdminMenu(userPrompt, user);
            case NONADMIN:
                return new NonAdminMenu(userPrompt, user);
            case VIDEOBROWSE:
                return new VideoBrowseMenu(userPrompt, user);
            default:
                return null;
        }
    }
}
