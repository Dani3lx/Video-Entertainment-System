package usecase;

import entities.AdminUser;
import entities.User;

import java.util.ArrayList;

public class AdminManager extends UserManager {

    private final ArrayList<User> users;

    public AdminManager(UserManager um, VideoManager vm) {
        super(vm);
        users = um.getAllUsers();
    }

    /**
     * Sets user ban status to true
     * @param user of type User
     */
    public void banUser(User user) {
        user.setBanStatus(true);
    }

    /**
     * Sets user ban status to false
     * @param user of type User
     */
    public void unbanUser(User user) {
        user.setBanStatus(false);
    }

    /**
     * Removes the user from the arraylist of users
     * @param user of type User
     */
    public void deleteUser(User user) {
        users.remove(user);
    }

    /**
     *
     * @param users of type Users
     * @param displayBan ban status of user
     * @return List of usernames corresponding to the displayBan
     */
    public ArrayList<String> returnUsersByBan(ArrayList<User> users, boolean displayBan) {
        ArrayList<String> newList = new ArrayList<>();
        for (User user : users) {
            if ((displayBan == user.getBanStatus()) && !(user instanceof AdminUser)) {
                newList.add("Username: " + user.getUserName());
            }
        }
        return newList;
    }

    /**
     *
     * @param users contains items of type User
     * @return List of all usernames in users
     */
    public ArrayList<String> returnUsers(ArrayList<User> users) {
        ArrayList<String> newList = new ArrayList<>();
        for (User user : users) {
            newList.add("Username: " + user.getUserName());
        }
        return newList;
    }
}
