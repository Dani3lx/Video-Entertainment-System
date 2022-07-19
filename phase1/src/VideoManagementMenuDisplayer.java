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
    VideoBrowsePresenter vp;
    VideoManager vmm; // May need to add the VM usage into VBP
    PlaylistManager pmm;
    MenuPresenter menuPresenter;
    MenuDisplayer menuDisplayer;
    UserActionHandler userActionHandler;
    Scanner sc = new Scanner(System.in);

    /**
     * INSTRUCTIONS FOR ANYONE WHO WANT TO ADD THE OTHER FEATURES
     *         - Allow user to upload videos
     *         - Allow user to interact with playlist(you guys can decide what to do with the playlist idk anything about
     *         it
     *
     * Do not use System.out.println() to display anything
     * I am using presenter to do these, so use the following few methods if you want ot print something to the screen
     *
     * - if you want to display an alert such as "You have successfully logged in", call presenter.displayAlert("your message");
     * - if you want to ask the user for an input such as "Please enter a number", call presenter.displayRequest("Your request");
     * - if you want to ask show an error such as "The input was invalid", call presenter.displayRequest("Error message");
     * - if you want to list items onto the screen, call presenter.displayList(your list);
     *
     * basically look at presenter and choose a method to use, or make your own (although maybe let me know and try not to make duplicate codes)
     *
     *After you are done taking in inputs and using them to call controllers and all those stuff, if you want to access other menus or i guess go into other menus
     * call menuDisplayer.whatever menu you want. (e.g. after im done viewing the playlists, i can return to the non admin menu by calling menuDisplayer.nonAdminMenu(user).
     *
     * oh yeah most of the menu methods requires a user, dont worry about this just make sure if you are ever going to call another menu, then that method
     * must take in a user and pass that same user to the menu method call.
     */


    /**
     * Constructs a VideoManagementMenuDisplayer
     *
     * @param menuPresenter the Presenter class that format and displays information to the user
     * @param menuDisplayer the main menu that this menu will interact with
     */
    public VideoManagementMenuDisplayer(MenuPresenter menuPresenter, MenuDisplayer menuDisplayer, VideoManager vm, UserActionHandler userActionHandler, PlaylistManager pmm){
        this.menuPresenter = menuPresenter;
        this.menuDisplayer = menuDisplayer;
        this.userActionHandler = userActionHandler;
        this.vmm = vm;
        this.pmm = pmm;
        vp = new VideoBrowsePresenter(vm);
    }

    /**
     * This menu navigates the user to perform actions related to video browsing
     *
     * @param user the current user using the menu
     */
    public void videoBrowseMenu(User user) {
        int result = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Browse by name \n 2 - Browse by categories \n 3 - Browse by uploader \n 4 - Return");

        ArrayList<Video> videos;
        switch (result) {
            case 1:
                menuPresenter.displayRequest("Please enter the name of the video");
                videos = userActionHandler.browseByName(sc.nextLine());
                vp.listVideos(videos);
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
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 3:
                menuPresenter.displayRequest("Please enter the name of the uploader");
                videos = userActionHandler.browseByUploader(sc.nextLine());
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 4:
                menuDisplayer.callMenu(user, userActionHandler.isAdmin(user));
                break;
            default:
                menuPresenter.displayError("Invalid input, try again");
                videoBrowseMenu(user);
        }
    }

    /**
     * This method is used to choose and display the video that the user selects
     *
     * @param videos list of videos
     * @param user the current user
     */
    public void viewVideo(ArrayList<Video> videos, User user)  {
        if (videos.size() == 0) {
            menuPresenter.displayAlert("No video can be found, try again");
            menuDisplayer.callMenu(user, userActionHandler.isAdmin(user));
        }
        Scanner sc = new Scanner(System.in);
        menuPresenter.displayRequest("Please enter a number to choose video you want to view");
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            if (choice >= 0 && choice < videos.size()) {
                vp.displayVideo(videos.get(choice));
                userVideoInteraction(videos.get(choice), user);
            }
        }
        menuPresenter.displayError("Invalid input");
        menuDisplayer.callMenu(user, userActionHandler.isAdmin(user));
    }

    /**
     * This method is used for the user to interact with the video
     *
     * Only use controller methods to do stuff here
     *
     * @param video list of videos
     * @param user the current user
     */
    public void userVideoInteraction(Video video, User user)  {
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Like the video \n 2 - Dislike the video \n 3 - Add to playlist");

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
                // NEW
                menuPresenter.displayRequest("Please enter a playlist name");
                String name = sc.nextLine();
                pmm.addToPlaylist(name, video.getUniqueID()); // using entity directly
                break;
            default:
                menuPresenter.displayError("Invalid input");
        }
        menuDisplayer.callMenu(user, userActionHandler.isAdmin(user));
    }

    /**
     * This menu navigates the user to perform actions on specific videos
     *
     * @param user the current user using the menu
     * @param nonAdminHandler controller that dictates what happens when a video action is performed
     */

    public void videoActionMenu(User user, NonAdminHandler nonAdminHandler) {

        int result = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - View all the videos uploaded by " + user.getUserName() + " \n 2 - Upload a video " +
                "\n 3 - Delete a video \n 4 - Edit the title of a video " +
                "\n 5 - Edit the categories of a video \n 6 - Edit the description of a video");
        String uniqueID;

        switch (result) {
            case 1:
                menuPresenter.displayVideos(user, vmm.getVids());
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
                nonAdminHandler.uploadVideo(user, title, description, categories, vidlink);
                menuPresenter.displayAlert("Upload successful");
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
            default:
                menuPresenter.displayError("Invalid input, try again");
                videoActionMenu(user, nonAdminHandler);
        }
        menuDisplayer.callMenu(user, user.isAdminInd());
    }
}
