import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class AdminHandler extends UserActionHandler {

    AdminManager am;

    public AdminHandler(UserManager um) {
        super(um);
        am = new AdminManager(um);
    }

    public void createAdminUser(String userName, String password) {
        ArrayList<User> all_users = um.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (am.validateUserName(u, userName)) {
                    System.out.println("Failed to create a new account");
                    return;
                }
            }
        }
        User newUser = am.instantiateUser(userName, password, true);
        updateUserHistory(newUser);
        um.updateData(newUser);
    }

    public boolean deleteUser(User currentUser, String name) {

        ArrayList<User> all_users = um.getAllUsers();

        for (User user : all_users) {
            if (am.validateUserName(user, name)) {
                if (am.validateUserName(currentUser, name)) {
                    am.deleteUser(user);
                    return true;
                }
                am.deleteUser(user);
                return false;
            }
        }
        return false;
    }
}