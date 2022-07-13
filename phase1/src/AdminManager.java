import java.util.ArrayList;

public class AdminManager extends UserManager{

    private UserManager um;

    private ArrayList<User> users;

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

    public void displayAllUsers(ArrayList<User> users, boolean displayBan) {
        for (User user : users) {
            if ((displayBan == user.getBanStatus()) && !(user instanceof AdminUser)) {
                System.out.println("Username: " + user.getUserName());
            }
        }
    }

    public void displayAllUsers(ArrayList<User> users) {
        for (User user : users) {
            System.out.println("Username: " + user.getUserName());
        }
    }
}
