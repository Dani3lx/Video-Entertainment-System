package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.AdminManager;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.ArrayList;

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
        AdminManager am = new AdminManager(um, vm);
        User deleted_user = null;

        // Displays all users and asks for a username as input to delete the user
        mp.displayAlert(LanguagePresenter.AlertTextType.ALLUSERS);
        mp.displayList(am.returnUsers(um.getAllUsers()));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.DELETEUSER);

        // Iterates through the list to check if the user exist in the database
        for (User user : um.getAllUsers()) {
            if(am.validateUserName(user, username)){

                // Checks if the user being deleted is the current user
                if (am.validateUserName(currentUser, username)) {
                    self_delete = true;
                }
                deleted_user = user;
            }
        }
        if (deleted_user != null){
            am.deleteUser(deleted_user);
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
