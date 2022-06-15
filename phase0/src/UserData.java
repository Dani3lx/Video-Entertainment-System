import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
public class UserData {
    // a list storing all User instances
    private static List<User> allUsers = new ArrayList<>();
    // people on the internet said that HashMap is generally preferred over HashTable
    // a dict (HashMap) mapping each User instance's username to its login history
    private static HashMap<String, ArrayList<LocalDateTime>> allLoginHistory = new HashMap<>();
    private static ArrayList<String> allUsers2 = new ArrayList<>();


    // this is called in the User class constructor so every time a User is created the data.txt inside the UserData
    // class is updated correctly
    public static void updateData(User user){
        allUsers.add(user);
        allLoginHistory.put(user.getUserName(), user.getLoginHistory());
    }

    // a getter for allUsers
    public static List<User> getAllUsers(){
        return allUsers;
    } //Akmar C: Changed to Arraylist

    public static void readData() throws IOException {
       BufferedReader data = new BufferedReader(new FileReader("userData.txt"));
       String current;
       while ((current = data.readLine())!=null){
           allUsers2.add(current);
       }
    }
    public static void writeData()  {
        FileWriter data = null; //todo: make sure it still has all the data it had before program ran
        try {
            data = new FileWriter("userData.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for(Object obj:allUsers){
            try {
                data.write(obj + System.lineSeparator()); //Source: https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //todo: for login history
    }

}
