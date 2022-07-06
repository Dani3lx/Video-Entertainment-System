import java.util.*;
import java.io.*;

public class DataManager {

    private ArrayList<User> users = new ArrayList<>();

    private UserManager um;

    public DataManager(UserManager um){
        this.um = um;
    }


    public void loadData(String filePath) {

        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            String[] record;

            while (scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");
                if (record[3].equals("true")) {
                    User adminUser = um.instantiateUser(record[0], record[1], Boolean.parseBoolean(record[2]),
                            new HashSet<>(Arrays.asList(record[4].split("/"))), true);
                    users.add(adminUser);
                } else {
                    User nonAdminUser = um.instantiateUser(record[0], record[1], Boolean.parseBoolean(record[2]),
                            new HashSet<>(Arrays.asList(record[4].split("/"))), false);
                    users.add(nonAdminUser);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public void saveData(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath, false);

            for (User user : users) {
                writer.write(user.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }


    public void setUsers(java.util.ArrayList<User> users) {
        this.users = users;
    }

}
