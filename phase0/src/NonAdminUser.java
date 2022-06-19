import java.util.HashSet;

public class NonAdminUser extends User {

    public NonAdminUser(String userName, String password) {
        super(userName, password);
        this.setAdminInd(false);
    }

    public NonAdminUser(String userName, String password, boolean banStatus, HashSet<String> loginHistory) {
        super(userName, password, banStatus, loginHistory);
        this.setAdminInd(false);
    }

}

