package controllers.menuAction.menuActions.startMenu;

import controllers.menuAction.menuActionFactories.MenuAction;
import gateways.DataManager;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

public class ExitProgram implements MenuAction {
    DataManager dataManager;
    UserManager um = UserManager.getInstance();
    VideoManager vm = VideoManager.getInstance();
    PlaylistManager pm = PlaylistManager.getInstance();
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
