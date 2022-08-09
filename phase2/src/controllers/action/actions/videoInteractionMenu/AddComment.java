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

public class AddComment extends MenuAction {
    public AddComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager(vm);

        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.ADDCOMMENT);
        String Comm = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.COMMENT);

        if (NAM.addComment(uniqueID,currentUser, Comm)){
            mp.displayAlert(LanguagePresenter.AlertTextType.ADDCOMMENT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.ADDCOMMENT);
        }
        next();
    }

    @Override
    public void next() {
        if (!um.getRole(currentUser)) {
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }else{
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        }
    }
}
