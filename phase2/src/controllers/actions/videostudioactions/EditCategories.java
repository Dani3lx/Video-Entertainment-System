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
 * Edits the video's categories.
 */
public class EditCategories extends MenuAction implements Action {

    /**
     * Create a EditCategories with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public EditCategories(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Edits a video's categories.
     */
    @Override
    public void run() {
        NonAdminManager nam = new NonAdminManager();

        // Asks for uniqueID of video and the new categories for the corresponding video
        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.EDITVIDEO);
        List<String> categories = userPrompt.getMultipleInputs(LanguagePresenter.RequestTextType.CATEGORY);

        if (nam.editCategories(currentUser, uniqueID, categories)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.EDIT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.EDIT);
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
