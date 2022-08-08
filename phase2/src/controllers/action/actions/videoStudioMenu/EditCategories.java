package controllers.action.actions.videoStudioMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.NonAdminManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.ArrayList;
import java.util.Arrays;

public class EditCategories extends MenuAction implements Action {

    public EditCategories(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run(){
        NonAdminManager nam = new NonAdminManager(vm);

        // Asks for uniqueID of video and the new categories for the corresponding video
        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.EDITVIDEO);
        String categories = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.CATEGORIES);

        if (nam.editTitle(currentUser, uniqueID, categories)){
            mp.displayAlert(LanguagePresenter.AlertTextType.EDIT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.EDIT);
        }
        next();
    }

    @Override
    public void next(){
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
    }
}
