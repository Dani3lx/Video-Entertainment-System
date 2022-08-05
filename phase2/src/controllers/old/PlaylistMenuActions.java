package controllers.old;

import entities.Playlist;
import entities.User;
import entities.Video;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;

/** This class is a controller responsible for implementing playlist
 * related methods and logic and feeding it to playlist menu
 * @author akmar
 */
public class PlaylistMenuActions {

    private final PlaylistManager pm;
    private final UserManager um;
    private final VideoManager vm;

    /**
     * Contructor for this class to use the same manager inputs
     * @param pm playlistmanager used to call playlist actions
     * @param vm videomanager used to change videos within the playlist
     */
    public PlaylistMenuActions(PlaylistManager pm, VideoManager vm){
        this.pm = pm;
        this.vm = vm;
        this.um = new UserManager(vm);
    }

    /**
     * Used to search all playlists by playlist name and return the playlist with the corresponding name
     * @param plname name of playlist you want to search
     * @return playlist of corresponding name or null
     */
    public Playlist SearchPlaylist(String plname) {
        return pm.getPlaylistByName(plname);
    }

    /**
     * get the list of all playlists
     * @return list of playlists
     */

    public ArrayList<Playlist> pl_list(){
        return pm.getPlaylists();
    }

    /**
     * Get the name of the playlist
     * @param pl playlist we want name of
     * @return String playlistname
     */
    public String playlistName(Playlist pl){
        return pm.getPlName(pl);
    }

    /**
     * add a like to the playlist
     * @param pl playlist we want to like
     */

    public void likePlaylist(Playlist pl){
        pm.likePlaylist(pl);
    }

    /**
     * get the number of likes on a playlist
     * @param pl playlist we want to see likes for
     * @return string of the number of ratings playlist has
     */
    public String getRatings(Playlist pl){
        return pm.getRatings(pl);
    }

    /**
     * Condensed logic to see if the user that is accessing the current playlist
     * and the creator of the playlist are the same individual
     * @param user user that is accessing the playlist
     * @param pl playlist being accessed
     * @return true is user and playlist creator are the same user
     */

    public boolean isUser(User user,Playlist pl){
        String user_name = um.getUserName(user);
        String pl_name = pm.getPlName(pl);
        return user_name.equals(pl_name);
    }

    /**
     * returns the names of videos in the playlist
     * @param pl playlist we want videos from
     * @return Arraylist of video names in the playlist
     */
    public ArrayList<String> videosinPL(Playlist pl){
        String name = playlistName(pl);
        return pm.namesInPlaylist(name, vm);
    }

    /**
     * Creates new playlist if there is none with the same name
     * @param user person who creates playlist
     * @param plname name of the playlist
     * @return the playlist that just gets created
     */
    public Playlist CreateNewPlaylist(User user, String plname) {
        String user_name = um.getUserName(user);
        boolean check =  pm.checkPlaylistByName(plname);
        if (check) {
            return null;
        }
        Playlist new_playlist = new Playlist(plname, user_name);
        pm.addPlaylist(new_playlist);
        return new_playlist;
    }

    /**
     * Adds or deletes video from playlist
     * @param Vidname name of the video that is being add/removed
     * @param user user that is doing the add/removing
     * @param pl playlist that the video is being added or removed from
     * @param Add whether its add or remove
     * @return true or false depending on whether the operation was completed
     *
     */
    public boolean AddDeleteFromPlaylist(String Vidname, User user, Playlist pl, boolean Add) {

        ArrayList<Video> vids = vm.getByName(Vidname);
        if(vids.isEmpty()){
            return false;
        }
        Video vid = vids.get(0);
        boolean result;
        if (Add) {
            result = pm.addToPlaylist(pl, vid);
        } else {
            result = pm.deleteFromPlaylist(user, pl, vid);
        }
        return result;
    }

    /**
     * Adds or deletes video from playlist
     * @param vid video that is being add/removed
     * @param user user that is doing the add/removing
     * @param pl playlist that the video is being added or removed from
     * @param Add whether its add or remove
     * @return true or false depending on whether the operation was completed
     *
     */
    public boolean AddDeleteFromPlaylist(Video vid,User user, Playlist pl, boolean Add) {
        boolean result;
        if (Add) {
            result = pm.addToPlaylist(pl,vid);
        } else {
            result = pm.deleteFromPlaylist(user,pl, vid);
        }
        return result;
    }

    /**
     * reorders playlist based on which method is used
     * @param pl playlist being reordered
     * @param choice which reorder method
     */
    public void reorderPL(Playlist pl, String choice){
        switch (choice) {
            case "name":
                pm.reorderPlaylistByName(pl);
                break;
            case "rating":
                pm.reorderPlaylistByRating(pl, vm);
                break;
            case "shuffle":
                pm.shufflePlaylist(pl, vm);
                break;
        }
    }



}
