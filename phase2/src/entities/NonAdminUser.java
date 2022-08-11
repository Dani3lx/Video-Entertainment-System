package entities;

import java.util.HashSet;

/**
 * This represents non admin user.
 */

public class NonAdminUser extends User {

    /**
     * Constructs a non admin user using username and password.
     *
     * @param userName the username
     * @param password the password
     */
    public NonAdminUser(String userName, String password) {
        super(userName, password);
        this.setAdminInd(false);
    }

    /**
     * Constructs a non admin user using username, password, ban status and login history.
     *
     * @param userName     the username
     * @param password     the password
     * @param banStatus    the ban status
     * @param loginHistory the login history
     */
    public NonAdminUser(String userName, String password, boolean banStatus, HashSet<String> loginHistory) {
        super(userName, password, banStatus, loginHistory);
        this.setAdminInd(false);
    }

}

