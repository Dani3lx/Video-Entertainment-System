public class AdminUser extends User{

    public AdminUser(String userName, String password){
        super(userName, password);
        this.setAdminInd(true);
    }

}
