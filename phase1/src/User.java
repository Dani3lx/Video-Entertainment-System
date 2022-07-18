import java.util.HashSet;
import java.util.Iterator;

public abstract class User {
    private final String userName;
    private String password;
    private HashSet<String> loginHistory;
    private boolean banStatus;
    private boolean adminInd;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.banStatus = false;
        this.loginHistory = new HashSet<>();

    }

    public User(String userName, String password, boolean banStatus, HashSet<String> loginHistory) {
        this(userName, password);
        this.banStatus = banStatus;
        this.loginHistory = loginHistory;

    }


    public boolean isAdminInd() {
        return adminInd;
    }

    public void setAdminInd(boolean adminInd) {
        this.adminInd = adminInd;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashSet<String> getLoginHistory() {
        return loginHistory;
    }


    public boolean getBanStatus() {
        return banStatus;
    }

    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
    }

    public boolean equals(User user){
    return (user.userName.equals(this.userName) && (user.password.equals(this.password) && (user.banStatus==this.banStatus) &&
            (user.loginHistory.equals(this.loginHistory)) && (user.isAdminInd() == this.isAdminInd())));
    }
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
