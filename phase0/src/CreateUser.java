
public class CreateUser {
    //cna take away constructor, if not necessary
//    private  String username;
//    private  String password;
//    private boolean adminorNon;
//
//    public CreateUser(String user, String pass, boolean AdminNonAdmin)
//    {
//        username = user;
//        password=pass;
//        adminorNon = AdminNonAdmin;
//    }
//
//    public String getUsername(){
//        return username;
//    }
//
//    public String getPassword(){
//        return password;
//    }
//
//    public boolean getadminorNon(){
//        return adminorNon;
//    }

    public boolean create(String user, String pass, boolean adminOrNot){
        //Calls appropriate method in UserManager to create account
        //Returns whether creation was successful or not
        boolean success = false;
        if (adminOrNot){
            success = UserManager.createAdminUser(user, pass);          //need fugure out if these createUser methods are isntance or static
        }
        else{
            success = UserManager.createNonAdminUser(user, pass);
        }
        return success;
    }
}
