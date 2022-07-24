package userInterfaces;

import controllers.UserActionHandler;
import entities.Playlist;
import entities.User;
import presenters.MenuPresenter;
import presenters.PlaylistPresenter;
import usecase.PlaylistManager;
import controllers.PlaylistMenuActions;
import usecase.UserManager;
import usecase.VideoManager;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class responsible for playlist menus and sub menus
 * @authors akmar
 */

public class PlaylistMenu {

    /*
     * This class holds the menu options for actions related to playlist
     * Controller Class
     * */
    VideoManager vmm; // May need to add the VM usage into VBP
    PlaylistManager pmm;
    UserManager um;
    PlaylistMenuActions pma;
    PlaylistPresenter pp;
    MenuPresenter menuPresenter;
    MenuDisplayer menuDisplayer;
    UserActionHandler userActionHandler;
    Scanner sc = new Scanner(System.in);

    /**
     * Constructor for playlist menu
     * @param menuPresenter call presenter for output
     * @param vm video manager to access videos in playlist
     * @param pma playlist menu actions to control usecases to be used for UI
     * @param pmm playlist manager, used by pma for actions
     * @param um user used by pma to check user logic
     * @param userActionHandler used by menuDisplayer
     * @param menuDisplayer used to output choices
     */
    public PlaylistMenu(MenuPresenter menuPresenter, VideoManager vm, PlaylistMenuActions pma, PlaylistManager pmm, UserManager um, UserActionHandler userActionHandler, MenuDisplayer menuDisplayer) {
        this.vmm = vm;
        this.pmm = pmm;
        this.um = um;
        this.menuPresenter = menuPresenter;
        this.userActionHandler = userActionHandler;
        this.pma = pma;
        this.pp = new PlaylistPresenter();
        this.menuDisplayer = menuDisplayer;
    }

    /**
     * Main Playlist Menu, used to find a playlist
     * @param user user who will access the menu
     */

    public void playlistBrowseMenu(User user) {
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n1 - Search Playlist by name \n2 - Create New Playlist \n3 - Display All Playlists \n4 - Return");

        switch (option) {
            case 1:
                menuPresenter.displayRequest("Enter the name of the playlist: ");
                String plname = sc.nextLine();
                Playlist pl = pma.SearchPlaylist(plname);
                checkPlaylist(pl, user, menuPresenter,  "No playlists can be found with that name, try again");
                playlistManageMenu(user, pl);
                break;
            case 2:
                menuPresenter.displayRequest("Enter the name of the playlist you want to create: ");
                plname = sc.nextLine();
                pl = pma.CreateNewPlaylist(user, plname);

                checkPlaylist(pl, user, menuPresenter,  "Playlist Already Exists");
                menuPresenter.displayAlert("Successfully created: " + pl.getPlaylistName());
                playlistManageMenu(user, pl);
                break;
            case 3:
                ArrayList<Playlist> pl_list = pma.pl_list();
                pp.listPlaylistNames(pl_list, pmm);
                int sub_option = menuDisplayer.getUserActionChoice("Do you want to " + "\n1 - Choose Playlist from list" +
                        "\n2 - Return");
                switch (sub_option) {
                    case 1:
                        menuPresenter.displayAlert("Choose playlist based on number: ");
                        int i = sc.nextInt();
                        pl = pma.pl_list().get(i);
                        playlistManageMenu(user, pl);
                        break;
                    case 2:
                        playlistBrowseMenu(user);
                        break;
                    default:
                        menuPresenter.displayError("Invalid input");
                        playlistBrowseMenu(user);
                }
                break;
            case 4:
                menuDisplayer.callMenu(user);
                break;
        }
    }

    /**
     * playlistManageMenu is used for actions on the chosen playlist by the user
     * @param user the user that is making changes to playlist
     * @param pl the playlist being changed/viewed
     */

