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

public class UnbanUser extends MenuAction implements Action {


    public UnbanUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run(){
        AdminManager am = new AdminManager();

        // Displays all banned users and asks for input to unban a user
        mp.displayAlert(LanguagePresenter.AlertTextType.BANNEDUSERS);
        mp.displayList(am.returnUsersByBan(um.getAllUsers(), true));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.UNBANUSER);

        if (am.unbanUser(username)){
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
