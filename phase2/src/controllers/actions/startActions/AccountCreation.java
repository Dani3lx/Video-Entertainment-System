package controllers.actions.startActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Creates a new user account.
 */
public class AccountCreation extends MenuAction implements Action {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates a new accountcreation with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public AccountCreation(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     *
     */
    public void run() {
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.USERNAME);
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);

        if (um.noUserExist(username)) {
            // Creates a new user through useCase method
            currentUser = um.instantiateUser(username, password, false);
            um.updateData(currentUser);
            mp.displayAlert(LanguagePresenter.AlertTextType.CREATEACCOUNT);
        } else {
            currentUser = null;
            mp.displayError(LanguagePresenter.ErrorTextType.CREATEACCOUNT);
        }
        next();
    }

    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (Objects.isNull(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.START).run();
        } else {
            um.updateHistory(currentUser, LocalDateTime.now().format(formatter));
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }
    }
}
