package controllers.actions.videoInteractionMenu;

import controllers.actionFactories.Action;

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
 * Deletes user comment from a video.
 */
public class DeleteComment extends MenuAction implements Action {
    private final Video v;

    /**
     * Creates a DeleteComment with the given user prompt, user, language presenter, menu presenter and video.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param v          the video
     */
    public DeleteComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video v) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.v = v;
    }

    /**
     * Deletes user comment from a video.
     */
    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager();
        if (NAM.deleteComment(v, currentUser)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.DELETECOMMENT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.DELETECOMMENT);
        }
        next();
    }

    /**
     * Navigates to the next appropriate menu.
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
