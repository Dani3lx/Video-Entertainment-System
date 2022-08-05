package controllers.menuAction.menuActions.startMenu;

import controllers.menuAction.menuActionFactories.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import presenters.menuPresenter.TerminalMenuPresenter;
import usecase.runtimeDataManager.UserManager;
import userInterfaces.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class AccountCreation implements MenuAction {
    UserPrompt userPrompt;
    MenuBuilder menuBuilder;
    UserManager um = UserManager.getInstance();
    User currentUser;
    MenuPresenter mp = new TerminalMenuPresenter();
    LanguagePresenter lp;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AccountCreation(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
    }

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
        navigateMenu();
    }

    public void navigateMenu() {
        menuBuilder = new MenuBuilder(userPrompt, currentUser, lp);
        if (Objects.isNull(currentUser)) {
            menuBuilder.getMenu("start").run();
        } else {
            um.updateHistory(currentUser, LocalDateTime.now().format(formatter));
            menuBuilder.getMenu("nonAdmin").run();
        }
    }
}
