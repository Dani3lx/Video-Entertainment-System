package controllers.action.actions.videoInteractionMenu;
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
public class EditComment extends MenuAction {
    private final Video v;
    public EditComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video v) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.v=v;
    }

    @Override
    public void run() {
        NonAdminManager NAM = new NonAdminManager(vm);
//        String uniqueID = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.EDITCOMMENT);
        String newComm = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.NEWCOMMENT);

        if (NAM.editComment(v.getUniqueID(), currentUser, newComm)){
            mp.displayAlert(LanguagePresenter.AlertTextType.EDITCOMMENT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.EDITCOMMENT);
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