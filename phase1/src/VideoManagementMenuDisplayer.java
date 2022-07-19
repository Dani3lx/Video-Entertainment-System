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

    /*
    * This is used for the user to interact with playlists and associated methods
    * will use VMM displayer/presenter for this unless otherwise necessary
    * Need to create way to #todo select and view playlist (not just get uniqueID)
    * */

    public void playlistBrowseMenu(User user)  {
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n1 - Search Playlist by name \n2 - Create New Playlist \n3 - Return");
        String plname;
        switch (option) {
            case 1:
                menuPresenter.displayRequest("Enter the name of the playlist: ");

                plname = sc.nextLine();
                try {
                    Playlist pl = pmm.getPlaylistByName(plname);
                    playlistManageMenu(user,pl);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                break;
            case 2:
                menuPresenter.displayRequest("Enter the name of the playlist you want to create: ");
                plname = sc.nextLine();
                Playlist pl = new Playlist(plname, user.getUserName()); //todo Does it make sense for User to be string?
                menuPresenter.displayAlert("Successfully created: " + pl.getPlaylistName());
                pmm.addPlaylist(pl);
                menuDisplayer.callMenu(user, userActionHandler.isAdmin(user));
                break;
            case 3:
                menuDisplayer.callMenu(user, userActionHandler.isAdmin(user));
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
//                menuPresenter.displayRequest("Please enter the name of the video you would like to add to the playlist "); // todo does it make more sense to have UniqueID search rather than name?
//                VidName = sc.nextLine();
//                videos = vmm.getByName(VidName); //todo Include logic in another class similar to VideoBrowsePresenter.java
//                for (Video vid: videos){         //todo perhaps make it so we can create a list of videos and then add them (won't have to reuse code)
//                    pmm.addToPlaylist(pl,vid);   //todo will need a cache of videos to input though
//                }
                break;
            case 3:
                menuPresenter.displayRequest("Please enter the name of the video you would like to remove from the playlist ");
                VidName = sc.nextLine();
                videos = vmm.getByName(VidName);
                for (Video vid: videos){
                    pmm.deleteFromPlaylist(pl.getPlaylistName(),vid.getUniqueID());
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
                        menuPresenter.displayAlert("You did not like " + pl.getPlaylistName()); //todo pl.getplaylistname also entity and controller interaction
                        playlistManageMenu(user, pl); //todo do I need to break this?
                }
            case 6:
                playlistBrowseMenu(user);
        }
// hello
    }

    /**
     * This menu navigates the user to perform actions on specific videos
     *
     * @param user the current user using the menu
     * @param nonAdminHandler controller that dictates what happens when a video action is performed
     */
    public void videoActionMenu(User user, NonAdminHandler nonAdminHandler){

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
                if (nonAdminHandler.deleteVideo(user, uniqueID)){
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

    public void viewPlaylist(User user,Playlist pl){
        int option = menuDisplayer.getUserActionChoice("Which Action would you like to perform " +
                "\n 1 - View Video Names in Playlist \n 2 - View How Many Likes "+pl.getPlaylistName()+" has"+  "\n 3 - Change Playlist Name \n 4 -  Return ");

        switch (option){
            case 1:
                //todo move logic to a different class
                ArrayList<String> vidname = pmm.namesInPlaylist(pl.getPlaylistName(),vmm);
                int num = 0;
                for (String video : vidname) {
                    System.out.println(num + ". " + video);
                    num++;
                }
                break;
            case 2:
                int numlike = pl.getLikes(); //todo entity and controller interaction -> change logic
                menuPresenter.displayAlert(pl.getPlaylistName() + " has " + numlike + " likes! ");
                break;
            case 3:
                if (user.getUserName().equals(pl.getUserName())){ //checks if current user also created the playlist
                    menuPresenter.displayRequest("Please enter the name you would like to change " + pl.getPlaylistName() + " to: " );
                    String PlName = menuDisplayer.sc.nextLine();
                    pl.setPlaylistName(PlName);
                }
                else menuPresenter.displayError("You do not have permission to change this Playlist's name");
                break;
            case 4:
                playlistManageMenu(user,pl);
                break;
        }

    }

    public void ReorderPlaylist(User user, Playlist pl){ //todo user needs authority to change the playlist
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Reorder Playlist Alphabetically \n 2 - Reorder Playlist by Video Rating \n 3 - Shuffle Playlist  \n 4 -  Return");

        if (user.getUserName().equals(pl.getUserName())){
            menuPresenter.displayError("You do not have permission to change the order");
            playlistManageMenu(user,pl);
        }
        else {

            switch (option){
                case 1:
                    pmm.reorderPlaylistByName(pl);
                    menuPresenter.displayAlert("You have successfully ordered "+ pl.getPlaylistName() +" by alphabetical order!");
                    break;
                case 2:
                    pmm.reorderPlaylistByRating(pl,vmm);
                    menuPresenter.displayAlert("You have successfully ordered "+ pl.getPlaylistName() +" by descending ratings order!");
                    break;
                case 3:
                    pmm.shufflePlaylist(pl,vmm);
                    menuPresenter.displayAlert("You have successfully shuffled "+ pl.getPlaylistName());
                    break;
                case 4:
                    playlistManageMenu(user,pl);
                    break;

            }
        }
    }
}
