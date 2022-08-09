package controllers.action.actions.videoInteractionMenu;

import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.NonAdminManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

public class EditComment extends MenuAction {

    public EditComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager(vm);
        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.EDITCOMMENT);
        String newComm = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.COMMENT);

        if (NAM.editComment(uniqueID, currentUser, newComm)){
            mp.displayAlert(LanguagePresenter.AlertTextType.EDITCOMMENT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.EDITCOMMENT);
        }
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
    }
}



