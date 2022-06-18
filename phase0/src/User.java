import java.time.LocalDateTime;
import java.util.*;
public abstract class User {
    private String userName;
    private String password;

    // Stores A date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30. We can
    // change this later.
    private HashSet<String> loginHistory;
    private boolean banStatus;
    private boolean adminInd;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.banStatus = false;
        this.loginHistory = new HashSet<>();

        // this.loginHistory = new ArrayList<>();
        // UserData.updateData(this);
    }

    public User(String userName, String password, boolean banStatus){
        this(userName, password);
        this.banStatus = banStatus;

        // this.loginHistory = new ArrayList<>();
        // UserData.updateData(this);
    }

    public boolean isBanStatus() {
        return banStatus;
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

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setLoginHistory(HashSet<String> loginHistory) {
        this.loginHistory = loginHistory;
    }

    public boolean getBanStatus() {
        return banStatus;
    }

    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
    }

    public void addLoginHistory(String loginHistory) { //todo: Added this to append loginhistory - A.C.
        this.loginHistory.add(loginHistory);
    }

    public void setAdmin(){
        this.adminInd = true;
    }

    @Override
    public String toString() {
        return this.getUserName() + "," + this.getPassword() + "," + this.getBanStatus() + "," + this.getLoginHistory() + "," + this.isAdminInd();
    }
}
