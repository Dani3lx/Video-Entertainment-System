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

public class BanUser extends MenuAction implements Action {

    private boolean banned;

    public BanUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run(){
        AdminManager am = new AdminManager(um, vm);

        // Displays all unbanned users and asks for input to ban a user
        mp.displayAlert(LanguagePresenter.AlertTextType.UNBANNEDUSERS);
        mp.displayList(am.returnUsersByBan(um.getAllUsers(), false));
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.BANUSER);

        // Iterates through the list to see if there is a user with matching username
        for (User user : um.getAllUsers()){
            if (am.validateUserName(user, username)){

                // Check if the user is unbanned
                if (!(am.validateBanStatus(user))) {
                    am.banUser(user);
                    banned = true;
                }
            }
        }
        if (banned){
            mp.displayAlert(LanguagePresenter.AlertTextType.BANNED);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.BANNED);
        }
        next();
    }

    @Override
    public void next(){
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.ADMIN).run();
    }
}
