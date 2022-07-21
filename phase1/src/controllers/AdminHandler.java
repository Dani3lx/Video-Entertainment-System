package controllers;

import entities.AdminUser;
import entities.User;
import usecase.AdminManager;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Responsible for handling admin users' actions.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class AdminHandler extends UserActionHandler {

    AdminManager am;

    /**
     * Constructs an admin handler with a record of all the users and videos.
     *
     * @param um this is the user manager which keep tracks of all the users
     * @param vm this is the video manager which keep tracks of all the videos
     */
    public AdminHandler(UserManager um, VideoManager vm) {
        super(um);
        am = new AdminManager(um, vm);
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
        ArrayList<User> all_users = um.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (am.validateUserName(u, username)) {
                    return false;
                }
            }
        }
        User newUser = am.instantiateUser(username, password, true);
        updateUserHistory(newUser);
        um.updateData(newUser);
        return true;
    }

    /**
     * Deletes a user using name.
     * Returns whether the user deleted was the current user.
     *
     * @param currentUser the user that is currently logged in
     * @param name        a name of the user to be deleted
     * @return whether the user deleted was the current user
     */
    public boolean deleteUser(User currentUser, String name) {
        ArrayList<User> all_users = um.getAllUsers();
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

    /**
     * Return whether the banning of a user using currentUser and name was successful.
     * Bans the user with name if appropriate.
     *
     * @param currentUser the user that is currently logged in
     * @param name        a name of the user to be banned
     * @return whether the banning of a user using currentUser and name was successful
     */
    public boolean banUser(User currentUser, String name) {
        ArrayList<User> all_users = um.getAllUsers();
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

    /**
     * Return whether the unbanning of a user using name was successful.
     * Unbans the user with name if appropriate.
     *
     * @param name the name of the user to be unbanned
     * @return whether the unbanning process was successful
     */
    public boolean unBanUser(String name) {
        ArrayList<User> all_users = um.getAllUsers();
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