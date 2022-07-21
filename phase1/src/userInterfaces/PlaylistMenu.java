package userInterfaces;

import controllers.UserActionHandler;
import entities.Playlist;
import entities.User;
import entities.Video;
import presenters.MenuPresenter;
import presenters.PlaylistPresenter;
import presenters.VideoBrowsePresenter;
import usecase.PlaylistManager;
import controllers.PlaylistMenuActions;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

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

    public PlaylistMenu(MenuPresenter menuPresenter ,VideoManager vm, PlaylistMenuActions pma, PlaylistManager pmm,UserManager um, UserActionHandler userActionHandler){
        this.vmm = vm;
        this.pmm = pmm;
        this.um = um;
        this.menuPresenter = menuPresenter;
        this.userActionHandler = userActionHandler;
        this.pma = pma;
        this.pp = new PlaylistPresenter();


    }
    /*
     * This is used for the user to interact with playlists and associated methods
     * will use VMM displayer/presenter for this unless otherwise necessary
     * Need to create way to #todo select and view playlist (not just get uniqueID)
     * */





    public void playlistBrowseMenu(User user){
        int option = getPlaylistChoice("Please input one of the following number to proceed " +
                "\n1 - Search Playlist by name \n2 - Create New Playlist \n3 - Display All Playlists \n4 - Return");

        switch (option) {
            case 1:
                menuPresenter.displayRequest("Enter the name of the playlist: ");
                String plname = sc.nextLine();
                Playlist pl = pma.SearchPlaylist(user,plname);
                checkPlaylist(pl,user,menuPresenter,userActionHandler,"No playlists can be found with that name, try again");
                playlistManageMenu(user,pl);
                break;
            case 2:
                menuPresenter.displayRequest("Enter the name of the playlist you want to create: ");
                plname = sc.nextLine();
                pl = pma.CreateNewPlaylist(user,plname);
                checkPlaylist(pl,user,menuPresenter,userActionHandler,"Playlist Already Exists");
                menuPresenter.displayAlert("Successfully created: " + pl.getPlaylistName());
                playlistManageMenu(user, pl);
                break;
            case 3:
                ArrayList<Playlist> pl_list = pmm.getPlaylists();
                pp.listPlaylistNames(pl_list,pmm);
                int sub_option = getPlaylistChoice("Do you want to "+"\n1 - Choose Playlist from list"+
                        "\n2 - Return");
                switch (sub_option){
                    case 1:
                        menuPresenter.displayAlert("Choose playlist based on number: ");
                        int i = sc.nextInt();
                        pl = pmm.getPlaylists().get(i);
                        playlistManageMenu(user, pl);
                        break;
                    case 2:
                        playlistBrowseMenu(user);
                        break;
                }
                break;
            case 4:
                menuDisplayer.callMenu(user,userActionHandler.isAdmin(user));//todo need help with this
                break;
        }
    }

    /*
     * This is used for user to interact with a specific playlist after they have selected a playlist
     * Need to create a cleaner select playlist
     * */

    public void playlistManageMenu(User user, Playlist pl){
        int option = getPlaylistChoice("Please input one of the following number to proceed " +
                "\n 1 - View Playlist \n 2 - Add Video to Playlist \n 3 - Remove Video from Playlist \n 4 - Reorder Playlist " +
                "\n 5 - Like Playlist \n 6 -  Return");
        String VidName;
        boolean result;
        switch(option){
            case 1:
                viewPlaylist(user,pl);
                break;
            case 2:
                menuPresenter.displayRequest("Please enter the name of the video you would like to add to the playlist "); // todo does it make more sense to have UniqueID search rather than name?
                VidName = sc.nextLine();
                result = pma.AddDeleteFromPlaylist(VidName,user,pl,true);
                if(result){
                    menuPresenter.displayAlert("Successfully added "+VidName+" to playlist");
                }
                else {
                    menuPresenter.displayAlert("Unsuccessful in adding "+VidName+" to playlist");
                }
                playlistManageMenu(user, pl);
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
                playlistManageMenu(user, pl);
                break;
            case 4:
                ReorderPlaylist(user, pl);
                break;
            case 5:
                int option2 = getPlaylistChoice("Do you want to like this playlist? "
                        + " \n 1 - Yes \n 2 - No");
                switch (option2){
                    case 1:
                        pmm.likePlaylist(pl);
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


    public void viewPlaylist(User user,Playlist pl) {
        int option = getPlaylistChoice("Which Action would you like to perform " +
                "\n1 - View Video Names in Playlist \n2 - View How Many Likes "+ pmm.getPlName(pl) +" has"+
                "\n3 - Change Playlist Name \n4 -  Return ");

        switch (option){
            case 1:
                ArrayList<String> vidname = pmm.namesInPlaylist(pmm.getPlName(pl),vmm);
                pp.listStringNames(vidname);
                viewPlaylist(user,pl);
                break;
            case 2:
                menuPresenter.displayAlert(pmm.getRatings(pl));
                viewPlaylist(user,pl);
                break;
            case 3:

                if ((um.getUserName(user)).equals(pmm.getPlName(pl))){ //checks if current user also created the playlist
                    menuPresenter.displayRequest("Please enter the name you would like to change " + pmm.getPlName(pl) + " to: " );
                    String PlName = sc.nextLine();
                    pl.setPlaylistName(PlName);
                }
                else menuPresenter.displayError("You do not have permission to change this Playlist's name");
                viewPlaylist(user,pl);
                break;
            case 4:
                playlistManageMenu(user,pl);
                break;
        }

    }
//todo last menu
    public void ReorderPlaylist(User user, Playlist pl) { //todo user needs authority to change the playlist
        int option = getPlaylistChoice("Please input one of the following number to proceed " +
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
    void checkPlaylist(Playlist pl,User user, MenuPresenter mp, UserActionHandler ua,String errorMsg){
        if(Objects.isNull(pl)){
            mp.displayAlert(errorMsg);
            playlistBrowseMenu(user);
        }
    }

    int getPlaylistChoice(String text) {
        menuPresenter.displayMenuOption(text);
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return 0;
        }
    }



}
