import gateways.DataManager;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import presenters.menuPresenter.TerminalMenuPresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.MenuFactory;
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
        MenuPresenter mp = new TerminalMenuPresenter(lp);
        UserPrompt userPrompt = new TerminalUserPrompt(mp);
        MenuFactory menuFactory = new MenuFactory(userPrompt, null, lp, mp);
        Menu menu = menuFactory.getMenu(Menus.START);
        menu.run();
    }
}