import java.util.ArrayList;

public class Gateway {
    UserManager um;

    public Gateway(UserManager um) {
        this.um = um;
    }
    public void saveChanges(){
        DataManager sm = new DataManager(um);
        sm.setUsers((ArrayList<User>) um.getAllUsers());
        sm.saveData("phase1/Data.csv");
    }
}
