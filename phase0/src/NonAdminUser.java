public class NonAdminUser extends User{

    public NonAdminUser(String userName, String password){
        super(userName, password);
        this.setAdminInd(false);
    }

}

