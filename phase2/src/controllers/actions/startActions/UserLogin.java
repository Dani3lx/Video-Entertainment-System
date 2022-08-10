package controllers.actions.startActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
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
 * Login a user.
 */
public class UserLogin extends MenuAction implements Action {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Creates an userlogin with the given user prompt, language presenter, and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public UserLogin(UserPrompt userPrompt, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Logs the user in.
     */
    public void run() {
        // Takes in a username and password and tries to log in
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.USERNAME);
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        currentUser = um.validateUser(username, password);
        next();
    }

    /**
     * Go to the next menu.
     */
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (Objects.isNull(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.START).run();
        }
        um.updateHistory(currentUser, LocalDateTime.now().format(formatter));
        if (um.getRole(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }
    }
}
