package controllers.actions.startActions;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import gateways.DataManager;

/**
 * Exits the program.
 */
public class ExitProgram extends MenuAction implements Action {
    private final DataManager dataManager = new DataManager();

    /**
     * Saves the data.
     */
    @Override
    public void run() {
        dataManager.saveData("phase2/datasets/Data.csv");
        dataManager.saveVideoData("phase2/datasets/VideoData.csv");
        dataManager.savePlayListData("phase2/datasets/PlaylistData.csv");
        next();
    }

    /**
     * Exits the program
     */
    public void next() {
        System.exit(0);
    }
}
