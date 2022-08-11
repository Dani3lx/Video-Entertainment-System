package userinterfaces.menufactories;

import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menus.usermenus.AdminMenu;
import userinterfaces.menus.usermenus.NonAdminMenu;
import userinterfaces.menus.usermenus.StartMenu;
import userinterfaces.userprompt.UserPrompt;

/**
 * A concrete menu factory that generates user related menus.
 */
public class UserMenuFactory implements MenuFactory {
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;

    /**
     * Creates a user menu factory with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public UserMenuFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.user = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Generate and return a user related menu base on the MenuEnums type.
     *
     * @param type the type of menu to generate
     * @return a menu with the given type
     */
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
