import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class UserManager {
    private final List<User> users = UserData.getAllUsers();

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
    }

    public void checkHistory(User user) {
        List<String> history = new ArrayList<>(user.getLoginHistory());
        Collections.sort(history);
        System.out.println(history);
    }



}