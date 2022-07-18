import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        VideoManager vm = new VideoManager();
        UserManager um = new UserManager(vm);
        PlaylistManager pm = new PlaylistManager();

        DataManager sm = new DataManager(um, vm, pm);
        sm.loadData("phase1/Data.csv"); //Read data from Data.csv
        sm.loadVideoData("phase1/VideoData.csv"); //Read data from VideoData.csv
        sm.loadPlaylistData("phase1/PlaylistData.csv"); //Read data from PlaylistData.csv
        um.setAllUsers(sm.getUsers());
        vm.setVids(sm.getVideos());
        pm.setPlaylists(sm.getPlaylists());

        MenuDisplayer md = new MenuDisplayer(um, vm, pm);
        md.startMenu();

    }
}