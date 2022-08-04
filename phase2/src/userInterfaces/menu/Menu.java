package userInterfaces.menu;

import entities.User;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menuAction.MenuActionFactory;
import userInterfaces.userPrompt.TerminalUserPrompt;
import userInterfaces.userPrompt.UserPrompt;
import userInterfaces.menuAction.MenuAction;

import java.util.List;

public class Menu {
    private final List<String> actionList;
    private final UserPrompt userPrompt;
    private final MenuActionFactory factory;
    private final String type;

    public Menu(String type, List<String> actionList, UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt, User user) {
        this.actionList = actionList;
        factory = new MenuActionFactory(um, vm, pm, userPrompt, user);
        this.type = type;
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserActionChoice(type, actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}
