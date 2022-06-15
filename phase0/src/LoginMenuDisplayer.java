import java.util.Scanner;
import java.util.Objects;

// This class is a controller
public class LoginMenuDisplayer {
    public void startMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Type 1 to login, type 2 to creat a new user account");

        if (sc.hasNextInt()) {
            int input = (sc.nextInt());
            if (input == 1) {
                User currentUser = UserLogin.loginUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to Login");
                    this.startMenu();
                } else {
                    System.out.println("you are now logged in");
                    if (currentUser instanceof AdminUser) {
                        AfterLoginMenu((AdminUser) currentUser);
                    } else {
                        AfterLoginMenu((NonAdminUser) currentUser);
                    }

                }
            } else if (input == 2) {
                User currentUser = CreateUser.createUser();
                if (Objects.isNull(currentUser)) {
                    System.out.println("Failed to create a new account");
                    this.startMenu();
                } else {
                    // When you create a new account you automatically log into it i think, so it should show the same
                    // menu.
                    System.out.println("you are now logged in");
                    UserData.writeData();

                    if (currentUser instanceof AdminUser) {
                        AfterLoginMenu((AdminUser) currentUser);
                    } else {
                        AfterLoginMenu((NonAdminUser) currentUser);
                    }
                }

            } else {
                System.out.println("Please enter a valid response");
                startMenu();
            }
        } else {
            System.out.println("Please enter a valid response");
            startMenu();
        }
    }

    private void AfterLoginMenu(NonAdminUser user) {
        System.out.println("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out");
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
                    AfterLoginMenu(user);
            }
        } else {
            System.out.println("Please enter a valid input");
            AfterLoginMenu(user);
        }
    }

    private void AfterLoginMenu(AdminUser user) {
        System.out.println("Here are some options for admin user ...");
    }

}