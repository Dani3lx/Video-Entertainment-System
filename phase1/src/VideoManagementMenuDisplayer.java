import java.util.ArrayList;
import java.util.Scanner;

public class VideoManagementMenuDisplayer {
    VideoPresenter vp = new VideoPresenter();
    Presenter presenter;
    MenuDisplayer menuDisplayer;

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
     * VideoManagementMenuDisplayer Constructor to initialize the object
     *
     * @param presenter The Presenter class that format and displays information to the user
     * @param menuDisplayer The main menu that this menu will interact with
     */
    public VideoManagementMenuDisplayer(Presenter presenter, MenuDisplayer menuDisplayer){
        this.presenter = presenter;
        this.menuDisplayer = menuDisplayer;
    }

    /**
     * This menu navigates the user to perform actions related to video browsing
     *
     * @param user The current user using the menu
     */
    public void videoBrowseMenu(User user) {
        int result = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Browse by name \n 2 - Browse by categories \n 3 - Browse by uploader \n 4 - Return");

        ArrayList<Video> videos;
        switch (result) {
            case 1:
                presenter.displayRequest("Please enter the name of the video");
                videos = menuDisplayer.userActionHandler.browseByName(sc.nextLine());
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 2:
                presenter.displayRequest("Please enter the name of the user, type CONTINUE to proceed");
                ArrayList<String> categories = new ArrayList<>();
                while (true) {
                    String item = sc.nextLine();
                    if (item.equals("CONTINUE")) {
                        break;
                    }
                    categories.add(item);
                }
                videos = menuDisplayer.userActionHandler.browseByCategories(categories);
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 3:
                presenter.displayRequest("Please enter the name of the uploader");
                videos = menuDisplayer.userActionHandler.browseByUploader(sc.nextLine());
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 4:
                menuDisplayer.callMenu(user, menuDisplayer.userActionHandler.validateUserPermission(user));
                break;
        }
    }

    /**
     * This method is used to choose and display the video that the user selects
     *
     * @param videos List of videos
     * @param user The current user
     */
    public void viewVideo(ArrayList<Video> videos, User user) {
        if (videos.size() == 0) {
            presenter.displayAlert("No video can be found, try again");
            videoBrowseMenu(user);
        }
        Scanner sc = new Scanner(System.in);
        presenter.displayRequest("Please enter a number to choose video you want to view");
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            if (choice >= 0 && choice < videos.size()) {
                vp.displayVideo(videos.get(choice));
                userVideoInteraction(videos.get(choice), user);
            }
        }
        presenter.displayError("Invalid input");
        videoBrowseMenu(user);

    }

    /**
     * This method is used for the user to interact with the video
     *
     * @param video List of videos
     * @param user The current user
     */
    public void userVideoInteraction(Video video, User user) {
        System.out.println("video");
        menuDisplayer.callMenu(user, menuDisplayer.userActionHandler.validateUserPermission(user));
    }
}
