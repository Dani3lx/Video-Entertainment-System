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

public class DeleteVideo extends MenuAction implements Action {

    public DeleteVideo (UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run(){
        NonAdminManager nam = new NonAdminManager();

        // Asks for uniqueID of video that needs to be deleted
        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DELETEVIDEO);

        if (nam.deleteVideo(currentUser, uniqueID)){
            mp.displayAlert(LanguagePresenter.AlertTextType.DELETEVIDEO);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.DELETEVIDEO);
        }
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
    }
}
