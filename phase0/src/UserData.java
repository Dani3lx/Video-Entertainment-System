
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class UserData {
    // a list storing all User instances
    private static List<User> allUsers = new ArrayList<>();
    // people on the internet said that HashMap is generally preferred over HashTable
    // a dict (HashMap) mapping each User instance's username to its login history
    private static HashMap<String, HashSet<String>> allLoginHistory = new HashMap<>();
    private static ArrayList<String> allUsers2 = new ArrayList<>();


    // this is called in the User class constructor so every time a User is created the data.txt inside the UserData
    // class is updated correctly
    public static void updateData(User user) {
        allUsers.add(user);
        allLoginHistory.put(user.getUserName(), user.getLoginHistory());
    }


    // a getter for allUsers
    public static List<User> getAllUsers() {
        return allUsers;
    } //Akmar C: Changed to Arraylist

    public static HashMap<String, HashSet<String>> getAllLoginHistory() {
        return allLoginHistory;
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
