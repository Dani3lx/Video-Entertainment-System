import java.util.ArrayList;
import java.util.List;

public class UserData {
    // a list storing all User instances
    private static List<User> allUsers = new ArrayList<>();
    // people on the internet said that HashMap is generally preferred over HashTable
    // a dict (HashMap) mapping each User instance's username to its login history


    // this is called in the User class constructor so every time a User is created the data.txt inside the UserData
    // class is updated correctly
    public static void updateData(User user) {
        allUsers.add(user);
    }

    // a getter for allUsers
    public static List<User> getAllUsers() {
        return allUsers;
    }



    public static void setAllUsers(List<User> userdata) {
        allUsers = userdata;
    }


    public static void displayAllUsers(List<User> users, boolean displayBan) {
        for (User user : users){
            if ((displayBan == user.getBanStatus()) && !(user instanceof AdminUser)){
                System.out.println("Username: " + user.getUserName());
            }
        }
    }

    public static void displayAllUsers(List<User> users) {
        for (User user : users) {
            System.out.println("Username: " + user.getUserName());
        }
    }

}
