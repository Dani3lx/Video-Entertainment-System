import gateways.DataManager;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import presenters.menuPresenter.TerminalMenuPresenter;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.Menu;
import userInterfaces.userPrompt.TerminalUserPrompt;
import userInterfaces.userPrompt.UserPrompt;

public class Main {
    public static void main(String[] args) {


        DataManager sm = new DataManager();
        sm.loadData("phase2/datasets/Data.csv"); //Read data from Data.csv
        sm.loadVideoData("phase2/datasets/VideoData.csv"); //Read data from VideoData.csv
        sm.loadPlaylistData("phase2/datasets/PlaylistData.csv"); //Read data from PlaylistData.csv


        LanguagePresenter lp = new EnglishPresenter();
        MenuPresenter mp = new TerminalMenuPresenter(lp);
        UserPrompt userPrompt = new TerminalUserPrompt(mp);
        UserMenuFactory userMenuFactory = new UserMenuFactory(userPrompt, null, lp, mp);
        Menu menu = userMenuFactory.getMenu(MenuEnums.START);
        menu.run();
    }
}