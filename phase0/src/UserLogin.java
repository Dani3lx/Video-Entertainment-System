import java.util.Scanner;

public class UserLogin {
    public static User loginUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your username: ");
        String username = sc.nextLine();
        System.out.println("Please enter your password: ");
        String password = sc.nextLine();
        UserManager thisUserManager = new UserManager();
        return thisUserManager.validateUser(username, password);
    }
}
