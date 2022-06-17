public class AdminUser extends User{

    public AdminUser(String userName, String password){
        super(userName, password);
        this.setAdminInd(true);
    }

    public AdminUser(String userName, String password, boolean banStatus){
        super(userName, password, banStatus);
        this.setAdminInd(true);
    }

}
