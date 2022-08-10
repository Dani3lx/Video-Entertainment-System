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

import java.util.List;

public class UploadVideo extends MenuAction implements Action {

    public UploadVideo (UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run(){
        NonAdminManager nam = new NonAdminManager(vm);

        // Asks for all the required inputs to upload a video
        String title = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.TITLE);
        String description = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DESCRIPTION);
        List<String> categories = userPrompt.getMultipleInputs(LanguagePresenter.RequestTextType.CATEGORY);
        String vidLink = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.VIDLINK);

        if (nam.uploadVideo(currentUser, title, description, categories, vidLink)){
            mp.displayAlert(LanguagePresenter.AlertTextType.UPLOADVIDEO);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.UPLOADVIDEO);
        }
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
    }
}
