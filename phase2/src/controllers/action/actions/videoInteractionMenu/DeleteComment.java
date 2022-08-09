package controllers.action.actions.videoInteractionMenu;

import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.NonAdminManager;
import usecase.runtimeDataManager.UserManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

public class DeleteComment extends MenuAction {
    public DeleteComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager(vm);

        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DELETECOMMENT);
        if (NAM.deleteComment(uniqueID,currentUser)){
            mp.displayAlert(LanguagePresenter.AlertTextType.DELETECOMMENT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.DELETECOMMENT);
        }
        next();
    }

    @Override
    public void next(){
        UserManager UM = new UserManager(vm);
        if (!UM.getRole(currentUser)) {
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }else{
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        }
    }
}