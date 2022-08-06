package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;

public class ChangePassword extends MenuAction implements Action {

    public ChangePassword(User user) {
        currentUser = user;
    }

    @Override
    public void run() {
        String password = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.PASSWORD);
        um.changePassword(currentUser, password);
        mp.displayAlert(LanguagePresenter.AlertTextType.CHANGEPASSWORD);
        navigateMenu();
    }

    @Override
    public void navigateMenu() {
        MenuBuilder menuBuilder = new MenuBuilder(userPrompt, currentUser);
        if (um.getRole(currentUser)) {
            menuBuilder.getMenu(Menus.ADMIN).run();
        } else {
            menuBuilder.getMenu(Menus.NONADMIN).run();
        }
    }
}