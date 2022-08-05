import gateways.DataManager;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.MenuBuilder;
import userInterfaces.menus.Menu;
import userInterfaces.userPrompt.TerminalUserPrompt;
import userInterfaces.userPrompt.UserPrompt;

public class Main {
    public static void main(String[] args) {

        VideoManager vm = VideoManager.getInstance();
        UserManager um = UserManager.getInstance();
        PlaylistManager pm = PlaylistManager.getInstance();

        DataManager sm = new DataManager(um, vm, pm);
        sm.loadData("phase2/datasets/Data.csv"); //Read data from Data.csv
        sm.loadVideoData("phase2/datasets/VideoData.csv"); //Read data from VideoData.csv
        sm.loadPlaylistData("phase2/datasets/PlaylistData.csv"); //Read data from PlaylistData.csv

        UserPrompt userPrompt = new TerminalUserPrompt();
        LanguagePresenter lp = new EnglishPresenter();

        // Demo, create a class called RunTimeData that stores all the data that will persist throughout the runtime of the program
        MenuBuilder builder = new MenuBuilder(userPrompt, null, lp);

        Menu menu = builder.getMenu("start");
        menu.run();
    }
}