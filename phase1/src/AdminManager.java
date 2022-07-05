import java.util.List;

public class AdminManager extends UserManager{
    private final List<User> users = UserData.getAllUsers();
    public void banUser(User user) {
        user.setBanStatus(true);
    }

    public void unbanUser(User user) {
        user.setBanStatus(false);
    }

    public void deleteUser(User user) {
        users.remove(user);
    }
}
