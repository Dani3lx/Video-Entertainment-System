package controllers.action.actions.videoStudioMenu;

import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.VideoEditor;
import usecase.runtimeDataManager.NonAdminManager;
import usecase.runtimeDataManager.VideoManager;
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
        VideoEditor VE = new VideoEditor();
//        VE.editComment();
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
    }
}



