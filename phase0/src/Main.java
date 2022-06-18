import java.io.IOException;

// This class is a controller
public class Main {
    public static void main(String[] args) throws IOException {

        DataManager dm = new DataManager();
        dm.loadData("Data.csv"); //Read Data from Data.csv
        LoginMenuDisplayer lmd = new LoginMenuDisplayer();

        lmd.startMenu();
    }
}