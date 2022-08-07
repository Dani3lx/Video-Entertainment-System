package controllers.action.actions.userMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.MenuFactory;
import userInterfaces.Menus;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class CheckLoginHistory extends MenuAction implements Action {

    public CheckLoginHistory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        List<String> history = um.getHistory(currentUser);
        mp.displayList(history);
        next();
    }

    @Override
    public void next() {
        MenuFactory menuFactory = new MenuFactory(userPrompt, currentUser, lp, mp);
        if (um.getRole(currentUser)) {
            menuFactory.getMenu(Menus.ADMIN).run();
        } else {
            menuFactory.getMenu(Menus.NONADMIN).run();
        }
    }
}
