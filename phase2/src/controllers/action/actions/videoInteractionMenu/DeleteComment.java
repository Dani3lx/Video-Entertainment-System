package controllers.action.actions.videoInteractionMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;

import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.NonAdminManager;
import usecase.runtimeDataManager.UserManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

public class DeleteComment extends MenuAction implements Action {
    private final Video v;
    public DeleteComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video v) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.v=v;
    }

    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager(vm);

        //String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DELETECOMMENT);
        if (NAM.deleteComment(v.getUniqueID(),currentUser)){
            mp.displayAlert(LanguagePresenter.AlertTextType.DELETECOMMENT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.DELETECOMMENT);
        }
        next();
    }

    @Override
    public void next(){

        if (!um.getRole(currentUser)) {
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }else{
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        }
    }
}
