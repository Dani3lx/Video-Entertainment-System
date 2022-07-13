import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserInterfaceHandler {
    private UserManager um;
    private AdminManager am;
    private final Scanner sc = new Scanner(System.in);
    private ArrayList<User> all_users;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public UserInterfaceHandler(UserManager um){
        this.um = um;
        am = new AdminManager(um);
        all_users = um.getAllUsers();
    }

    public void displayHistory(User user) {
        System.out.println(um.getHistory(user));
    }

    public void updateUserHistory(User user) {
        um.updateHistory(user, LocalDateTime.now().format(formatter));
    }


    public User loginUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();
//        UserManager thisUserManager = new UserManager();
        return um.validateUser(username, password);
    }

    public boolean deleteUser(User currentUser) {
        System.out.println("Here are all the users: \n");
        am.displayAllUsers(um.getAllUsers());
        System.out.println("Please enter the username of the user you wish to delete");
        String name = sc.nextLine();
        ArrayList<User> all_users = um.getAllUsers();

        for (User user : all_users) {
            if (am.validateUserName(user, name)) {
                if (am.validateUserName(currentUser, name)) {
                    System.out.println("Are you sure you want to delete this account");
                    System.out.println("Type t to continue");
                    String result = sc.nextLine();
                    if (result.equals("t")) {
                        am.deleteUser(user);
                        System.out.println("You have successfully deleted your own account");
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    am.deleteUser(user);
                    System.out.println("The user " + name + " has been successfully deleted");
                    return false;
                }
            }
        }
        System.out.println("The user " + name + " does not exist");
        return false;
    }

    public User createUser() {
        System.out.println("Please enter a username: ");
        String userName = sc.nextLine();

        System.out.println("Please enter a password: ");
        String password = sc.nextLine();


        ArrayList<User> all_users = um.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (u.getUserName().equals(userName)) {
                    return null;
                }
            }
        }
        User newUser = am.instantiateUser(userName, password, false);

        um.updateData(newUser);
        return newUser;
    }

    public void creatAdminUser() {
        System.out.println("Please enter a username: ");
        String userName = sc.nextLine();

        System.out.println("Please enter a password: ");
        String password = sc.nextLine();

        ArrayList<User> all_users = um.getAllUsers();
        if (!(Objects.isNull(all_users))) {
            for (User u : all_users) {
                if (am.validateUserName(u, userName)) {
                    System.out.println("Failed to create a new account");
                    return;
                }
            }
        }

        User newUser = am.instantiateUser(userName, password, true);
        am.updateHistory(newUser, LocalDateTime.now().format(formatter));
        um.updateData(newUser);
    }

    public void banUser(User currentUser) {
        System.out.println("Here are all the users that are not banned");
        am.displayAllUsers(all_users, false);
        System.out.println("\n\nPlease enter the name of the user that you wish to ban");
        String name = sc.nextLine();

        for (User user : all_users) {
            if (am.validateUserName(user, name)) {
                if (am.validateUserName(currentUser, name)) {
                    System.out.println("You can not ban your own account");

                } else if (user instanceof AdminUser) {
                    System.out.println("You can not ban an admin account");

                } else {
                    am.banUser(user);
                    System.out.println("You have successfully banned " + name);
                }
                return;
            }
        }
        System.out.println("User can not be found");
    }

    public void unBanUser() {
        System.out.println("Here are all the users that are banned");
        am.displayAllUsers(all_users, true);
        System.out.println("\nPlease enter the name of the user that you wish to unban");
        String name = sc.nextLine();
        for (User user : all_users) {
            if (am.validateUserName(user, name)) {
                if (am.validateBanStatus(user)) {
                    System.out.println(name + " has been unbanned");
                    am.unbanUser(user);
                }
                return;
            }
        }
        System.out.println("User can not be found");
    }

    public void changePassword(User user) {
        System.out.println("Please enter a new password");
        um.changePassword(user, sc.nextLine());
    }

}
