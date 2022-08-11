package controllers.actions.videoInteractionMenu;

import controllers.actions.MenuAction;
import entities.User;
import entities.Video;

import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.NonAdminManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

/**
 * Adds a comment to a video.
 */
public class AddComment extends MenuAction {
    private final Video v;

    /**
     * Creates a AddComment with the given user prompt, user, language presenter, menu presenter and video.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param v          the video
     */
    public AddComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video v) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.v = v;
    }

    /**
     * Adds a comment to the video.
     */
    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager();

        String Comm = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.COMMENT);

        if (NAM.addComment(v, currentUser, Comm)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.ADDCOMMENT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.ADDCOMMENT);
        }
        next();
    }

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (!um.getRole(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        }
    }
}
