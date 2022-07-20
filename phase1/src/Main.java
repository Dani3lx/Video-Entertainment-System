import gateways.DataManager;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.MenuDisplayer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        VideoManager vm = new VideoManager();
        UserManager um = new UserManager(vm);
        PlaylistManager pm = new PlaylistManager();

        DataManager sm = new DataManager(um, vm, pm);
        sm.loadData("phase1/datasets/Data.csv"); //Read data from Data.csv
        sm.loadVideoData("phase1/datasets/VideoData.csv"); //Read data from VideoData.csv
        sm.loadPlaylistData("phase1/datasets/PlaylistData.csv"); //Read data from PlaylistData.csv
        um.setAllUsers(sm.getUsers());
        vm.setVids(sm.getVideos());
        pm.setPlaylists(sm.getPlaylists());

        MenuDisplayer md = new MenuDisplayer(um, vm, pm);
        md.startMenu();

    }
}