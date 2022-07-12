import java.io.IOException;
import java.util.ArrayList;

public class Gateway {
    UserManager um = new UserManager();
    DataManager sm = new DataManager(um);

    public void saveChanges(){
        sm.setUsers((ArrayList<User>) um.getAllUsers());
        sm.saveData("phase1/Data.csv");
    }

    public void getData() throws IOException {
        sm.loadData("phase1/Data.csv"); //Read Data from Data.csv
        um.setAllUsers(sm.getUsers());
        LoginMenuDisplayer lmd = new LoginMenuDisplayer(um, this);

        lmd.startMenu();
    }
}
