import java.util.ArrayList;

public class AdminManager extends UserManager {

    private final ArrayList<User> users;

    public AdminManager(UserManager um) {
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

    public ArrayList<String> returnUsersByBan(ArrayList<User> users, boolean displayBan) {
        ArrayList<String> newList = new ArrayList<>();
        for (User user : users) {
            if ((displayBan == user.getBanStatus()) && !(user instanceof AdminUser)) {
                newList.add("Username: " + user.getUserName());
            }
        }
        return newList;
    }

    public ArrayList<String> returnUsers(ArrayList<User> users) {
        ArrayList<String> newList = new ArrayList<>();
        for (User user : users) {
            newList.add("Username: " + user.getUserName());
        }
        return newList;
    }
}
