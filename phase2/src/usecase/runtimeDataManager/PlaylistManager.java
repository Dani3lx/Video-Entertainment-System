package usecase.runtimeDataManager;

import entities.Playlist;
import entities.User;
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
        this.playlists = new ArrayList<Playlist>();
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
        List<String> pl_names = new ArrayList<String>();
        int i = 0;
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
     * @return boolean indicating if operation was successful
     */
    public boolean addToPlaylist(Playlist pl, Video vid) {
        String vidid = vid.getUniqueID();
        ArrayList<String> videos = pl.getUniqueIDs();

        for (String uniqueID : videos) {
            if (uniqueID.equals(vidid)) {
                return false;
            }
        }
        pl.addUniqueID(vidid);
        return true;
    }

    /**
     * Delete a Video from a Playlist created by a User
     * @param user The user who wants to delete the video
     * @param pl the playlist they want to delete from
     * @param vid the video they want to delete
     * @return boolean indicating if the operation was successful
     */
    public boolean deleteFromPlaylist(User user, Playlist pl, Video vid) {

        ArrayList<String> videos = pl.getUniqueIDs();
        for (String uniqueID : videos) {
            if (uniqueID.equals(vid.getUniqueID())) {
                if((user.getUserName()).equals(pl.getUserName())) {
                    pl.removeUniqueID(vid.getUniqueID());
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    /**
     * Delete a Video from a Playlist created by a User
     * @param pl the playlist they want to delete from
     * @param vid the video they want to delete
     * @return boolean indicating if the operation was successful
     */
    public boolean deleteFromPlaylist(Playlist pl, Video vid) {

        ArrayList<String> videos = pl.getUniqueIDs();
        for (String uniqueID : videos) {
            if (uniqueID.equals(vid.getUniqueID())) {
                    pl.removeUniqueID(vid.getUniqueID());
                    return true;
            }
        }
        return false;
    }

    /**
     * Return the name of each video within the specified playlist.
     * @param playlistName name of the playlist to be viewed
     * @param vm VideoManager used to access video names using the uniqueID
     * @return ArrayList<String> of video names
     */
    public ArrayList<String> namesInPlaylist(String playlistName, VideoManager vm) {
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
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getPlaylistName().equalsIgnoreCase(playlistName)) {
                return playlists.get(i);
            }
        }
        return null;
    }

    /**
     * Reorder the specified playlist using the VideoRatingComparator and return the new Playlist object.
     * @param playlist the name of the playlist to be reordered
     * @param vm VideoManager to access Video objects
     * @return Playlist after reordering
     */
    public Playlist reorderPlaylistByRating(Playlist playlist, VideoManager vm) {
        Playlist new_pl = playlist;
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
     * Reorder the specified playlist by uniqueIDs and return the new Playlist object.
     * @param playlist the name of the playlist to be reordered
     * @return Playlist after reordering
     */
    public Playlist reorderPlaylistByName(Playlist playlist) {
        Playlist new_pl = new Playlist(playlist.getPlaylistName(), playlist.getLikes(),
                new ArrayList<>(), playlist.getUserName());
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        Collections.sort(uniqueIDs);
        new_pl.setUniqueIDs(uniqueIDs);
        return new_pl;
    }

    public Playlist reorderPlaylistByName(Playlist playlist, VideoManager vm) {
        Playlist new_pl = new Playlist(playlist.getPlaylistName(), playlist.getLikes(),
                new ArrayList<>(), playlist.getUserName());
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
     * @param vm VideoManager to access Video objects
     * @return Playlist after reordering
     */
    public Playlist shufflePlaylist(Playlist playlist, VideoManager vm) {
        Playlist new_pl = playlist;
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
        String outline = pl.getPlaylistName() + " has " + numlike + " likes! ";
        return outline;
    }


    /**
     * Check if playlist name already exists
     * @param playlistName name of the playlist to check
     * @return boolean indicating if name already exists
     */

    public boolean checkPlaylistByName(String playlistName) {
        for (int i = 0; i < playlists.size(); i++) {
            if (playlists.get(i).getPlaylistName().equalsIgnoreCase(playlistName)) {
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
