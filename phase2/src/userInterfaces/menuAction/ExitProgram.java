package userInterfaces.menuAction;

import controllers.UserActionHandler;
import gateways.DataManager;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;

public class ExitProgram implements MenuAction{
    DataManager dataManager;
    public ExitProgram(UserManager um, VideoManager vm, PlaylistManager pm) {
        dataManager = new DataManager(um, vm, pm);
    }

    @Override
    public void run() {
        dataManager.saveData("phase1/datasets/Data.csv");
        dataManager.saveVideoData("phase1/datasets/VideoData.csv");
        dataManager.savePlayListData("phase1/datasets/PlaylistData.csv");
        System.exit(0);
    }
}
