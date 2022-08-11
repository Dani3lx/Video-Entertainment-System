package controllers.actions.useractions;

import controllers.actionfactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import usecase.runtimedatamanager.AdminManager;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.MenuFactory;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.UserPrompt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates a new admin account.
 */
public class CreateAdminUser extends MenuAction implements Action {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a CreateAdminUser with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public CreateAdminUser(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        currentUser = user;
    }

    /**
     * Creates a new admin account.
     */
    @Override
    public void run() {
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.USERNAME);
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        AdminManager am = new AdminManager();
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

    /**
     * Go to the next appropriate menu.
     */
    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        userMenuFactory.getMenu(MenuEnums.ADMIN).run();
    }
}
