package controllers.actions.playlistactions;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.PlaylistsMenuFactory;
import userinterfaces.userprompt.UserPrompt;
/**
 * This class is responsible for returning to the playlist search menu
 */
public class ReturnPL extends MenuAction implements Action {

    private final MenuFactory playlistsMenuFactory;
    /**
     * Creates a constructor with the given user prompt, user, language presenter, menu presenter and associated playlist.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public ReturnPL(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
        playlistsMenuFactory = new PlaylistsMenuFactory(userPrompt, currentUser, lp, mp, null);
    }
    /**
     * This method is how the abstract factory completes the action this class is responsible for
     */
    @Override
    public void run() {
        next();
    }
    /**
     * This method is how the program moves from running the action to a new menu display
     */
    @Override
    public void next() {
        playlistsMenuFactory.getMenu(MenuEnums.PLAYLIST).run();
    }
}
