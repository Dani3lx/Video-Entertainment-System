import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        UserManager um = new UserManager();
        DataManager dm = new DataManager(um);
        dm.loadData("phase1/Data.csv"); //Read Data from Data.csv
        um.setAllUsers(dm.getUsers());
        LoginMenuDisplayer lmd = new LoginMenuDisplayer(um);

        lmd.startMenu();
    }
}