import java.io.IOException;

// This class is a controller
public class Main {
    public static void main(String[] args) throws IOException {

        DataManager dm = new DataManager();
        dm.loadData("Data.csv"); //Read Data from Data.csv
        UserData.setAllUsers(dm.getUsers()); // todo, same as todo in login menu display
        LoginMenuDisplayer lmd = new LoginMenuDisplayer();

        lmd.startMenu();
    }
}