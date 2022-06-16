import java.io.IOException;

// This class is a controller
public class Main {
    public static void main(String[] args) throws IOException {
        LoginMenuDisplayer lmd = new LoginMenuDisplayer();
        //DataManager.readCSV("Data.csv"); todo fix readCSV so that it can read the rest of the user information
        //UserData.setAllUsers(DataManager.getUserList());
        lmd.startMenu();
    }
}