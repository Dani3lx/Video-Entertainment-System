package userInterfaces.menu;

import entities.User;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import controllers.menuAction.MenuActionFactory;
import userInterfaces.userPrompt.UserPrompt;
import controllers.menuAction.MenuAction;

import java.util.List;

public class Menu {
    private final List<String> actionList;
    private final UserPrompt userPrompt;
    private final MenuActionFactory factory;
    private final String type;

    public Menu(String type, List<String> actionList, UserPrompt userPrompt, User user) {
        this.actionList = actionList;
        factory = new MenuActionFactory(userPrompt, user);
        this.type = type;
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(type, actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}
