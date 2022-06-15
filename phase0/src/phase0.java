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
                    if (currentUser instanceof AdminUser) {
                        displayAfterLogin((AdminUser) currentUser);
                    } else {
                        displayAfterLogin((NonAdminUser) currentUser);
                    }

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
                    if (currentUser instanceof AdminUser) {
                        displayAfterLogin((AdminUser) currentUser);
                    } else {
                        displayAfterLogin((NonAdminUser) currentUser);
                    }
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

    private static void displayAfterLogin(NonAdminUser user) {
        System.out.println("Please input one of the following number to proceed \n 1 - Change Password \n 2 - Check login history \n 3 - Log out");
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int result = sc.nextInt();
            switch (result) {
                case 1:
                    System.out.println("Change password");
                    break;
                case 2:
                    System.out.println("Checking history");
                    break;
                case 3:
                    System.out.println("Log out");
                    break;
                default:
                    System.out.println("Please enter a valid input");
                    displayAfterLogin(user);
            }
        } else {
            System.out.println("Please enter a valid input");
            displayAfterLogin(user);
        }
    }

    private static void displayAfterLogin(AdminUser user) {
        System.out.println("Here are some options for admin user ...");
    }

}
