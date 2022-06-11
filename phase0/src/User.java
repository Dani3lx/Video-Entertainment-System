import java.time.LocalDateTime;
import java.util.*;
public abstract class User {
    private String userName;
    private String password;

    // Stores A date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30. We can
    // change this later.
    private ArrayList<LocalDateTime> loginHistory;
    private boolean banStatus;

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.banStatus = false;
        this.loginHistory = new ArrayList<>();
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

    public ArrayList<LocalDateTime> getLoginHistory() {
        return loginHistory;
    }

    public void setLoginHistory(ArrayList<LocalDateTime> loginHistory) {
        this.loginHistory = loginHistory;
    }

    public boolean isBanStatus() {
        return banStatus;
    }

    public void setBanStatus(boolean banStatus) {
        this.banStatus = banStatus;
    }
}
