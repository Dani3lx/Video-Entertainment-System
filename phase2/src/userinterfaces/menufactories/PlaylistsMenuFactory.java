package userinterfaces.menufactories;

import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menus.playlistsmenu.PlaylistManageMenu;
import userinterfaces.menus.playlistsmenu.PlaylistMenu;
import userinterfaces.menus.playlistsmenu.PlaylistOrderMenu;
import userinterfaces.menus.playlistsmenu.PlaylistViewMenu;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;

/**
 * A concrete menu factory that generates playlist related menus.
 */
public class PlaylistsMenuFactory implements MenuFactory {
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final List<Playlist> playlists;

    /**
     * Creates a playlist menu factory with the given user prompt, user, language presenter, menu presenter and playlists.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param playlists  a list of playlists
     */
    public PlaylistsMenuFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> playlists) {
        this.user = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.playlists = playlists;
    }

    /**
     * Generate and return a playlist related menu base on the MenuEnums type.
     *
     * @param type the type of menu to generate
     * @return a menu with the given type
     */
    public Menu getMenu(MenuEnums type) {

        switch (type) {
            case PLAYLIST:
                return new PlaylistMenu(userPrompt, user, lp, mp, playlists);
            case PLAYLISTMANAGE:
                return new PlaylistManageMenu(userPrompt, user, lp, mp, playlists);
            case PLAYLISTVIEW:
                return new PlaylistViewMenu(userPrompt, user, lp, mp, playlists);
            case PLAYLISTORDER:
                return new PlaylistOrderMenu(userPrompt, user, lp, mp, playlists);
            default:
                return null;
        }
    }
}
