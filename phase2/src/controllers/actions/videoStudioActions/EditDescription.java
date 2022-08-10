package controllers.actions.videoStudioActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.NonAdminManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

public class EditDescription extends MenuAction implements Action {

    public EditDescription(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        NonAdminManager nam = new NonAdminManager(vm);

        // Asks for uniqueID of video and the new description for the corresponding video
        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.EDITVIDEO);
        String description = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DESCRIPTION);

        if (nam.editDescription(currentUser, uniqueID, description)){
            mp.displayAlert(LanguagePresenter.AlertTextType.EDIT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.EDIT);
        }
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
    }
}
