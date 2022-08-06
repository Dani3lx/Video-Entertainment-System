package controllers.action.actions.startMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import presenters.language.LanguagePresenter;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// Not a controller class, but acts as a UI controller.
public class UserLogin extends MenuAction implements Action {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void run() {
        // Takes in a username and password and tries to log in
        String username = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.USERNAME);
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        currentUser = um.validateUser(username, password);
        navigateMenu();
    }

    public void navigateMenu() {
        MenuBuilder menuBuilder = new MenuBuilder(userPrompt, currentUser);
        if (Objects.isNull(currentUser)) {
            menuBuilder.getMenu(Menus.START).run();
        }
        um.updateHistory(currentUser, LocalDateTime.now().format(formatter));
        if (um.getRole(currentUser)) {
            menuBuilder.getMenu(Menus.ADMIN).run();
        } else {
            menuBuilder.getMenu(Menus.NONADMIN).run();
        }
    }
}
