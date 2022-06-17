import java.util.*;
import java.io.*;

public class DataManager2 {

    private ArrayList<User> users = new ArrayList<>();

    public void loadData(String filePath) {

        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            String[] record;
            User user;

            while (scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");
                if (record[4].equals("true")){
                    user = new AdminUser(record[0], record[1], Boolean.parseBoolean(record[2]));
                }
                else {
                    user = new NonAdminUser(record[0], record[1], Boolean.parseBoolean(record[2]));
                }
                users.add(user);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public void saveData(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);

            for (User user : users) {
                writer.write(user.toString() + "\n");
            }

            writer.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public ArrayList<User> getUsers(){
        return users;
    }

}
