package usecase.runtimeDataManager;

import entities.AdminUser;
import entities.User;

import java.util.ArrayList;

/**
 * This class is responsible for performing all actions pertaining to the AdminUser class.
 *
 * @author Benedek Balla, Daniel Xu, ...
 */
public class AdminManager extends UserManager {

    private final ArrayList<User> users;

    public AdminManager(UserManager um, VideoManager vm) {
        super(vm);
        users = um.getAllUsers();
    }

    /**
     * Sets user ban status to true
     */
    public boolean banUser(User currentUser, String username) {
        for (User user : users) {
            if (validateUserName(user, username)) {
                // Check if the user is the current user or if the user is an admin user
                if (validateUserName(currentUser, username) || (getRole(user))) {
                    return false;
                } else if (!(validateBanStatus(user))) { // Check if the user is unbanned
                    user.setBanStatus(true);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sets user ban status to false
     *
     */
    public boolean unbanUser(String username) {
        for (User user : users) {
            if (validateUserName(user, username) && (validateBanStatus(user))) {
                user.setBanStatus(false);
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the user from the arraylist of users
     */
    public boolean deleteUser(String username) {
        for (User u : users) {
            if (validateUserName(u, username)) {
                users.remove(u);
                return true;
            }
        }
        return false;
    }

    /**
     * @param users      of type Users
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
