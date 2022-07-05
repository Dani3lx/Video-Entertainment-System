import java.util.HashSet;

public class AdminUser extends User {

    public AdminUser(String userName, String password) {
        super(userName, password);
        this.setAdminInd(true);
    }

    public AdminUser(String userName, String password, boolean banStatus, HashSet<String> loginHistory) {
        super(userName, password, banStatus, loginHistory);
        this.setAdminInd(true);
    }

}
