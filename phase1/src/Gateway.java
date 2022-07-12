import java.io.IOException;
import java.util.ArrayList;

public class Gateway {
    UserManager um = new UserManager();
    DataManager dm = new DataManager(um);

    public void saveChanges(){
        DataManager sm = new DataManager(um);
        sm.setUsers((ArrayList<User>) um.getAllUsers());
        sm.saveData("phase1/Data.csv");
    }

    public void getData() throws IOException {
        dm.loadData("phase1/Data.csv"); //Read Data from Data.csv
        um.setAllUsers(dm.getUsers());
        LoginMenuDisplayer lmd = new LoginMenuDisplayer(um, this);

        lmd.startMenu();
    }
}
