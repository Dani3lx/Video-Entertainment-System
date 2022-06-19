import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CreateUser {
    private final Scanner sc = new Scanner(System.in);
    UserManager um = new UserManager();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public User createUser() {
        System.out.println("Please enter a username: ");
        String userName = sc.nextLine();

        System.out.println("Please enter a password: ");
        String password = sc.nextLine();


        List<User> all_users = UserData.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (u.getUserName().equals(userName)) {
                    return null;
                }
            }
        }
        User newUser = um.instantiateUser(userName, password, false);

        UserData.updateData(newUser);
        return newUser;
    }

    public void creatAdminUser() {
        System.out.println("Please enter a username: ");
        String userName = sc.nextLine();

        System.out.println("Please enter a password: ");
        String password = sc.nextLine();

        List<User> all_users = UserData.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (u.getUserName().equals(userName)) {
                    System.out.println("Failed to create a new account");
                    return;
                }
            }
        }

        User newUser = um.instantiateUser(userName, password, true);
        newUser.getLoginHistory().add(LocalDateTime.now().format(formatter));
        UserData.updateData(newUser);
    }
}
