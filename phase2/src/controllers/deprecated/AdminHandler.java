package controllers.deprecated;

import entities.User;
import usecase.runtimeDataManager.AdminManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;

/**
 * Responsible for handling admin users' actions.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-21
 */
public class AdminHandler extends UserActionHandler {
    private final AdminManager am;
    private final ArrayList<User> all_users;

    /**
     * Constructs an admin handler with a record of all the users and videos.
     *
     * @param um this is the user manager which keep tracks of all the users
     * @param vm this is the video manager which keep tracks of all the videos
     */
    public AdminHandler(UserManager um, VideoManager vm) {
        super(um);
        am = new AdminManager(um, vm);
        all_users = um.getAllUsers();
    }

    /**
     * Create a new admin user account if appropriate.
     * Returns whether the creation of an admin user using username and password was successful.
     *
     * @param username the username of a user
     * @param password the password of a user
     * @return whether account creation was successful
     */
    public boolean createAdminUser(String username, String password) {

        // Iterates through the list to check if the username already exist in the database using useCase method.
//        if (!(Objects.isNull(all_users))) {
//            for (User u : all_users) {
//                if (am.validateUserName(u, username)) {
//                    return false;
//                }
//            }
//        }
//
//        // Creates a new user through useCase method
//        User newUser = am.instantiateUser(username, password, true);
//        updateUserHistory(newUser);
//        um.updateData(newUser);
//        return true;

        // Iterates through the list to check if the username already exist in the database using useCase method.
        if (um.noUserExist(username)) {
//            // Creates a new user through useCase method
//            User newUser = am.instantiateUser(username, password, true);
//            updateUserHistory(newUser);
//            um.updateData(newUser);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Deletes the user with name.
     * Returns whether the user deleted was the current user.
     *
     * @param currentUser the user that is currently logged in
     * @param name        a name of the user to be deleted
     * @return whether the user deleted was the current user
     */
    public boolean deleteUser(User currentUser, String name) {

//        // Iterates through the list to check if the user exist in the database
//        for (User user : all_users) {
//            if (am.validateUserName(user, name)) {
//
//                // Checks if the user being deleted is the current user
//                if (am.validateUserName(currentUser, name)) {
//                    am.deleteUser(user);
//                    return true;
//                }
//                am.deleteUser(user);
//                return false;
//            }
//        }
        return false;
    }

    /**
     * Return whether the banning of a user with currentUser and name was successful.
     * Bans the user with name if appropriate.
     *
     * @param currentUser the user that is currently logged in
     * @param name        a name of the user to be banned
     * @return whether the banning of a user using currentUser and name was successful
     */
    public boolean banUser(User currentUser, String name) {

        // Iterates through the list to see if there is a user with matching name
        for (User user : all_users) {
            if (am.validateUserName(user, name)) {

                // Check if the user is the current user or if the user is an admin user
                if (am.validateUserName(currentUser, name) || (am.getRole(user))) {
                    return false;
                } else if (!(am.validateBanStatus(user))) { // Check if the user is unbanned
//                    am.banUser(user);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Return whether the unbanning of a user using name was successful.
     * Unbans the user with name if appropriate.
     *
     * @param name the name of the user to be unbanned
     * @return whether the unbanning process was successful
     */
    public boolean unBanUser(String name) {

        // Iterates through the list to see if a user with name exist in the database
        for (User user : all_users) {
            if (am.validateUserName(user, name)) {

                // Check if the user is already banned
                if (am.validateBanStatus(user)) {
//                    am.unbanUser(user);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}