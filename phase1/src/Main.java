import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserManager um = new UserManager();
        DataManager sm = new DataManager(um);
        sm.loadData("phase1/Data.csv"); //Read Data from Data.csv
        um.setAllUsers(sm.getUsers());
//        LoginMenuDisplayer lmd = new LoginMenuDisplayer(um);
//
//        lmd.startMenu();
        MenuDisplayer md = new MenuDisplayer(um);
        md.startMenu();
    }
}