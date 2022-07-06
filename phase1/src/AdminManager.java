import java.util.List;

public class AdminManager extends UserManager{

    private UserManager um;

    private List<User> users;

    public AdminManager(UserManager um){
        this.um = um;
        users = um.getAllUsers();
    }

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
