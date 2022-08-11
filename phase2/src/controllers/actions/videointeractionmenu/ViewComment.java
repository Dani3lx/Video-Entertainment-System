package controllers.actions.videointeractionmenu;

import controllers.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.UserPrompt;

/**
 * View a video's comments.
 */
public class ViewComment extends MenuAction {
    private final Video v;

    /**
     * Creates a ViewComment with the given user prompt, user, language presenter, menu presenter and video.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param v          the video
     */
    public ViewComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video v) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.v = v;
    }

    /**
     * View the video's comments.
     */
    @Override
    public void run() {
        mp.displayAlert(LanguagePresenter.AlertTextType.DISPLAYCOMMENT);
        mp.displayList(vm.getVideoComments(v));
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
