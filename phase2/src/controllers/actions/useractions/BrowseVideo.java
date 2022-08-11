package controllers.actions.useractions;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.VideosMenuFactory;
import userinterfaces.userprompt.UserPrompt;

/**
 * Navigates to the browse video menu.
 */
public class BrowseVideo extends MenuAction implements Action {

    private final MenuFactory videosMenuFactory;

    /**
     * Creates a BrowseVideo with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public BrowseVideo(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        videosMenuFactory = new VideosMenuFactory(userPrompt, user, lp, mp, null);
    }

    /**
     * Goes to the browse video menu.
     */
    @Override
    public void run() {
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        videosMenuFactory.getMenu(MenuEnums.VIDEOBROWSE).run();
    }
}
