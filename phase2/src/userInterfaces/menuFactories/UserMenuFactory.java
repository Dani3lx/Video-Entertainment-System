package userInterfaces.menuFactories;

import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menus.playlistsMenu.PlaylistMenu;
import userInterfaces.menus.userMenus.AdminMenu;
import userInterfaces.menus.userMenus.NonAdminMenu;
import userInterfaces.menus.userMenus.StartMenu;
import userInterfaces.menus.videosMenus.VideoStudioMenu;
import userInterfaces.userPrompt.UserPrompt;

public class UserMenuFactory implements MenuFactory {
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;


    public UserMenuFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.user = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    public Menu getMenu(MenuEnums type) {
        switch (type) {
            case START:
                return new StartMenu(userPrompt, user, lp, mp);
            case ADMIN:
                return new AdminMenu(userPrompt, user, lp, mp);
            case NONADMIN:
                return new NonAdminMenu(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}
