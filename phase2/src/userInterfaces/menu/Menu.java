package userInterfaces.menu;

import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menuAction.MenuActionFactory;
import userInterfaces.userPrompt.TerminalUserPrompt;
import userInterfaces.userPrompt.UserPrompt;
import userInterfaces.menuAction.MenuAction;

import java.util.List;

public class Menu {
    private List<String> actionList;
    private UserPrompt userPrompt = new TerminalUserPrompt();
    private MenuActionFactory factory;
    private String type;

    public Menu(String type, List<String> actionList, UserManager um, VideoManager vm, PlaylistManager pm) {
        this.actionList = actionList;
        factory = new MenuActionFactory(um, vm, pm);
        this.type = type;
    }

    public void run(){
        int result = userPrompt.getUserActionChoice(type, actionList);
        MenuAction action = factory.getMenuAction(actionList.get(result - 1));
        action.run();
    }
}
