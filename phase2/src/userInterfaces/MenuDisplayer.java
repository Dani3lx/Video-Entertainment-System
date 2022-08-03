package userInterfaces;

import controllers.AdminHandler;
import controllers.NonAdminHandler;
import controllers.UserActionHandler;
import entities.User;
import gateways.DataManager;
import presenters.EnglishPresenter;
import presenters.LanguagePresenter;
import presenters.MenuPresenter;
import usecase.PlaylistManager;
import controllers.PlaylistMenuActions;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.*;

/**
 * Responsible for displaying the different menus and interacting with users.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class MenuDisplayer {
    private final Scanner sc = new Scanner(System.in);
    private final UserActionHandler userActionHandler;
    private final DataManager dataManager;
    private final UserManager um;
    private final VideoManager vm;
    private final MenuPresenter menuPresenter;
    private final VideoManagementMenuDisplayer vmmDisplayer;
    private final PlaylistMenu pmd;

    /**
     * Constructs a menu displayer with a record of all the users and videos.
     *
     * @param um the user manager for managing users
     * @param vm the video manager for managing videos
     * @param pm the playlist manager for managing playlists
     */
    public MenuDisplayer(UserManager um, VideoManager vm, PlaylistManager pm) {
        this.um = um;
        this.vm = vm;
        PlaylistMenuActions pma = new PlaylistMenuActions(pm, vm);
        this.userActionHandler = new UserActionHandler(um);
        this.dataManager = new DataManager(um, vm, pm);
        this.menuPresenter = new MenuPresenter(um, vm);
        this.vmmDisplayer = new VideoManagementMenuDisplayer(menuPresenter, this, vm, userActionHandler, pm);
        this.pmd = new PlaylistMenu(menuPresenter, vm, pma, pm, um, userActionHandler, this);
    }

    /**
     * This menu navigates the user to login or create account
     */
    public void startMenu() {
        User currentUser;
        String[] info;



        LanguagePresenter lp = new EnglishPresenter();
        // Gets user's choice to log in, create a new account or exit program
        switch (getUserActionChoice(lp.getMenuText("startMenu"))) {




            case 1:
                // Takes in a username and password and tries to log in
                info = getLoginInfo();
                currentUser = userActionHandler.loginUser(info[0], info[1]);
                checkNoUserFound(currentUser, "Login was unsuccessful");
                userActionHandler.updateUserHistory(currentUser);
                if (userActionHandler.isAdmin(currentUser)) {
                    menuPresenter.displayAlert("you are now logged in to an admin account");
                    adminMenu(currentUser);
                } else {
                    menuPresenter.displayAlert("you are now logged in to a non-admin account");
                    nonAdminMenu(currentUser);
                }
                break;
            case 2:
                // Takes in username and password and tries to create a new account
                info = getLoginInfo();
                currentUser = userActionHandler.createUser(info[0], info[1]);
                checkNoUserFound(currentUser, "Account creation was not successful");
                userActionHandler.updateUserHistory(currentUser);
                menuPresenter.displayAlert("A new account has been successfully created");
                nonAdminMenu(currentUser);
                break;
            case 3:
                // Saves all the data and exits the program
                dataManager.saveData("phase2/datasets/Data.csv");
                dataManager.saveVideoData("phase2/datasets/VideoData.csv");
                dataManager.savePlayListData("phase2/datasets/PlaylistData.csv");
                System.exit(0);
                break;
            default:
                menuPresenter.displayError("Invalid choice, please try again!");
                startMenu();
        }
    }


    /*
     * This menu navigates the user to perform actions done by non admin users
     *
     * @param user the current user
     */
    private void nonAdminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Browse Videos \n 5 - View Playlists \n 6 - " +
                "Upload/delete/edit videos");

        NonAdminHandler nonAdminHandler = new NonAdminHandler(um, vm);

        if (result == 6) {
            vmmDisplayer.videoActionMenu(user, nonAdminHandler);
        } else {
            basicUserMenu(user, result);
        }
    }

    /*
     * This menu navigates the user to perform actions done by admin users
     *
     * @param user the current user
     */
    private void adminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Browse Videos \n 5 - View Playlists \n 6 - Create AdminUser \n" +
                " 7 - Delete User \n 8 - Ban User \n 9 - UnBan User");
        AdminHandler adminHandler = new AdminHandler(um, vm);
        String[] info;
        String target;
        switch (result) {
            case 6:
                info = getLoginInfo();
                if (adminHandler.createAdminUser(info[0], info[1])) {
                    menuPresenter.displayAlert("Account has been successfully created");
                } else {
                    menuPresenter.displayAlert("Account creation was unsuccessful");
                }
                adminMenu(user);
                break;
            case 7:
                menuPresenter.displayUsers();
                menuPresenter.displayRequest("Please enter the username of the user you wish to delete");

                // Check if current user is deleted
                if (adminHandler.deleteUser(user, sc.nextLine())) {
                    startMenu();
                }
                menuPresenter.displayAlert("Deletion was successful");
                adminMenu(user);
                break;
            case 8:
                // Displays unbanned users
                menuPresenter.displayUsers(false);
                menuPresenter.displayRequest("Please enter the name of the user that you wish to ban");
                target = sc.nextLine();
                if (adminHandler.banUser(user, target)) {
                    menuPresenter.displayAlert("The user " + target + " has been " +
                            "successfully banned");
                } else {
                    menuPresenter.displayError("The ban operation was unsuccessful");
                }
                adminMenu(user);
                break;
            case 9:
                // Displays banned users
                menuPresenter.displayUsers(true);
                menuPresenter.displayRequest("Please enter the name of the user that you wish to unban");
                target = sc.nextLine();
                if (adminHandler.unBanUser(target)) {
                    menuPresenter.displayAlert("The user " + target + "has been " +
                            "unbanned");
                } else {
                    menuPresenter.displayError("Unban was unsuccessful");
                }
                adminMenu(user);
                break;
            default:
                basicUserMenu(user, result);
        }
    }

    /*
     * This is a basic menu that navigates the user to perform actions done by all users
     *
     * @param user    the current user
     * @param choice  the action that user wish to perform
     * @param isAdmin the type of user
     */
    private void basicUserMenu(User user, int choice) {
        switch (choice) {
            case 1:
                menuPresenter.displayRequest("Please enter a new password");
                userActionHandler.changePassword(user, sc.nextLine());
                menuPresenter.displayAlert("Password change was successful");
                callMenu(user);
                break;
            case 2:
                menuPresenter.displayAlert("Checking history:");
                menuPresenter.displayLoginHistory(user);
                callMenu(user);
                break;
            case 3:
                startMenu();
                break;
            case 4:
                vmmDisplayer.videoBrowseMenu(user);
                break;
            case 5:
                pmd.playlistBrowseMenu(user);
                break;
        }
    }

    /**
     * This method decides which user menu to call
     *
     * @param user the current user
     */
    protected void callMenu(User user) {
        if (userActionHandler.isAdmin(user)) {
            adminMenu(user);
        } else {
            nonAdminMenu(user);
        }
    }

    /**
     * This method takes in the user and return user's choice of action
     *
     * @param text the text to be displayed
     * @return the choice that the user made
     */
    protected int getUserActionChoice(String text) {
        Scanner sc = new Scanner(System.in);
        menuPresenter.displayMenuOption(text);
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return 0;
        }
    }

    /*
     * This method checks whether a user is found
     *
     * @param currentUser the current user
     * @param message     the message to be displayed
     */
    private void checkNoUserFound(User currentUser, String message) {
        if (Objects.isNull(currentUser)) {
            menuPresenter.displayError(message);
            startMenu();
        }
    }

    /*
     * This method takes in and returns the name and password
     *
     * @return the username and password
     */
    private String[] getLoginInfo() {
        menuPresenter.displayRequest("Please enter a username: ");
        String username = sc.nextLine();
        menuPresenter.displayRequest("Please enter a password: ");
        String password = sc.nextLine();
        return new String[]{username, password};
    }
}
