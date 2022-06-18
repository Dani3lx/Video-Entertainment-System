import java.util.List;
import java.util.Scanner;

public class BanUser {
    private UserManager um = new UserManager();
    private Scanner sc = new Scanner(System.in);
    private List<User> all_users = UserData.getAllUsers();

    public void banUser(User currentUser){
        System.out.println("Here are all the users that are not banned");
        UserData.displayAllUsers(all_users, false);
        System.out.println("\n\nPlease enter the name of the user that you wish to ban");
        String name = sc.nextLine();

        for (User user : all_users) {
            if (user.getUserName().equals(name)){
                if (currentUser.getUserName().equals(name)){
                    System.out.println("You can not ban your own account");

                } else if (user instanceof AdminUser){
                    System.out.println("You can not ban an admin account");

                } else {
                    um.banUser(user);
                    System.out.println("You have successfully banned " + name);
                }
                return;
            }
        }
        System.out.println("User can not be found");
    }

    public void unBanUser(){
        System.out.println("Here are all the users that are banned");
        UserData.displayAllUsers(all_users, true);
        System.out.println("\nPlease enter the name of the user that you wish to unban");
        String name = sc.nextLine();
        for (User user : all_users) {
            if (user.getUserName().equals(name)){
                if (user.getBanStatus()){
                    System.out.println(name + " has been unbanned");
                    um.unbanUser(user);
                }
                return;
            }
        }
        System.out.println("User can not be found");
    }

}
