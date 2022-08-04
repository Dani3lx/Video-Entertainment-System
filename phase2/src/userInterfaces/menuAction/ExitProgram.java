package userInterfaces.menuAction;

import gateways.DataManager;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;

public class ExitProgram implements MenuAction{
    DataManager dataManager;
    public ExitProgram(UserManager um, VideoManager vm, PlaylistManager pm) {
        dataManager = new DataManager(um, vm, pm);
    }

    @Override
    public void run() {
        dataManager.saveData("phase2/datasets/Data.csv");
        dataManager.saveVideoData("phase2/datasets/VideoData.csv");
        dataManager.savePlayListData("phase2/datasets/PlaylistData.csv");
    }

    public void navigateMenu(){
        System.exit(0);
    }
}
