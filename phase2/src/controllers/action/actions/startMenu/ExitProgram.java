package controllers.action.actions.startMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.MenuAction;
import gateways.DataManager;

public class ExitProgram extends MenuAction implements Action {
    DataManager dataManager;
    public ExitProgram() {
        dataManager = new DataManager(um, vm, pm);
    }

    @Override
    public void run() {
        dataManager.saveData("phase2/datasets/Data.csv");
        dataManager.saveVideoData("phase2/datasets/VideoData.csv");
        dataManager.savePlayListData("phase2/datasets/PlaylistData.csv");
        navigateMenu();
    }

    public void navigateMenu(){
        System.exit(0);
    }
}
