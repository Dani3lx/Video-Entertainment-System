import java.time.LocalDateTime;
import java.util.*;

public class UserManager {
    private List<User> users = UserData.getAllUsers(); // todo: maybe change this cause I'm not sure if it works correct

    public void deleteUser(User user){
        users.remove(user);
    }

    public void banUser(User user){
        user.setBanStatus(true);
    }

    public void unbanUser(User user){
        user.setBanStatus(false);
    }

    /*
    @param username: username of User to be validated
    @param password: password of User to be validated

    Use UserData to check if the username exists, and the passwords match.
    If they do, check if the User is banned.

    @return true if the User information is valid and the User.banStatus is false
     */
    public User validateUser(String username, String password){
        if (Objects.isNull(users)){ return null; }
        else {
            for (User user : users) {
                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    if (!user.getBanStatus()) {
                        user.addLoginHistory(LocalDateTime.now()); // todo: Added this to include log in history - A.C.
                        return user;
                    }
                }
            }
        }
        return null;
    }

    public void changePassword(User user, String password){
        user.setPassword(password);
    }

    /*
    @param username: username of new User to be created
    @param passowrd: password of new User to be created

    Check if UserData already contains a User with the same username.
    If not, create a new NonAdminUser instance and update UserData.

    @return true if new NonAdminUser was created successfully, false otherwise
     */
    public boolean createNonAdminUser(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username)) { return false; }
        }
        NonAdminUser newUser = new NonAdminUser(username, password);
        // UserData.updateData(newUser); removed since constructor already adds it
        return true;
    }

    /*
    @param username: username of new User to be created
    @param password: password of new User to be created

    Check if UserData already contains a User with the same username.
    If not, create a new AdminUser instance and update UserData.

    @return true if new AdminUser was created successfully, false otherwise
     */
    public boolean createAdminUser(String username, String password) {
        for (User user : users) {
            if (user.getUserName().equals(username)) { return false; }
        }
        AdminUser newUser = new AdminUser(username, password);
        // UserData.updateData(newUser); removed since constructor already adds it
        return true;
    }

}