    public void playlistManageMenu(User user, Playlist pl) {
        int option3 = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - View Playlist \n 2 - Add Video to Playlist \n 3 - Remove Video from Playlist \n 4 - Reorder Playlist " +
                "\n 5 - Like Playlist \n 6 -  Return");
        String VidName;
        boolean result;
        switch (option3) {
            case 1:
                viewPlaylist(user, pl);
                break;
            case 2:
                menuPresenter.displayRequest("Please enter the name of the video you would like to add to the playlist "); // todo does it make more sense to have UniqueID search rather than name?
                sc.nextLine();
                VidName = sc.nextLine();
                result = pma.AddDeleteFromPlaylist(VidName, user, pl, true);
                if (result) {
                    menuPresenter.displayAlert("Successfully added " + VidName + " to playlist");
                } else {
                    menuPresenter.displayAlert("Unsuccessful in adding " + VidName + " to playlist");
                }
                playlistManageMenu(user, pl);
                break;
            case 3:
                menuPresenter.displayRequest("Please enter the name of the video you would like to remove from the playlist "); //todo is this now videoID?
                sc.nextLine();
                VidName = sc.nextLine();
                System.out.println(VidName);
                result = pma.AddDeleteFromPlaylist(VidName, user, pl, false);
                if (result) {
                    menuPresenter.displayAlert("Successfully removed " + VidName + " to " + pl.getPlaylistName());
                } else {
                    menuPresenter.displayAlert("Unsuccessful in removing " + VidName + " to " + pl.getPlaylistName());
                }
                playlistManageMenu(user, pl);
                break;
            case 4:
                ReorderPlaylist(user, pl);
                break;
            case 5:
                int option2 = menuDisplayer.getUserActionChoice("Do you want to like this playlist? "
                        + " \n 1 - Yes \n 2 - No");
                switch (option2) {
                    case 1:
                        pma.likePlaylist(pl);
                        menuPresenter.displayAlert("You have successfully liked the playlist");
                        playlistManageMenu(user, pl);
                        break;
                    case 2:
                        menuPresenter.displayAlert("You did not like this playlist");
                        playlistManageMenu(user, pl);
                        break;
                }
            case 6:
                playlistBrowseMenu(user);
                break;
        }
    }

    /**
     * view playlist to see what is inside the playlist including likes, videos and ability to change the name
     * @param user user viewing/making changes to the playlist
     * @param pl playlist being viewed/made changes to
     */
    public void viewPlaylist(User user, Playlist pl) {
        int option = menuDisplayer.getUserActionChoice("Which Action would you like to perform " +
                "\n1 - View Video Names in Playlist \n2 - View How Many Likes " + pma.playlistName(pl) + " has" +
                "\n3 - Change Playlist Name \n4 -  Return ");

        switch (option) {
            case 1:
                ArrayList<String> vidname = pma.videosinPL(pl);
                pp.listStringNames(vidname);
                viewPlaylist(user, pl);
                break;
            case 2:
                String ratings = pma.getRatings(pl);
                menuPresenter.displayAlert(ratings);
                viewPlaylist(user, pl);
                break;
            case 3:
                boolean check = pma.isUser(user,pl);
                if (check) { //checks if current user also created the playlist
                    menuPresenter.displayRequest("Please enter the name you would like to change " + pma.playlistName(pl) + " to: ");
                    String PlName = sc.nextLine();
                    pl.setPlaylistName(PlName);
                } else menuPresenter.displayError("You do not have permission to change this Playlist's name");
                viewPlaylist(user, pl);
                break;
            case 4:
                playlistManageMenu(user, pl);
                break;
        }

    }

    /**
     * ReorderPlaylist is the menu where you choose which reorder method to use
     * @param user checks if user is able to make changes to playlist
     * @param pl playlist being reordered
     */
    public void ReorderPlaylist(User user, Playlist pl) { //todo user needs authority to change the playlist
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Reorder Playlist Alphabetically \n 2 - Reorder Playlist by Video Rating \n 3 - Shuffle Playlist  \n 4 -  Return");
        boolean check = pma.isUser(user,pl);
        if (check) {
            menuPresenter.displayError("You do not have permission to change the order");
            playlistManageMenu(user, pl);
        } else {

            switch (option) {
                case 1:
                    pma.reorderPL(pl,"name");
                    menuPresenter.displayAlert("You have successfully ordered " + pma.playlistName(pl) + " by alphabetical order!");
                    ReorderPlaylist(user,pl);
                    break;
                case 2:
                    pma.reorderPL(pl,"rating");
                    menuPresenter.displayAlert("You have successfully ordered " + pma.playlistName(pl) + " by descending ratings order!");
                    ReorderPlaylist(user,pl);
                    break;
                case 3:
                    pma.reorderPL(pl,"shuffle");
                    menuPresenter.displayAlert("You have successfully shuffled " + pma.playlistName(pl));
                    ReorderPlaylist(user,pl);
                    break;
                case 4:
                    playlistManageMenu(user, pl);
                    break;

            }
        }
    }

    /**
     * helper function used to give error messages
     * @param pl playlist being checked
     * @param user user that interacts with playlist
     * @param mp presenter used to output prompts
     * @param errorMsg message to display
     */
    void checkPlaylist(Playlist pl, User user, MenuPresenter mp,  String errorMsg) {
        if (Objects.isNull(pl)) {
            mp.displayAlert(errorMsg);
            playlistBrowseMenu(user);
        }
    }

}
