package controllers.actions.userActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.VideosMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

/**
 * Navigates to the video studio menu.
 */
public class VideoStudio extends MenuAction implements Action {

    private final MenuFactory userMenuFactory;

    /**
     * Creates a VideoStudio with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public VideoStudio(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        userMenuFactory = new VideosMenuFactory(userPrompt, user, lp, mp, null);
    }

    /**
     * Goes to the video studio menu.
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
        userMenuFactory.getMenu(MenuEnums.VIDEOSTUDIO).run();
    }
}