import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DataManager dm = new DataManager();
        dm.loadData("phase0/Data.csv"); //Read Data from Data.csv
        UserData.setAllUsers(dm.getUsers());
        LoginMenuDisplayer lmd = new LoginMenuDisplayer();

        lmd.startMenu();
    }
}