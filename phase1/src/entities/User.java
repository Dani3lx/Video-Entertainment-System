package entities;

import java.util.HashSet;
import java.util.Iterator;

/**
 * This represents user.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-21
 */

public abstract class User {
    private final String userName;
    private String password;
    private HashSet<String> loginHistory;
    private boolean banStatus;
    private boolean adminInd;

    /**
     * Constructs a user using username and password.
     *
     * @param userName the username
     * @param password the password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.banStatus = false;
        this.loginHistory = new HashSet<>();
    }

    /**
     * Constructs a user using username, password, ban status and login history.
     *
     * @param userName     the username
     * @param password     the password
     * @param banStatus    the ban status
     * @param loginHistory the login history
     */
    public User(String userName, String password, boolean banStatus, HashSet<String> loginHistory) {
        this(userName, password);
        this.banStatus = banStatus;
        this.loginHistory = loginHistory;
    }

    /**
     * Returns whether the user is an admin.
     *
     * @return whether the user is an admin
     */
    public boolean isAdminInd() {
        return adminInd;
    }

    /**
     * Sets user as admin or non admin.
     *
     * @param adminInd whether the user is an admin
     */
    public void setAdminInd(boolean adminInd) {
        this.adminInd = adminInd;
    }

    /**
     * Return the username.
     *
     * @return the username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Return the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return the login history.
     *
     * @return the login history
     */
    public HashSet<String> getLoginHistory() {
        return loginHistory;
    }

    /**
     * Return the ban status.
     *
     * @return the ban status
     */
    public boolean getBanStatus() {
        return banStatus;
    }

    /**
     * Sets the ban status.
     *
     * @param banStatus the ban status
     */
    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
    }

    /**
     * Return whether user are equal to this user.
     *
     * @param user the targeted user
     * @return whether user are equal to this user
     */
    public boolean equals(User user) {
        return (user.userName.equals(this.userName) && (user.password.equals(this.password) && (user.banStatus == this.banStatus) &&
                (user.loginHistory.equals(this.loginHistory)) && (user.isAdminInd() == this.isAdminInd())));
    }

    /**
     * Return the string representation of user.
     *
     * @return the string representation of user
     */
    @Override
    public String toString() {
        Iterator<String> it = loginHistory.iterator();
        StringBuilder s = new StringBuilder();
        while (it.hasNext()) {
            s.append(it.next()).append("/");
        }
        // https://www.geeksforgeeks.org/how-to-iterate-hashset-in-java/#:~:text=First%2C%20we%20make%20an%20iterator,Next()%20method%20in%20Java.
        return this.getUserName() + "," + this.getPassword() + "," + this.getBanStatus() + "," + this.isAdminInd() + "," + s;
    }
}
