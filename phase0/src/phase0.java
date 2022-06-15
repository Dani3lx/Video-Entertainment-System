import java.util.Scanner;
import java.util.Objects;

// This class is a controller
public class phase0 {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 1 to login, type 2 to creat a new user account");

        if (sc.hasNextInt()) {
            int input = (sc.nextInt());
            if (input == 1) {
                User currentUser = UserLogin.loginUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to Login");
                    phase0.run();
                } else {
                    System.out.println("you are now logged in");
                    displayAfterLogin();
                }
            } else if (input == 2) {
                User currentUser = CreateUser.createUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to create a new account");
                    phase0.run();
                } else {
                    // When you create a new account you automatically log into it i think, so it should show the same
                    // menu.
                    System.out.println("you are now logged in");
                    UserData.writeData();
                    displayAfterLogin();
                }

            } else {
                System.out.println("Please enter a valid response");
                phase0.run();
            }
        } else {
            System.out.println("Please enter a valid response");
            phase0.run();
        }
    }

    private static void displayAfterLogin() {
        System.out.println("Here are some options for when you are logged in ...");
    }

}
