package controllers.actions.videostudioactions;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import usecase.runtimedatamanager.NonAdminManager;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;

/**
 * Uploads a video.
 */
public class UploadVideo extends MenuAction implements Action {

    /**
     * Create a UploadVideo with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public UploadVideo(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Uploads a video.
     */
    @Override
    public void run() {
        NonAdminManager nam = new NonAdminManager();

        // Asks for all the required inputs to upload a video
        String title = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.TITLE);
        String description = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DESCRIPTION);
        List<String> categories = userPrompt.getMultipleInputs(LanguagePresenter.RequestTextType.CATEGORY);
        String vidLink = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.VIDLINK);

        if (nam.uploadVideo(currentUser, title, description, categories, vidLink)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.UPLOADVIDEO);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.UPLOADVIDEO);
        }
        next();
    }

    /**
     * Navigates to the next appropriate menu.
     */
    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
    }
}
