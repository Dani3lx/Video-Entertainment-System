package controllers.actions.userActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.AdminManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

public class DeleteUser extends MenuAction implements Action {

    private boolean self_delete;

    public DeleteUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run(){
        AdminManager am = new AdminManager();

        // Displays all users and asks for a username as input to delete the user
        mp.displayAlert(LanguagePresenter.AlertTextType.ALLUSERS);
        mp.displayList(am.returnUsers(um.getAllUsers()));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DELETEUSER);

        if (am.validateUserName(currentUser, username)) {
            self_delete = true;
        }

        if (am.deleteUser(username)) {
            mp.displayAlert(LanguagePresenter.AlertTextType.DELETEUSER);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.DELETEUSER);
        }
        next();
    }

    @Override
    public void next(){
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (self_delete) {
            userMenuFactory.getMenu(MenuEnums.START).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        }
    }
}
