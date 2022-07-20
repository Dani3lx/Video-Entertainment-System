package userInterfaces;

import controllers.AdminHandler;
import controllers.NonAdminHandler;
import controllers.UserActionHandler;
import entities.User;
import gateways.DataManager;
import presenters.MenuPresenter;
import usecase.PlaylistManager;
import usecase.PlaylistMenuActions;
import usecase.UserManager;
import usecase.VideoManager;

import java.awt.*;
import java.util.*;

/**
 * Responsible for displaying the different menus and interacting with users.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class MenuDisplayer {
    Scanner sc = new Scanner(System.in);
    UserActionHandler userActionHandler;
    DataManager dataManager;
    UserManager um;
    VideoManager vm;
    MenuPresenter menuPresenter;
    VideoManagementMenuDisplayer vmmDisplayer;
    PlaylistMenu pmd;
    PlaylistMenuActions pma;

    /**
     * Constructs a menu displayer with a record of all the users and videos.
     *
     * @param um the user manager for managing users
     * @param vm the video manager for managing videos
     */
    public MenuDisplayer(UserManager um, VideoManager vm, PlaylistManager pm) {
        this.um = um;
        this.vm = vm;
        pma = new PlaylistMenuActions();
        userActionHandler = new UserActionHandler(um);
        dataManager = new DataManager(um, vm, pm);
        menuPresenter = new MenuPresenter(um, vm);
        vmmDisplayer = new VideoManagementMenuDisplayer(menuPresenter, this, vm, userActionHandler, pm);
        pmd = new PlaylistMenu(menuPresenter,this,vm,pma,pm,userActionHandler);
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
                if (userActionHandler.isAdmin(currentUser)) {
                    menuPresenter.displayAlert("you are now logged in to an admin account");
                    adminMenu(currentUser);
                } else {
                    menuPresenter.displayAlert("you are now logged in to an non-admin account");
                    nonAdminMenu(currentUser);
                }
                break;
            case 2:
                info = getLoginInfo();
                currentUser = userActionHandler.createUser(info[0], info[1]);
                checkNoUserFound(currentUser, "Account creation was not successful");
                userActionHandler.updateUserHistory(currentUser);
                menuPresenter.displayAlert("A new account has been successfully created");
                nonAdminMenu(currentUser);
                break;
            case 3:
                dataManager.saveData("phase1/Data.csv");
                dataManager.saveVideoData("phase1/VideoData.csv");
                dataManager.savePlayListData("phase1/PlaylistData.csv");
                System.exit(0);
                break;
            default:
                menuPresenter.displayError("Please enter a valid input");
                startMenu();
        }
    }


    /**
     * This menu navigates the user to perform actions done by non admin users
     *
     * @param user the current user
     */
    private void nonAdminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Browse Videos \n 5 - " +
                "Upload/delete/edit videos \n 6 - View Playlists");

        NonAdminHandler nonAdminHandler = new NonAdminHandler(um, vm);

        switch (result) {
            case 4:
                vmmDisplayer.videoBrowseMenu(user);
                break;
            case 5:
                vmmDisplayer.videoActionMenu(user, nonAdminHandler);
                break;
            case 6:
                pmd.playlistBrowseMenu(user);
                break;
            default:
                basicUserMenu(user, result, false);
        }
    }
    // Trying to push
    /**
     * This menu navigates the user to perform actions done by admin users
     *
     * @param user the current user
     */
    private void adminMenu(User user) {
        int result = getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Create AdminUser \n" +
                " 5 - Delete User \n 6 - Ban User \n 7 - UnBan User \n 8 - Browse Videos \n 9 - View Playlists");
        AdminHandler adminHandler = new AdminHandler(um, vm);
        String[] info;
        switch (result) {
            case 4:
                info = getLoginInfo();
                if (adminHandler.createAdminUser(info[0], info[1])) {
                    menuPresenter.displayAlert("Account has been successfully created");
                } else {
                    menuPresenter.displayAlert("Account creation was unsuccessful");
                }
                adminMenu(user);
                break;
            case 5:
                menuPresenter.displayUsers();
                menuPresenter.displayRequest("Please enter the username of the user you wish to delete");

                if (adminHandler.deleteUser(user, sc.nextLine())) {
                    startMenu();
                }
                menuPresenter.displayAlert("Deletion was successful");
                adminMenu(user);
                break;
            case 6:
                menuPresenter.displayUsers(false);
                menuPresenter.displayRequest("Please enter the name of the user that you wish to ban");
                if (adminHandler.banUser(user, sc.nextLine())) {
                    menuPresenter.displayError("The ban operation was unsuccessful");
                } else {
                    menuPresenter.displayAlert("The ban operation was successful");
                }
                adminMenu(user);
                break;
            case 7:
                menuPresenter.displayUsers(true);
                menuPresenter.displayRequest("Please enter the name of the user that you wish to unban");
                if (adminHandler.unBanUser(sc.nextLine())) {
                    menuPresenter.displayAlert("The account has been unbanned");
                } else {
                    menuPresenter.displayError("Unban was unsuccessful");
                }
                adminMenu(user);
                break;
            case 8:
                vmmDisplayer.videoBrowseMenu(user);
                break;
            case 9:
                //todo
                pmd.playlistBrowseMenu(user);
                break;
            default:
                basicUserMenu(user, result, true);
        }
    }

    /**
     * This is a basic menu that navigates the user to perform actions done by all users
     *
     * @param user    the current user
     * @param choice  the action that user wish to perform
     * @param isAdmin the type of user
     */
    void basicUserMenu(User user, int choice, boolean isAdmin) {

        switch (choice) {
            case 1:
                menuPresenter.displayRequest("Please enter a new password");
                userActionHandler.changePassword(user, sc.nextLine());
                menuPresenter.displayAlert("Password change was successful");
                callMenu(user, isAdmin);
                break;
            case 2:
                menuPresenter.displayAlert("Checking history:");
                menuPresenter.displayLoginHistory(user, userActionHandler);
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
     * @param user    the current user
     * @param isAdmin the user's type
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
     * @param text the text to be displayed
     * @return the choice that the user made
     */
    int getUserActionChoice(String text) {
        Scanner sc = new Scanner(System.in);
        menuPresenter.displayMenuOption(text);
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return 0;
        }
    }

    /**
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

    /**
     * This method takes in and returns the name and password
     *
     * @return the username and password
     */
    String[] getLoginInfo() {
        menuPresenter.displayRequest("Please enter a username: ");
        String username = sc.nextLine();
        menuPresenter.displayRequest("Please enter a password: ");
        String password = sc.nextLine();
        return new String[]{username, password};
    }

    String[] getUploadVidInfo(){
        menuPresenter.displayRequest("Please enter title of video: ");
        String title = sc.nextLine();
        menuPresenter.displayRequest("Please enter description of video: ");
        String description = sc.nextLine();
        menuPresenter.displayRequest("Please enter number of categories you wish to input:all categories of video: ");
        int numCates = sc.nextInt();
        ArrayList<String> cates = new ArrayList<>();
        menuPresenter.displayRequest("Please enter all categories of video: ");
        for (int i=0; i<numCates;i++){
            cates.add(sc.nextLine());
        }
        menuPresenter.displayRequest("Please enter video link: ");
        String vidLink = sc.nextLine();
        return new String[]{title, description, vidLink};
    }
    ArrayList<String> getCates(){
        menuPresenter.displayRequest("Please enter number of categories you wish to input: ");
        int numCates = sc.nextInt();
        ArrayList<String> cates = new ArrayList<>();
        menuPresenter.displayRequest("Please enter all categories of video: ");
        for (int i=0; i<numCates;i++){
            cates.add(sc.nextLine());
        }return cates;
    }
}
