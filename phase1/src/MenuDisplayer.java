import java.util.Objects;
import java.util.Scanner;

public class MenuDisplayer {
    Scanner sc = new Scanner(System.in);
    UserActionHandler userActionhandler;
    DataManager dataManager;
    UserManager um;

    public MenuDisplayer(UserManager um, VideoManager vm) {
        this.um = um;
        userActionhandler = new UserActionHandler(um);
        dataManager = new DataManager(um, vm);
    }

    public void startMenu() {
        User currentUser;
        String[] info;
        switch (getUserActionChoice("Type 1 to login, type 2 to create a new user account, type 3" +
                " to exit program")) {
            case 1:
                info = getLoginInfo();
                currentUser = userActionhandler.loginUser(info[0], info[1]);
                checkNoUserFound(currentUser, "login");
                userActionhandler.updateUserHistory(currentUser);
                if (userActionhandler.validateUserPermission(currentUser)) {
                    displayAlertMessage("adminLogin");
                    adminMenu(currentUser);
                } else {
                    displayAlertMessage("nonAdminLogin");
                    nonAdminMenu(currentUser);
                }
                break;
            case 2:
                info = getLoginInfo();
                currentUser = userActionhandler.createUser(info[0], info[1]);
                checkNoUserFound(currentUser, "accountCreation");
                userActionhandler.updateUserHistory(currentUser);
                displayAlertMessage("accountCreation");
                nonAdminMenu(currentUser);
                break;
            case 3:
                dataManager.saveData("phase1/Data.csv");
                dataManager.saveVideoData("phase1/VideoData.csv");
                System.exit(0);
                break;
            default:
                displayErrorMessage("userInput");
                startMenu();
        }
    }

    private void nonAdminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n\n\n");
        switch (result) {
            case 4:
                System.out.println("UploadVideo");
            case 5:
                System.out.println("BrowseVideos");
            case 6:
                System.out.println("View Playlist");
            default:
                basicUserMenu(user, result, false);
        }
    }

    private void adminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Create AdminUser \n" +
                " 5 - Delete User \n 6 - Ban User \n 7 - UnBan User \n");
        AdminHandler adminHandler = new AdminHandler(um);
        String[] info;
        switch (result) {
            case 4:
                info = getLoginInfo();
                adminHandler.createAdminUser(info[0], info[1]);
                adminMenu(user);
                break;
            case 5:
                displayUsers();
                System.out.println("Please enter the username of the user you wish to delete");

                if (adminHandler.deleteUser(user, sc.nextLine())) {
                    startMenu();
                }
                displayAlertMessage("deleteUser");
                adminMenu(user);
                break;
            case 6:
                displayUsers(false);
                System.out.println("\nPlease enter the name of the user that you wish to ban");
                if (adminHandler.banUser(user, sc.nextLine())) {
                    displayAlertMessage("banUserFail");
                } else {
                    displayAlertMessage("banUserSuccess");
                }
                adminMenu(user);
                break;
            case 7:
                displayUsers(true);
                System.out.println("\nPlease enter the name of the user that you wish to unban");
                if (adminHandler.unBanUser(sc.nextLine())) {
                    displayAlertMessage("unbanSuccess");
                } else {
                    displayAlertMessage("unbanFail");
                }
                adminMenu(user);
                break;
            default:
                basicUserMenu(user, result, true);
        }
    }

    private void basicUserMenu(User user, int choice, boolean isAdmin) {

        switch (choice) {
            case 1:
                System.out.println("Please enter a new password");
                userActionhandler.changePassword(user, sc.nextLine());
                displayAlertMessage("passwordChange");
                callMenu(user, isAdmin);
                break;
            case 2:
                System.out.println("Checking history:");
                System.out.println(userActionhandler.getHistory(user));
                System.out.println("\n");
                callMenu(user, isAdmin);
                break;
            case 3:
                startMenu();
                break;
        }
    }

    private void callMenu(User user, boolean isAdmin) {
        if (isAdmin) {
            adminMenu(user);
        } else {
            nonAdminMenu(user);
        }
    }

    public void displayAlert(String message) {
        System.out.println(alertText(message));
    }

    public void displayAlertMessage(String alert) {
        switch (alert) {
            case "adminLogin":
                System.out.println(alertText("you are now logged in to an admin account"));
                break;
            case "nonAdminLogin":
                System.out.println(alertText("you are now logged in to an non-admin account"));
                break;
            case "accountCreation":
                System.out.println(alertText("A new account has been successfully created"));
                break;
            case "passwordChange":
                System.out.println("Password change was successful\n");
                break;
            case "banUserFail" :
                System.out.println("The ban operation was unsuccessful");
                break;
            case "banUserSuccess" :
                System.out.println("The ban operation was successful");
                break;
            case "deleteUser" :
                System.out.println("This user is now removed");
                break;
            case "unbanSuccess":
                System.out.println("The unban operation was successful");
                break;
            case "unbanFail":
                System.out.println("The unban operation was unsuccessful");
                break;
        }
    }

    private void displayUsers(boolean banStatus) {
        AdminManager am = new AdminManager(um);
        if (banStatus) {
            System.out.println("Here are all the banned users");
        } else {
            System.out.println("Here are all the unbanned users");
        }
        for (String item : am.returnUsersByBan(um.getAllUsers(), banStatus)) {
            System.out.println(item);
        }
    }

    private void displayUsers() {
        System.out.println("Here are all the users");
        AdminManager am = new AdminManager(um);
        for (String item : am.returnUsers(um.getAllUsers())) {
            System.out.println(item);
        }
    }

    private int getUserActionChoice(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.println(menuOption(text));
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return 0;
        }
    }

    private void checkNoUserFound(User currentUser, String process) {
        if (Objects.isNull(currentUser)) {
            displayErrorMessage(process);
            startMenu();
        }
    }

    private void displayErrorMessage(String error) {
        switch (error) {
            case "login":
                System.out.println("Login was unsuccessful");
                break;
            case "accountCreation":
                System.out.println("Account creation was unsuccessful");
                break;
            case "userInput":
                System.out.println("Please enter a valid input");
                break;
            case "accountDeletion":
                System.out.println("You have deleted your own account");
        }
    }

    private String[] getLoginInfo() {
        System.out.println("Please enter a username: ");
        String username = sc.nextLine();
        System.out.println("Please enter a password: ");
        String password = sc.nextLine();
        return new String[]{username, password};
    }


    private String menuOption(String input) {
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("*".repeat(num));
        return decorator + "\n" + input + "\n" + decorator;
    }

    private String alertText(String input) {
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("-".repeat(num));
        return decorator + "\n" + input + "\n" + decorator;
    }
}