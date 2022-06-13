import java.time.LocalDateTime;
import java.util.*;

public class UserData {
    // a list storing all User instances
    private static List<User> allUsers;
    // people on the internet said that HashMap is generally preferred over HashTable
    // a dict (HashMap) mapping each User instance's username to its login history
    private static HashMap<String, ArrayList<LocalDateTime>> allLoginHistory;

    // constructor for UserData. it is possible we don't need this constructor and just instantiate the
    // variables directly above, but this is open up for discussion
    public UserData(){
        allUsers = new ArrayList<>();
        allLoginHistory = new HashMap<>();
    }

    // this is called in the User class constructor so every time a User is created the data inside the UserData
    // class is updated correctly
    public static void updateData(User user){
        allUsers.add(user);
        allLoginHistory.put(user.getUserName(), user.getLoginHistory());
    }

    // a getter for allUsers
    public static List<User> getAllUsers(){
        return allUsers;
    } //Akmar C: Changed to Arraylist

}
