import java.util.ArrayList;
import java.util.Objects;

public class AdminHandler extends UserActionHandler {

    AdminManager am;

    private final ArrayList<User> all_users;

    public AdminHandler(UserManager um, VideoManager vm) {
        super(um);
        am = new AdminManager(um, vm);
        all_users = um.getAllUsers();
    }

    public boolean createAdminUser(String userName, String password) {
        ArrayList<User> all_users = um.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (am.validateUserName(u, userName)) {
                    return false;
                }
            }
        }
        User newUser = am.instantiateUser(userName, password, true);
        updateUserHistory(newUser);
        um.updateData(newUser);
        return true;
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