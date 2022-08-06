package userInterfaces;

import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menus.*;
import userInterfaces.userPrompt.UserPrompt;

public class MenuBuilder {
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;


    public MenuBuilder(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.user = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    public Menu getMenu(Menus type) {
        switch (type) {
            case START:
                return new StartMenu(userPrompt, user, lp, mp);
            case ADMIN:
                return new AdminMenu(userPrompt, user, lp, mp);
            case NONADMIN:
                return new NonAdminMenu(userPrompt, user, lp, mp);
            case VIDEOBROWSE:
                return new VideoBrowseMenu(userPrompt, user, lp, mp);
            case PLAYLIST:
                return new PlaylistMenu(userPrompt, user, lp, mp);
            case VIDEOSTUDIO:
                return new VideoStudioMenu(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}
