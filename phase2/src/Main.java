import gateways.DataManager;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menu.Menu;
import controllers.menuAction.MenuActionFactory;
import userInterfaces.userPrompt.TerminalUserPrompt;
import userInterfaces.userPrompt.UserPrompt;

public class Main {
    public static void main(String[] args) {

        VideoManager vm = VideoManager.getInstance();
        UserManager um = UserManager.getInstance();
        PlaylistManager pm = new PlaylistManager();

        DataManager sm = new DataManager(um, vm, pm);
        sm.loadData("phase2/datasets/Data.csv"); //Read data from Data.csv
        sm.loadVideoData("phase2/datasets/VideoData.csv"); //Read data from VideoData.csv
        sm.loadPlaylistData("phase2/datasets/PlaylistData.csv"); //Read data from PlaylistData.csv

        UserPrompt userPrompt = new TerminalUserPrompt();



        // Demo, create a class called RunTimeData that stores all the data that will persist throughout the runtime of the program
        MenuBuilder builder = new MenuBuilder(um, vm, pm, userPrompt, null);

        MenuActionFactory factory = new MenuActionFactory(um, vm, pm, userPrompt, null);

        Menu menu = builder.getMenu("start");
        menu.run();
    }
}