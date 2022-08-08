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

public class UnbanUser extends MenuAction implements Action {

    private boolean unbanned;

    public UnbanUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run(){
        AdminManager am = new AdminManager(um, vm);

        // Displays all banned users and asks for input to unban a user
        mp.displayAlert(LanguagePresenter.AlertTextType.BANNEDUSERS);
        mp.displayList(am.returnUsersByBan(um.getAllUsers(), true));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.UNBANUSER);

        // Iterates through the list to see if there is a user with matching username
        for (User user : um.getAllUsers()){
            if (am.validateUserName(user, username)){

                // Check if the user is already banned
                if (am.validateBanStatus(user)){
                    am.unbanUser(user);
                    unbanned = true;
                }
            }
        }
        if (unbanned){
            mp.displayAlert(LanguagePresenter.AlertTextType.UNBANNED);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.UNBANNED);
        }
        next();
    }

    @Override
    public void next(){
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.ADMIN).run();
    }
}
