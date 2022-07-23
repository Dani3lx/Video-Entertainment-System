package usecase;

import entities.Playlist;
import entities.User;
import entities.Video;

import java.util.*;

/**
 * This class is responsible for performing all direct interactions with the Playlist entity class.
 * @author Benedek Balla, Shu Fan Nicholas Au
 */
public class PlaylistManager {
    private ArrayList<Playlist> playlists;

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
     * Add a specified Video object to a specified Playlist object
     * @param pl Playlist to be added to
     * @param vid Video to be added
     * @return boolean indicating if operation was successful
     */
    public boolean addToPlaylist(Playlist pl, Video vid) {

        ArrayList<String> videos = pl.getUniqueIDs();
        for (String uniqueID : videos) {
            if (uniqueID.equals(vid.getUniqueID())) {
                return false;
            }
        }
        pl.addUniqueID(vid.getUniqueID());
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
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
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
            playlist.setUniqueIDs(newUniqueIDs);
            return playlist;
        } catch (Exception e) {
            return playlist;
        }
    }

    /**
     * Reorder the specified playlist by name and return the new Playlist object.
     * @param playlist the name of the playlist to be reordered
     * @return Playlist after reordering
     */
    public Playlist reorderPlaylistByName(Playlist playlist) {
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        Collections.sort(uniqueIDs);
        playlist.setUniqueIDs(uniqueIDs);
        return playlist;
    }

    /**
     * Reorder the specified playlist using the RandomComparator and return the new Playlist object.
     * @param playlist the name of the playlist to be reordered
     * @param vm VideoManager to access Video objects
     * @return Playlist after reordering
     */
    public Playlist shufflePlaylist(Playlist playlist, VideoManager vm) {
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        ArrayList<Video> videos = new ArrayList<>();
        try {
            for (String uniqueID : uniqueIDs) {
                videos.add(vm.getByUniqueID(uniqueID));
            }
            ArrayList<String> newUniqueIDs = new ArrayList<>();
            for (Video video : videos) {
                newUniqueIDs.add(video.getUniqueID());
            }
            playlist.setUniqueIDs(newUniqueIDs);
            return playlist;
        } catch (Exception e) {
            return playlist;
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
     * Get the ratings of a playlist
     * @param pl the playlist
     * @return String the String of the form "_(playlist name)_ has _(x)_ likes!"
     */
    public String getRatings(Playlist pl) {
        int numlike = pl.getLikes();
        String outline = pl.getPlaylistName() + " has " + numlike + " likes! ";
        return outline;
    }

}
