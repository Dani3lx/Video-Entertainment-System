import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        UserManager um = new UserManager();
        VideoManager vm = new VideoManager();

        DataManager sm = new DataManager(um, vm);
        sm.loadData("phase1/Data.csv"); //Read Data from Data.csv
        sm.loadVideoData("phase1/VideoData.csv"); //Read Data from VideoData.csv
        um.setAllUsers(sm.getUsers());
        vm.setVids(sm.getVideos());
//        LoginMenuDisplayer lmd = new LoginMenuDisplayer(um);
//
//        lmd.startMenu();
        MenuDisplayer md = new MenuDisplayer(um, vm);
        md.startMenu();


    }
}