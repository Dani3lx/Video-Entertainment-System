package controllers.action.actions.startMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.userPrompt.UserPrompt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// Not a controller class, but acts as a UI controller.
public class UserLogin extends MenuAction implements Action {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserLogin(UserPrompt userPrompt, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    public void run() {
        // Takes in a username and password and tries to log in
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.USERNAME);
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        currentUser = um.validateUser(username, password);
        next();
    }

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
