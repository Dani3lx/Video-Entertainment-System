import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        VideoManager vm = new VideoManager();
        UserManager um = new UserManager(vm);

        DataManager sm = new DataManager(um, vm);
        sm.loadData("phase1/Data.csv"); //Read Data from Data.csv
        sm.loadVideoData("phase1/VideoData.csv"); //Read Data from VideoData.csv
        um.setAllUsers(sm.getUsers());
        vm.setVids(sm.getVideos());

        MenuDisplayer md = new MenuDisplayer(um, vm);
        md.startMenu();

    }
}