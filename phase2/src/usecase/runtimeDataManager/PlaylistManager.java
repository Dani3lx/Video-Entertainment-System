package usecase.runtimeDataManager;

import entities.Playlist;
import entities.Video;
import usecase.VideoRatingComparator;
import usecase.RandomComparator;

import java.util.*;

/**
 * This class is responsible for performing all direct interactions with the Playlist entity class.
 * @author Benedek Balla, Shu Fan Nicholas Au, Akmar
 */
public class PlaylistManager {
    private ArrayList<Playlist> playlists;
    private static PlaylistManager instance;

    public static PlaylistManager getInstance(){
        if (instance == null) {
            instance = new PlaylistManager();
        }

        return instance;
    }

    /**
     * Initialize empty ArrayList for playlists
     */
    public PlaylistManager() {
        this.playlists = new ArrayList<>();
    }

    /**
     * Initialize playlists with an existing ArrayList
     * @param playlists list of playlists
     */
    public PlaylistManager(ArrayList<Playlist> playlists){
        this.playlists = playlists;
    }

    /**
     * Add playlist object to playlists
     * @param pl playlist object
     */
    public void addPlaylist(Playlist pl) {
        playlists.add(pl);
    }

    /**
     * Set playlists equal to input list of playlists
     * @param playlists new list of playlists
     */
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    /**
     * Get list of playlists
     * @return playlists
     */
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    /**
     * Get list of playlist names
     * @return list of strings
     */
    public List<String> getPlaylistNames(){
        List<String> pl_names = new ArrayList<>();
        for(Playlist playlist : playlists){
            String pl_name = playlist.getPlaylistName();
            pl_names.add(pl_name);
        }
        return pl_names;
    }


    /**
     * Add a specified Video object to a specified Playlist object
     * @param pl Playlist to be added to
     * @param vid Video to be added
     */
    public void addToPlaylist(Playlist pl, Video vid) {
        String vidid = vid.getUniqueID();
        ArrayList<String> videos = pl.getUniqueIDs();

        for (String uniqueID : videos) {
            if (uniqueID.equals(vidid)) {
                return;
            }
        }
        pl.addUniqueID(vidid);
    }

    /**
     * Delete a Video from a Playlist created by a User
     * @param pl the playlist they want to delete from
     * @param vid the video they want to delete
     */
    public void deleteFromPlaylist(Playlist pl, Video vid) {

        ArrayList<String> videos = pl.getUniqueIDs();
        for (String uniqueID : videos) {
            if (uniqueID.equals(vid.getUniqueID())) {
                    pl.removeUniqueID(vid.getUniqueID());
                    return;
            }
        }
    }

    /**
     * Return the name of each video within the specified playlist.
     * @param playlistName name of the playlist to be viewed
     * @return ArrayList<String> of video names
     */
    public ArrayList<String> namesInPlaylist(String playlistName) {
        VideoManager vm = VideoManager.getInstance();
        Playlist playlist = getPlaylistByName(playlistName);
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        ArrayList<String> videoName = new ArrayList<>();
        try {
            for (String uniqueID : uniqueIDs) {
                videoName.add(vm.getByUniqueID(uniqueID).getName());
            }
            return videoName;
        } catch (Exception e) {
            return videoName;
        }
    }

    /**
     * Return the Playlist object corresponding to the name of the playlist.
     * @param playlistName the name of the playlist to be returned
     * @return Playlist the Playlist object corresponding to the name
     */
    public Playlist getPlaylistByName(String playlistName) {
        for (Playlist playlist : playlists) {
            if (playlist.getPlaylistName().equalsIgnoreCase(playlistName)) {
                return playlist;
            }
        }
        return null; //hello
    }

    /**
     * Return copy playlist of input playlist
     * @param pl playlist to be copied
     * @param username of user copying playlist
     * @return Playlist new copy of playlist pl
     */
    public Playlist clonePlaylist(Playlist pl,String username){

        String pl_name = pl.getPlaylistName() + "_clone";
        int pl_likes = pl.getLikes();
        ArrayList<String> pl_vids = pl.getUniqueIDs();

        return new Playlist(pl_name,pl_likes,pl_vids,username);
    }

