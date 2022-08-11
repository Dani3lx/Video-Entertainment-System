import gateways.DataManager;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import presenters.menupresenter.TerminalMenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menufactories.Menu;
import userinterfaces.menufactories.UserMenuFactory;
import userinterfaces.userprompt.TerminalUserPrompt;
import userinterfaces.userprompt.UserPrompt;

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