package entities;

import java.util.HashSet;


/**
 * This represents admin user.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-21
 */
public class AdminUser extends User {

    /**
     * Constructs an admin user using username and password.
     *
     * @param userName the username
     * @param password the password
     */
    public AdminUser(String userName, String password) {
        super(userName, password);
        this.setAdminInd(true);
    }

    /**
     * Constructs an admin user using username, password, ban status and login history.
     *
     * @param userName     the username
     * @param password     the password
     * @param banStatus    the ban status
     * @param loginHistory the login history
     */
    public AdminUser(String userName, String password, boolean banStatus, HashSet<String> loginHistory) {
        super(userName, password, banStatus, loginHistory);
        this.setAdminInd(true);
    }

}
