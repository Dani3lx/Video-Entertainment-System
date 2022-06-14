import java.util.List;

public class CreateUser {

//    @param user: username of new User to be created
//    @param pass: password of new User to be created
//    @param admin: true/false whether the user to be created is admin or not

//    Check if UserData already contains a User with the same username. If there is, return false
//    If not, create a new AdminUser instance or NonAdminUser and update UserData.
//
//    @return true if new AdminUser or NonAdminUser was created successfully
    public static boolean createUser(String user, String pass, boolean admin){

        List<User> all_users = UserData.getAllUsers();
        for (User u: all_users) {
            if (u.getUserName().equals(user)) {
                return false;

            }
        }
        if (admin){
            User newUser = new AdminUser(user, pass);
            UserData.updateData(newUser);

            }
        else{
            User newUser = new NonAdminUser(user, pass);
            UserData.updateData(newUser);

            }
        return true;


    }
}
