import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class UserActionHandler {
    UserManager um;

    public UserActionHandler(UserManager um) {
        this.um = um;
    }

    public List<String> getHistory(User user) {
        return um.getHistory(user);
    }

    public User loginUser(String userName, String password) {
        return um.validateUser(userName, password);
    }

    public User createUser(String userName, String password) {

        List<User> all_users = um.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (u.getUserName().equals(userName)) {
                    return null;
                }
            }
        }
        User newUser = um.instantiateUser(userName, password, false);

        um.updateData(newUser);
        return newUser;
    }

    public void updateUserHistory(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        um.updateHistory(user, LocalDateTime.now().format(formatter));
    }

    public boolean validateUserPermission(User user) {
        return um.getRole(user);
    }

    public void changePassword(User user, String newPassword) {
        um.changePassword(user, newPassword);
    }
}