    /**
     * Reorder the specified playlist using the VideoRatingComparator and return the new Playlist object.
     * @param playlist the name of the playlist to be reordered
     * @param username of user reordering playlist
     * @return Playlist after reordering
     */
    public Playlist reorderPlaylistByRating(Playlist playlist,String username) {
        VideoManager vm = VideoManager.getInstance();
        Playlist new_pl = clonePlaylist(playlist,username);
        ArrayList<String> uniqueIDs = new_pl.getUniqueIDs();
        ArrayList<Video> videos = new ArrayList<>();
        try {
            for (String uniqueID : uniqueIDs) {
                videos.add(vm.getByUniqueID(uniqueID));
            }
            Collections.sort(videos, new VideoRatingComparator());

            ArrayList<String> newUniqueIDs = new ArrayList<>();
            for (Video video : videos) {
                newUniqueIDs.add(video.getUniqueID());
            }
            new_pl.setUniqueIDs(newUniqueIDs);
            return new_pl;
        } catch (Exception e) {
            return new_pl;
        }
    }

    /**
     * Return a new playlist reordered according to video names of input playlist
     * @param playlist to be reordered
     * @param username of current user requesting reordering
     * @return new reordered playlist
     */
    public Playlist reorderPlaylistByName(Playlist playlist,String username) {
        VideoManager vm = VideoManager.getInstance();
        Playlist new_pl = clonePlaylist(playlist,username);
        ArrayList<String> unsortedUniqueIDs = playlist.getUniqueIDs();
        ArrayList<String> sortedUniqueIDs = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        for (String id : unsortedUniqueIDs) { names.add(vm.getByUniqueID(id).getName()); }

        Collections.sort(names);

        for (String name : names) {
            sortedUniqueIDs.add(vm.getByName(name).get(0).getUniqueID());
        }

        new_pl.setUniqueIDs(sortedUniqueIDs);
        return new_pl;
    }

    /**
     * Reorder the specified playlist using the RandomComparator and return the new Playlist object.
     * @param playlist the name of the playlist to be reordered
     // * @param vm VideoManager to access Video objects
     * @return Playlist after reordering
     */
    public Playlist shufflePlaylist(Playlist playlist,String username) {
        VideoManager vm = VideoManager.getInstance();
        Playlist new_pl = clonePlaylist(playlist,username);
        ArrayList<String> uniqueIDs = new_pl.getUniqueIDs();
        ArrayList<Video> videos = new ArrayList<>();
        try {
            for (String uniqueID : uniqueIDs) {
                videos.add(vm.getByUniqueID(uniqueID));
            }
            Collections.sort(videos, new RandomComparator());

            ArrayList<String> newUniqueIDs = new ArrayList<>();
            for (Video video : videos) {
                newUniqueIDs.add(video.getUniqueID());
            }
            new_pl.setUniqueIDs(newUniqueIDs);
            return new_pl;
        } catch (Exception e) {
            return new_pl;
        }
    }

    /**
     * Increment the likes of a playlist by one
     * @param playlist the playlist to be liked
     */
    public void likePlaylist(Playlist playlist) {
        playlist.setLikes(playlist.getLikes() + 1);
    }

    /**
     * Get the name of the playlist
     * @param pl the playlist
     * @return String name of playlist
     */
    public String getPlName(Playlist pl){
        return pl.getPlaylistName();
    }

    /**
     * Change the name of the playlist
     * @param pl the playlist
     * @param NewName the new name of the playlist
     */
    public void setPlName(Playlist pl,String NewName){pl.setPlaylistName(NewName);}

    /**
     * Get the ratings of a playlist
     * @param pl the playlist
     * @return String the String of the form "_(playlist name)_ has _(x)_ likes!"
     */
    public String getRatings(Playlist pl) {
        int numlike = pl.getLikes();
        return pl.getPlaylistName() + " has " + numlike + " likes! ";
    }

    /**
     * Check if playlist name already exists
     * @param playlistName name of the playlist to check
     * @return boolean indicating if name already exists
     */
    public boolean checkPlaylistByName(String playlistName) {
        for (Playlist playlist : playlists) {
            if (playlist.getPlaylistName().equalsIgnoreCase(playlistName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if user can change playlist
     * @param pl playlist we want to change
     * @param username string name of the individual trying to change the playlist
     * @return boolean indicating if user is allowed to change the playlist
     */
    public boolean validatePlaylistAction(Playlist pl,String username){
        boolean validate;
        String pl_user = pl.getUserName();
        if (pl_user.equals(username)){
            validate = true;
        }
        else {
            validate = false;
        }
        return validate;
    }

}
