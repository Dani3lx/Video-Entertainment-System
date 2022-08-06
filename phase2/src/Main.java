import gateways.DataManager;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.MenuBuilder;
import userInterfaces.Menus;
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
        LanguagePresenter lp = new EnglishPresenter();
        UserPrompt userPrompt = new TerminalUserPrompt(lp);
        MenuBuilder builder = new MenuBuilder(userPrompt, null);

        Menu menu = builder.getMenu(Menus.START);
        menu.run();
    }
}