package controllers.actions.startActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import gateways.DataManager;

public class ExitProgram extends MenuAction implements Action {
    DataManager dataManager = new DataManager();
    public ExitProgram() {}

    @Override
    public void run() {
        dataManager.saveData("phase2/datasets/Data.csv");
        dataManager.saveVideoData("phase2/datasets/VideoData.csv");
        dataManager.savePlayListData("phase2/datasets/PlaylistData.csv");
        next();
    }

    public void next(){
        System.exit(0);
    }
}
