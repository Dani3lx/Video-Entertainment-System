import gateways.DataManager;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menu.Menu;
import userInterfaces.userPrompt.TerminalUserPrompt;
import userInterfaces.userPrompt.UserPrompt;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        VideoManager vm = new VideoManager();
        UserManager um = new UserManager(vm);
        PlaylistManager pm = new PlaylistManager();

        DataManager sm = new DataManager(um, vm, pm);
        sm.loadData("phase2/datasets/Data.csv"); //Read data from Data.csv
        sm.loadVideoData("phase2/datasets/VideoData.csv"); //Read data from VideoData.csv
        sm.loadPlaylistData("phase2/datasets/PlaylistData.csv"); //Read data from PlaylistData.csv

        UserPrompt userPrompt = new TerminalUserPrompt();

        MenuBuilder builder = new MenuBuilder(um, vm, pm, userPrompt, null);
        Menu menu = builder.getMenu("start");
        menu.run();
    }
}