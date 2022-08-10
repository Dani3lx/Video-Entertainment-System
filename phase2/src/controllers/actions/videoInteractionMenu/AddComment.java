package controllers.actions.videoInteractionMenu;

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

public class AddComment extends MenuAction {
    private final Video v;
    public AddComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video v) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.v=v;
    }

    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager();

        //String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.ADDCOMMENT);
        String Comm = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.COMMENT);

        if (NAM.addComment(v.getUniqueID(),currentUser, Comm)){
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
