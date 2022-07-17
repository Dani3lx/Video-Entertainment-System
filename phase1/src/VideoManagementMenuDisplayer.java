import java.util.ArrayList;
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
    public VideoManagementMenuDisplayer(MenuPresenter menuPresenter, MenuDisplayer menuDisplayer, VideoManager vm, UserActionHandler userActionHandler){
        this.menuPresenter = menuPresenter;
        this.menuDisplayer = menuDisplayer;
        this.userActionHandler = userActionHandler;
        vp = new VideoBrowsePresenter(vm);
    }

    /**
     *
     * THESE ARE FOR THE PLAY LIST STUFFS
     *
     */

    public void playlistMenu(User user, NonAdminHandler nonAdminHandler){

        int result = menuDisplayer.getUserActionChoice("1 - Select a playlist \n2 - Add playlist \n3 - Delete playlist");

        ArrayList<Playlist> userPlaylists = nonAdminHandler.getPlaylists();

        vp.displayPlaylist(userPlaylists);

        switch (result) {
            case 1:
                selectPlaylist(userPlaylists, user, nonAdminHandler);
                break;
            case 2:
                // todo add a playlist to the user using non admin handler
                System.out.println("ADD PLAYLIST"); // place holder
                playlistMenu(user, nonAdminHandler);
                break;
            case 3:
                // todo Remove a playlist to the user using non admin handler
                System.out.println("REMOVE PLAYLIST"); // place holder
                playlistMenu(user, nonAdminHandler);
                break;
            default:
                menuPresenter.displayError("Invalid input, try again");
                playlistMenu(user, nonAdminHandler);
        }
    }

    public void selectPlaylist(ArrayList<Playlist> playlists, User user, NonAdminHandler nonAdminHandler) {
        if (playlists.size() == 0) {
            menuPresenter.displayAlert("No playlists can be found, try again");
            playlistMenu(user, nonAdminHandler);
        }
        Scanner sc = new Scanner(System.in);
        menuPresenter.displayRequest("Please enter a number to choose playlist you want to view");
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            if (choice >= 0 && choice < playlists.size()) {
                int result = menuDisplayer.getUserActionChoice("1 - View playlist \n2 - Edit playlist");
                switch (result) {
                    case 1:
                        // todo figure out a way to transform the playlist into an arraylist of videos then call the viewVideo method with it.

                        ArrayList<Video> videos = new ArrayList<>(); // place holder
                        viewVideo(videos, user);
                    case 2:
                        editPlaylist();
                }

            }
        }
        menuPresenter.displayError("Invalid input");
        playlistMenu(user, nonAdminHandler);
    }

    /**
     * Allows the user to add or delete from playlist. (This can get fairly technical so good luck).
     */
    public void editPlaylist(){
        // todo
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
                menuPresenter.displayRequest("Please enter the name of the user, type CONTINUE to proceed");
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
    public void viewVideo(ArrayList<Video> videos, User user) {
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
    public void userVideoInteraction(Video video, User user) {
        // todo do the liking and rating and stuff here. Maybe if the user name matches the name of the current user, you can edit the title and categories and stuff.
        menuDisplayer.callMenu(user, userActionHandler.isAdmin(user));
    }

    /*
    * This is used for the user to interact with playlists and associated methods
    * will use VMM displayer/presenter for this unless otherwise necessary
    * Need to create way to #todo select and view playlist (not just get uniqueID)
    * */

    public void playlistBrowseMenu(User user){
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Search Playlist by name \n 2 - Create New Playlist \n 2 -  Return ");
        ArrayList<Playlist> pl;
        switch (option) {
            case 1:
                menuPresenter.displayRequest("Enter the name of the playlist: ");
                // TODO: 7/17/2022: CREATE METHOD TO SEARCH FOR PLAYLIST

                break;
            case 2:
                //TODO create new playlist method here
                break;
            case 3:
                menuDisplayer.callMenu(user, menuDisplayer.userActionHandler.isAdmin(user));
                break;
        }
    }

    /*
    * This is used for user to interact with a specific playlist after they have selected a playlist
    * Need to create a cleaner select playlist
    * */

    public void playlistManageMenu(User user,Playlist pl){
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - View Playlist \n 2 - Add Video to Playlist \n 3 - Remove Video from Playlist \n 4 - Reorder Playlist " +
                "\n 5 - Like Playlist \n 6 -  Return");
        String VidName;
        ArrayList <Video> videos;
        switch(option){
            case 1:
                viewPlaylist(user,pl);
                break;
            case 2:
                menuPresenter.displayRequest("Please enter the name of the video you would like to add to the playlist "); // todo does it make more sense to have UniqueID search rather than name?
                VidName = menuDisplayer.sc.nextLine();
                videos = vmm.getByName(VidName); //todo Include logic in another class similar to VideoBrowsePresenter.java
                for (Video vid: videos){         //todo perhaps make it so we can create a list of videos and then add them (won't have to reuse code)
                    pmm.addToPlaylist(pl,vid);   //todo will need a cache of videos to input though
                }
                break;
            case 3:
                menuPresenter.displayRequest("Please enter the name of the video you would like to remove from the playlist ");
                VidName = menuDisplayer.sc.nextLine();
                videos = vmm.getByName(VidName);
                for (Video vid: videos){
                    pmm.deleteFromPlaylist(pl,vid);
                }
                break;
            case 4:
                ReorderPlaylist(user, pl);
                break;
            case 5:
                int option2 = menuDisplayer.getUserActionChoice("Do you want to like this playlist: "+ pl.getPlaylistName()
                        + " \n 1 - Yes \n 2 - No");
                switch (option2){
                    case 1:
                        pmm.likePlaylist(pl);
                        menuPresenter.displayAlert("You have successfully liked " + pl.getPlaylistName());
                        break;
                    case 2:
                        menuPresenter.displayAlert("You did not like " + pl.getPlaylistName());
                        playlistManageMenu(user, pl); //todo do I need to break this?
                }
            case 6:
                playlistBrowseMenu(user);
        }

    }

    public void viewPlaylist(User user,Playlist pl){

    }

    public void ReorderPlaylist(User user, Playlist pl){ //todo user needs authority to change the playlist

    }
}
