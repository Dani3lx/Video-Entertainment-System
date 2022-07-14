import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class MenuDisplayer {
    Scanner sc = new Scanner(System.in);
    UserActionHandler userActionHandler;
    DataManager dataManager;
    UserManager um;

    VideoManager vm;
    Presenter presenter;

    VideoManagementMenuDisplayer vmmDisplayer;

    /**
     * Constructor for MenuDisplayer to initialize the object
     *
     * @param um The user manager for managing users
     * @param vm The video manager for managing videos
     */
    public MenuDisplayer(UserManager um, VideoManager vm) {
        this.um = um;
        this.vm = vm;
        userActionHandler = new UserActionHandler(um);
        dataManager = new DataManager(um, vm);
        presenter = new Presenter(um, vm);
        vmmDisplayer = new VideoManagementMenuDisplayer(presenter, this);
    }

    /**
     * This menu navigates the user to login or create account
     */
    public void startMenu() {
        User currentUser;
        String[] info;
        switch (getUserActionChoice("Type 1 to login, type 2 to create a new user account, type 3" +
                " to exit program")) {
            case 1:
                info = getLoginInfo();
                currentUser = userActionHandler.loginUser(info[0], info[1]);
                checkNoUserFound(currentUser, "Login was unsuccessful");
                userActionHandler.updateUserHistory(currentUser);
                if (userActionHandler.validateUserPermission(currentUser)) {
                    presenter.displayAlert("you are now logged in to an admin account");
                    adminMenu(currentUser);
                } else {
                    presenter.displayAlert("you are now logged in to an non-admin account");
                    nonAdminMenu(currentUser);
                }
                break;
            case 2:
                info = getLoginInfo();
                currentUser = userActionHandler.createUser(info[0], info[1]);
                checkNoUserFound(currentUser, "Account creation was not successful");
                userActionHandler.updateUserHistory(currentUser);
                presenter.displayAlert("A new account has been successfully created");
                nonAdminMenu(currentUser);
                break;
            case 3:
                dataManager.saveData("phase1/Data.csv");
                dataManager.saveVideoData("phase1/VideoData.csv");
                System.exit(0);
                break;
            default:
                presenter.displayError("Please enter a valid input");
                startMenu();
        }
    }


    /**
     * This menu navigates the user to perform actions done by non admin users
     *
     * @param user The current user
     */
    private void nonAdminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Browse Videos \n 5 - " +
                "Upload videos \n 6 - View Playlists");
        switch (result) {
            case 4:
                vmmDisplayer.videoBrowseMenu(user);
                break;
            case 5:
                System.out.println("Uploadvideo");
            case 6:
                System.out.println("View Playlist");
            default:
                basicUserMenu(user, result, false);
        }
    }

    /**
     * This menu navigates the user to perform actions done by admin users
     *
     * @param user The current user
     */
    private void adminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Create AdminUser \n" +
                " 5 - Delete User \n 6 - Ban User \n 7 - UnBan User \n 8 - Browse Videos");
        AdminHandler adminHandler = new AdminHandler(um, vm);
        String[] info;
        switch (result) {
            case 4:
                info = getLoginInfo();
                if (adminHandler.createAdminUser(info[0], info[1])) {
                    presenter.displayAlert("Account has been successfully created");
                } else {
                    presenter.displayAlert("Account creation was unsuccessful");
                }
                adminMenu(user);
                break;
            case 5:
                presenter.displayUsers();
                presenter.displayRequest("Please enter the username of the user you wish to delete");

                if (adminHandler.deleteUser(user, sc.nextLine())) {
                    startMenu();
                }
                presenter.displayAlert("Deletion was successful");
                adminMenu(user);
                break;
            case 6:
                presenter.displayUsers(false);
                presenter.displayRequest("Please enter the name of the user that you wish to ban");
                if (adminHandler.banUser(user, sc.nextLine())) {
                    presenter.displayError("The ban operation was unsuccessful");
                } else {
                    presenter.displayAlert("The ban operation was successful");
                }
                adminMenu(user);
                break;
            case 7:
                presenter.displayUsers(true);
                presenter.displayRequest("Please enter the name of the user that you wish to unban");
                if (adminHandler.unBanUser(sc.nextLine())) {
                    presenter.displayAlert("The account has been unbanned");
                } else {
                    presenter.displayError("Unban was unsuccessful");
                }
                adminMenu(user);
                break;
            case 8:
                vmmDisplayer.videoBrowseMenu(user);
                break;
            default:
                basicUserMenu(user, result, true);
        }
    }

    /**
     * This is a basic menu that navigates the user to perform actions done by all users
     *
     * @param user The current user
     * @param choice The action that user wish to perform
     * @param isAdmin The type of user
     */
    void basicUserMenu(User user, int choice, boolean isAdmin) {

        switch (choice) {
            case 1:
                presenter.displayRequest("Please enter a new password");
                userActionHandler.changePassword(user, sc.nextLine());
                presenter.displayAlert("Password change was successful");
                callMenu(user, isAdmin);
                break;
            case 2:
                presenter.displayAlert("Checking history:");
                presenter.displayLoginHistory(user, userActionHandler);
                callMenu(user, isAdmin);
                break;
            case 3:
                startMenu();
                break;
        }
    }


    /**
     * This method decides which user menu to call
     *
     * @param user The current user
     * @param isAdmin The user's type
     */
    void callMenu(User user, boolean isAdmin) {
        if (isAdmin) {
            adminMenu(user);
        } else {
            nonAdminMenu(user);
        }
    }

    /**
     * This method takes in the user and return user's choice of action
     *
     * @param text
     * @return
     */
    int getUserActionChoice(String text) {
        Scanner sc = new Scanner(System.in);
        presenter.displayMenuOption(text);
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return 0;
        }
    }

    /**
     * This method checks whether a user is found
     *
     * @param currentUser
     * @param message
     */
    private void checkNoUserFound(User currentUser, String message) {
        if (Objects.isNull(currentUser)) {
            presenter.displayError(message);
            startMenu();
        }
    }

    /**
     * This method takes in and returns the name and password
     *
     * @return
     */
    String[] getLoginInfo() {
        presenter.displayRequest("Please enter a username: ");
        String username = sc.nextLine();
        presenter.displayRequest("Please enter a password: ");
        String password = sc.nextLine();
        return new String[]{username, password};
    }

}
