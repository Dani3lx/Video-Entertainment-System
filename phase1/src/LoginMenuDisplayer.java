import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LoginMenuDisplayer {
//    UserManager um = new UserManager();
    private UserManager um;
    UserInterfaceHandler UIhandler;
    Presenter p = new Presenter();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public LoginMenuDisplayer(UserManager um){
        this.um = um;
        UIhandler = new UserInterfaceHandler(um);
    }

    /**
     * Display the start menu of the login system.
     *
     * @throws IOException if error reading from file
     */
    public void startMenu() throws IOException {
        // test
        Scanner sc = new Scanner(System.in);
        System.out.println(p.startMenuOption("Type 1 to login, type 2 to create a new user account, type 3" +
                " to exit program"));

        User currentUser;
        if (sc.hasNextInt()) {
            int input = (sc.nextInt());

            switch (input) {
                case 1:
                    currentUser = UIhandler.loginUser();
                    if (Objects.isNull(currentUser)) {
                        System.out.println("Failed to Login");
                        this.startMenu();
                    } else {

                        um.updateHistory(currentUser, LocalDateTime.now().format(formatter));
                        if (currentUser.isAdminInd()) {
                            System.out.println(p.alertText("you are now logged in to an admin account"));
                            AfterLoginMenu(currentUser);
                        } else {
                            System.out.println(p.alertText("you are now logged in to a non-admin account"));
                            AfterLoginMenu(currentUser);
                        }
                    }
                case 2:
                    currentUser = UIhandler.createUser();
                    if (Objects.isNull(currentUser)) {
                        System.out.println("Failed to create a new account");
                        this.startMenu();
                    } else {
                        um.updateHistory(currentUser, LocalDateTime.now().format(formatter));
                        System.out.println("New account has been created");
                        AfterLoginMenu(currentUser);
                    }
                case 3:
                    DataManager sm = new DataManager(um);
                    sm.setUsers((ArrayList<User>) um.getAllUsers());
                    sm.saveData("phase1/Data.csv");
                    System.exit(0);
                default:
                    System.out.println("Please enter a valid response");
                    startMenu();
            }

        } else {
            System.out.println("Please enter a valid response");
            startMenu();
        }
    }

    /**
     * Display the menu after User logs in.
     */

    private void AfterLoginMenu(User user) throws IOException {

        if (!(user.isAdminInd())) {
            System.out.println("Please input one of the following number to proceed " +
                    "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n\n\n");
        } else {
            System.out.println("Please input one of the following number to proceed " +
                    "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Create AdminUser \n" +
                    " 5 - Delete User \n 6 - Ban User \n 7 - UnBan User \n");
        }

        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            int result = sc.nextInt();
            sc.nextLine();
            switch (result) {
                case 1:
                    System.out.println("Please enter a new password");
                    String newPassword = sc.nextLine();
                    um.changePassword(user, newPassword);
                    System.out.println("Password change was successful\n");
                    break;
                case 2:
                    System.out.println("Checking history:");
                    um.checkHistory(user);
                    System.out.println("\n");
                    break;
                case 3:
                    startMenu();
                    break;
                case 4:
                    if (user.isAdminInd()) {
                        UIhandler.creatAdminUser();
                    }
                    break;
                case 5:
                    if (user.isAdminInd()) {
                        if (UIhandler.deleteUser(user)) {
                            startMenu();
                        } else {
                            AfterLoginMenu(user);
                        }
                    }
                    break;
                case 6:
                    if (user.isAdminInd()) {
                        UIhandler.banUser(user);
                    }
                    break;
                case 7:
                    if (user.isAdminInd()) {
                        UIhandler.unBanUser();
                    }
                    break;
                default:
                    System.out.println("Please enter a valid input");
            }
        } else {
            System.out.println("Please enter a valid input");
        }
        AfterLoginMenu(user);
    }
}

