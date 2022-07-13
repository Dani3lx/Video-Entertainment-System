import java.util.ArrayList;
import java.util.Objects;

public class AdminHandler extends UserActionHandler {

    AdminManager am;

    private final ArrayList<User> all_users;

    public AdminHandler(UserManager um) {
        super(um);
        am = new AdminManager(um);
        all_users = um.getAllUsers();
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

    public boolean banUser(User currentUser, String name) {

        for (User user : all_users) {
            if (am.validateUserName(user, name)) {
                if (am.validateUserName(currentUser, name) || (user instanceof AdminUser)) {
                    return true;
                } else {
                    am.banUser(user);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean unBanUser(String name) {
        for (User user : all_users) {
            if (am.validateUserName(user, name)) {
                if (am.validateBanStatus(user)) {
                    am.unbanUser(user);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}