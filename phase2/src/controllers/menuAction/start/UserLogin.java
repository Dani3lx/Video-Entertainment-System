package controllers.menuAction.start;

import controllers.old.UserActionHandler;
import controllers.menuAction.MenuAction;
import entities.User;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

// Not a controller class, but acts as a UI controller.
public class UserLogin implements MenuAction {
    LanguagePresenter lp;
    UserPrompt userPrompt;
    User currentUser;
    UserManager um = UserManager.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserLogin(UserPrompt userPrompt, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.lp = lp;
    }

    public void run() {
        // Takes in a username and password and tries to log in
        String username = userPrompt.getUserStringInput(lp.getRequestText("username"));
        String password = userPrompt.getUserStringInput(lp.getRequestText("password"));
        currentUser = um.validateUser(username, password);
        navigateMenu();
    }

    public void navigateMenu() {
        MenuBuilder menuBuilder = new MenuBuilder(userPrompt, currentUser, lp);
        if (Objects.isNull(currentUser)) {
            menuBuilder.getMenu("start").run();
        }
        um.updateHistory(currentUser, LocalDateTime.now().format(formatter));
        if (um.getRole(currentUser)) {
            menuBuilder.getMenu("admin").run();
        } else {
            menuBuilder.getMenu("nonAdmin").run();
        }
    }
}
