package userInterfaces.menus;

import controllers.action.actionFactories.UserActionFactory;
import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class NonAdminMenu implements Menu{
    private final UserPrompt userPrompt;
    private final ActionFactory factory;

    private final List<String> actionList = List.of(new String[]{"change password", "check history", "logout", "browse video", "view playlist", "video studio"});

    public NonAdminMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        factory = new UserActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(LanguagePresenter.MenuTextType.NONADMIN, actionList);
        Action action = factory.getAction(actionList.get(result - 1));
        action.run();
    }
}