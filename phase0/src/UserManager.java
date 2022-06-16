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

    // todo Will need to change the csv file accordingly as well
    public void changePassword(User user, String password){
        user.setPassword(password);
        System.out.println("Password change was successful");
    }

    public void checkHistory(User user) {
        System.out.println("Checking history:");
        System.out.println(user.getLoginHistory());
    }



}