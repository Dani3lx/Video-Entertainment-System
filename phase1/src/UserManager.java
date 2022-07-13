import java.util.*;

public class UserManager {
    private ArrayList<User> users;

    public User validateUser(String username, String password) {
        if (Objects.isNull(users)) {
            return null;
        } else {
            for (User user : users) {
                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    if (!user.getBanStatus()) {
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public void changePassword(User user, String password) {
        user.setPassword(password);
    }

    public List<String> getHistory(User user) {
        List<String> history = new ArrayList<>(user.getLoginHistory());
        Collections.sort(history);
        return history;
    }

    public void updateHistory(User user, String date) {
        user.getLoginHistory().add(date);
    }

    public User instantiateUser(String userName, String password, boolean banStatus, HashSet<String> history,
                                boolean adminStatus){
        if (adminStatus) {
            return new AdminUser(userName, password, banStatus, history);
        } else {
            return new NonAdminUser(userName, password, banStatus, history);
        }
    }

    public User instantiateUser(String userName, String password, boolean adminStatus){
        if (adminStatus) {
            return new AdminUser(userName, password);
        } else {
            return new NonAdminUser(userName, password);
        }
    }

    public boolean validateUserName(User user, String name) {
        return user.getUserName().equals(name);
    }

    public boolean validateBanStatus(User user) {
        return user.getBanStatus();
    }

    public void updateData(User user) {
        users.add(user);
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }


    public void setAllUsers(ArrayList<User> allUsers) {
        users = allUsers;
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

    public boolean getRole(User user) {
        return user.isAdminInd();
    }
}