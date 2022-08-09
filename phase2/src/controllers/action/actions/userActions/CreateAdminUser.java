package controllers.action.actions.userActions;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.runtimeDataManager.AdminManager;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAdminUser extends MenuAction implements Action {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public CreateAdminUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    @Override
    public void run() {
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.USERNAME);
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        AdminManager am = new AdminManager(um, vm);
        if (um.noUserExist(username)) {
            // Creates a new user through useCase method
            User newUser = am.instantiateUser(username, password, true);
            um.updateHistory(newUser, LocalDateTime.now().format(formatter));
            um.updateData(newUser);
            mp.displayAlert(LanguagePresenter.AlertTextType.CREATEACCOUNT);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.CREATEACCOUNT);
        }
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.ADMIN).run();
    }
}
