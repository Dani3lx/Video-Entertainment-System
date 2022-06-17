import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CreateUser {

//    @param user: username of new User to be created
//    @param pass: password of new User to be created
//    @param admin: true/false whether the user to be created is admin or not

//    Check if UserData already contains a User with the same username. If there is, return false
//    If not, create a new AdminUser instance or NonAdminUser and update UserData.
//
//    @return true if new AdminUser or NonAdminUser was created successfully
    public User createUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a username: ");
        String userName = sc.nextLine();

        System.out.println("Please enter a password: ");
        String password = sc.nextLine();


        List<User> all_users = UserData.getAllUsers();
        if (!(Objects.isNull(all_users))){
            for (User u: all_users) {
                if (u.getUserName().equals(userName)) {
                    return null;
                }
            }
        }
        User newUser = new NonAdminUser(userName, password);

        // update to csv instead
        UserData.updateData(newUser);
        return newUser;
    }

    // Returns void because im assuming that we dont just log into the new account automatically when we create one.
    public void creatAdminUser(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a username: ");
        String userName = sc.nextLine();

        System.out.println("Please enter a password: ");
        String password = sc.nextLine();

        List<User> all_users = UserData.getAllUsers();
        if (!(Objects.isNull(all_users))){
            for (User u: all_users) {
                if (u.getUserName().equals(userName)) {
                    System.out.println("Failed to create a new account");
                    return;
                }
            }
        }
        User newUser = new AdminUser(userName, password);

        // update to csv instead
        UserData.updateData(newUser);
    }
}
