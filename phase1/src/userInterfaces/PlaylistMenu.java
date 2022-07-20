package userInterfaces;

import controllers.UserActionHandler;
import entities.Playlist;
import entities.User;
import entities.Video;
import presenters.MenuPresenter;
import presenters.VideoBrowsePresenter;
import usecase.PlaylistManager;
import usecase.PlaylistMenuActions;
import usecase.VideoManager;
import userInterfaces.MenuDisplayer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class PlaylistMenu {

    /*
    * This class holds the menu options for actions related to playlist
    * Controller Class
    * */
    VideoManager vmm; // May need to add the VM usage into VBP
    PlaylistManager pmm;
    PlaylistMenuActions pma;
    VideoBrowsePresenter vbp;
    MenuPresenter menuPresenter;
    MenuDisplayer menuDisplayer;
    UserActionHandler userActionHandler;
    Scanner sc = new Scanner(System.in);

    public PlaylistMenu(MenuPresenter menuPresenter, MenuDisplayer menuDisplayer, VideoManager vm, PlaylistMenuActions pma, PlaylistManager pmm){
        this.menuPresenter = menuPresenter;
        this.menuDisplayer = menuDisplayer;
        this.pma = pma;
        this.vmm = vm;
        this.pmm = pmm;

    }
    /*
     * This is used for the user to interact with playlists and associated methods
     * will use VMM displayer/presenter for this unless otherwise necessary
     * Need to create way to #todo select and view playlist (not just get uniqueID)
     * */

    public void playlistBrowseMenu(User user){
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n1 - Search Playlist by name \n2 - Create New Playlist \n3 - Display All Playlists \n4 - Return");
        String plname;
        switch (option) {
            case 1:
                menuPresenter.displayRequest("Enter the name of the playlist: ");
                plname = sc.nextLine();
                Playlist pl = pma.SearchPlaylist(pmm,user,plname);
                if(pl==null){
                    menuPresenter.displayAlert("No playlists can be found with that name, try again");
                    menuDisplayer.callMenu(user,userActionHandler.isAdmin(user));
                }
                playlistManageMenu(user,pl);
                break;
            case 2:
                menuPresenter.displayRequest("Enter the name of the playlist you want to create: ");
                plname = sc.nextLine();
                pl = pma.CreateNewPlaylist(user,plname);
                if(pl==null){
                    menuPresenter.displayAlert("Playlist Already Exists");
                    menuDisplayer.callMenu(user,userActionHandler.isAdmin(user));
                }
                else {
                    menuPresenter.displayAlert("Successfully created: " + pl.getPlaylistName());
                    playlistManageMenu(user, pl);
                }
                break;
            case 3:
                ArrayList<Playlist> pl_list = pmm.getPlaylists();
                pma.listPLaylistNames(pl_list);
                break;
            case 4:
                menuDisplayer.callMenu(user,userActionHandler.isAdmin(user));
                break; //todo
        }
    }

    /*
     * This is used for user to interact with a specific playlist after they have selected a playlist
     * Need to create a cleaner select playlist
     * */

    public void playlistManageMenu(User user, Playlist pl){
        int option = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - View Playlist \n 2 - Add Video to Playlist \n 3 - Remove Video from Playlist \n 4 - Reorder Playlist " +
                "\n 5 - Like Playlist \n 6 -  Return");
        String VidName;
        boolean result;
        ArrayList<Video> videos;
        switch(option){
            case 1:
                viewPlaylist(user,pl);
                break;
            case 2:
                menuPresenter.displayRequest("Please enter the name of the video you would like to add to the playlist "); // todo does it make more sense to have UniqueID search rather than name?
                VidName = sc.nextLine();
                result = pma.AddDeleteFromPlaylist(VidName,user,pl,true);
                if(result){
                    menuPresenter.displayAlert("Successfully added "+VidName+" to "+pl.getPlaylistName());
                }
                else {
                    menuPresenter.displayAlert("Unsuccessful in adding "+VidName+" to "+pl.getPlaylistName());
                }
                break;
            case 3:
                menuPresenter.displayRequest("Please enter the name of the video you would like to remove from the playlist "); //todo is this now videoID?
                VidName = sc.nextLine();
                result = pma.AddDeleteFromPlaylist(VidName,user,pl,false);
                if(result){
                    menuPresenter.displayAlert("Successfully removed "+VidName+" to "+pl.getPlaylistName());
                }
                else {
                    menuPresenter.displayAlert("Unsuccessful in removing "+VidName+" to "+pl.getPlaylistName());
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
                        playlistManageMenu(user, pl);
                        break;
                }
            case 6:
                playlistBrowseMenu(user);
                break;
        }
    }


    public void viewPlaylist(User user,Playlist pl) {
        int option = menuDisplayer.getUserActionChoice("Which Action would you like to perform " +
                "\n 1 - View Video Names in Playlist \n 2 - View How Many Likes "+pl.getPlaylistName()+" has"+  "\n 3 - Change Playlist Name \n 4 -  Return ");

        switch (option){
            case 1:
                ArrayList<String> vidname = pmm.namesInPlaylist(pl.getPlaylistName(),vmm);
                //todo vbp.listVideoNames(vidname);
                break;
            case 2:
                menuPresenter.displayAlert(pma.GetRatings(pl));
                break;
            case 3:
                // TODO
                // TODO
                // TODO Finish after meeting w/ john
                // TODO
                // TODO
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

    public void ReorderPlaylist(User user, Playlist pl) { //todo user needs authority to change the playlist
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
