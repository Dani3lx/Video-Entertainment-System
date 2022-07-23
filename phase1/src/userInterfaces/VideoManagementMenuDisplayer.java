package userInterfaces;

import controllers.NonAdminHandler;
import controllers.PlaylistMenuActions;
import controllers.UserActionHandler;
import entities.Playlist;
import entities.User;
import entities.Video;
import presenters.MenuPresenter;
import presenters.VideoBrowsePresenter;
import usecase.PlaylistManager;
import usecase.VideoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Responsible for displaying different menu and interacting with user to perform actions related to video.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class VideoManagementMenuDisplayer {
    private final VideoBrowsePresenter vp;
    private final PlaylistMenuActions pma;
    private final MenuPresenter menuPresenter;
    private final MenuDisplayer menuDisplayer;
    private final UserActionHandler userActionHandler;
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructs a userInterfaces.VideoManagementMenuDisplayer
     *
     * @param menuPresenter     the Presenter class that format and displays information to the user
     * @param menuDisplayer     the main menu that this menu will interact with
     * @param vm                the video manager that manages videos
     * @param userActionHandler the user action handler that handles user actions
     * @param pmm               the playlist manager that manages playlists
     */
    public VideoManagementMenuDisplayer(MenuPresenter menuPresenter, MenuDisplayer menuDisplayer, VideoManager vm,
                                        UserActionHandler userActionHandler, PlaylistManager pmm) {
        this.menuPresenter = menuPresenter;
        this.menuDisplayer = menuDisplayer;
        this.userActionHandler = userActionHandler;
        this.pma = new PlaylistMenuActions(pmm,vm);
        this.vp = new VideoBrowsePresenter(vm);
    }

    /**
     * This menu navigates the user to perform actions related to video browsing
     *
     * @param user the current user using the menu
     */
    protected void videoBrowseMenu(User user) {
        int result = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Browse by name \n 2 - Browse by categories \n 3 - Browse by uploader \n 4 - Return");

        ArrayList<Video> videos;
        switch (result) {
            case 1:
                menuPresenter.displayRequest("Please enter the name of the video");
                videos = userActionHandler.browseByName(sc.nextLine());
                viewVideo(videos, user);
                break;
            case 2:
                menuPresenter.displayRequest("Please enter the name of the categories, type CONTINUE to proceed");
                ArrayList<String> categories = new ArrayList<>();
                while (true) {
                    String item = sc.nextLine();
                    if (item.equals("CONTINUE")) {
                        break;
                    }
                    categories.add(item);
                }
                videos = userActionHandler.browseByCategories(categories);
                viewVideo(videos, user);
                break;
            case 3:
                menuPresenter.displayRequest("Please enter the name of the uploader");
                videos = userActionHandler.browseByUploader(sc.nextLine());
                viewVideo(videos, user);
                break;
            case 4:
                menuDisplayer.callMenu(user);
                break;
            default:
                menuPresenter.displayError("Invalid choice, try again");
                videoBrowseMenu(user);
        }
    }

    /**
     * This method is used to choose and display the video that the user selects
     *
     * @param videos list of videos
     * @param user   the current user
     */
    protected void viewVideo(ArrayList<Video> videos, User user) {
        vp.listVideos(videos);
        if (videos.size() == 0) {
            menuPresenter.displayAlert("No video can be found, try again");
            videoBrowseMenu(user);
        }
        Scanner sc = new Scanner(System.in);
        menuPresenter.displayRequest("Please enter a number to choose video you want to view");
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            if (choice >= 0 && choice < videos.size()) {
                vp.displayVideo(videos.get(choice));
                userVideoInteraction(videos.get(choice), user);
            } else {
                menuPresenter.displayError("Please select a valid video");
                viewVideo(videos, user);
            }
        } else {
            menuPresenter.displayError("Invalid input, try again");
            viewVideo(videos, user);
        }
    }

    /**
     * This method is used for the user to interact with the video
     *
     * @param video list of videos
     * @param user  the current user
     */
    public void userVideoInteraction(Video video, User user) {
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Like the video \n 2 - Dislike the video \n 3 - Add to playlist \n 4 - Return");

        switch (option) {
            case 1:
                userActionHandler.rateVideo(video, true);
                menuPresenter.displayAlert("You have liked the video");
                break;
            case 2:
                userActionHandler.rateVideo(video, false);
                menuPresenter.displayAlert("You have disliked the video");
                break;
            case 3:
                menuPresenter.displayRequest("Please enter a playlist name");
                String name = sc.nextLine();
                Playlist pl = pma.SearchPlaylist(user,name);
                pma.AddDeleteFromPlaylist(video,user,pl,true);
                break;
            case 4:
                menuDisplayer.callMenu(user);
            default:
                menuPresenter.displayError("Invalid input");
                userVideoInteraction(video, user);
        }
        userVideoInteraction(video, user);
    }

    /**
     * This menu navigates the user to perform actions on specific videos
     *
     * @param user            the current user using the menu
     * @param nonAdminHandler controller that dictates what happens when a video action is performed
     */

    public void videoActionMenu(User user, NonAdminHandler nonAdminHandler) {

        int result = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - View all the videos uploaded by " + user.getUserName() + " \n 2 - Upload a video " +
                "\n 3 - Delete a video \n 4 - Edit the title of a video " +
                "\n 5 - Edit the categories of a video \n 6 - Edit the description of a video" +
                "\n 7 - Return");
        String uniqueID;

        switch (result) {
            case 1:
                menuPresenter.displayVideos(user);
                break;
            case 2:
                menuPresenter.displayRequest("Enter video title: ");
                String title = sc.nextLine();
                menuPresenter.displayRequest("Enter video description (optional): ");
                String description = sc.nextLine();
                menuPresenter.displayRequest("Enter video categories seperated by commas (optional): ");
                ArrayList<String> categories = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));
                menuPresenter.displayRequest("Enter video path: ");
                String vidlink = sc.nextLine();
                Boolean upload = nonAdminHandler.uploadVideo(user, title, description, categories, vidlink);
                if (upload){
                    menuPresenter.displayAlert("Upload successful");
                } else {
                    menuPresenter.displayAlert("Upload unsuccessful, title or video path cannot be blank");
                }

                break;
            case 3:
                menuPresenter.displayRequest("Enter uniqueID of the video you want to be deleted: ");
                uniqueID = sc.nextLine();
                if (nonAdminHandler.deleteVideo(user, uniqueID)) {
                    menuPresenter.displayAlert("Delete successful");
                } else {
                    menuPresenter.displayError("Delete unsuccessful");
                }
                break;
            case 4:
                menuPresenter.displayRequest("Enter uniqueID of the video you want to edit: ");
                uniqueID = sc.nextLine();
                menuPresenter.displayRequest("Enter new title: ");
                String newTitle = sc.nextLine();
                nonAdminHandler.editTitle(user, uniqueID, newTitle);
                break;
            case 5:
                menuPresenter.displayRequest("Enter uniqueID of the video you want to edit: ");
                uniqueID = sc.nextLine();
                menuPresenter.displayRequest("Enter new categories seperated by commas: ");
                ArrayList<String> newCate = new ArrayList<>(Arrays.asList(sc.nextLine().split(",")));
                nonAdminHandler.editCategories(user, uniqueID, newCate);
                break;
            case 6:
                menuPresenter.displayRequest("Enter uniqueID of the video you want to edit: ");
                uniqueID = sc.nextLine();
                menuPresenter.displayRequest("Enter new description: ");
                String newDes = sc.nextLine();
                nonAdminHandler.editDescription(user, uniqueID, newDes);
                break;
            case 7:
                menuDisplayer.callMenu(user);
            default:
                menuPresenter.displayError("Invalid input, try again");
                videoActionMenu(user, nonAdminHandler);
        }
        menuDisplayer.callMenu(user);
    }
}
