package entities;

import java.util.*;

/**
 * This class defines a playlist and stores all the associated data.
 *
 * @author Benedek Balla, Shu Fan Nicholas Au, Wing Zou
 * @version 1.0
 * @since 2022-07-15
 */
public class Playlist implements Iterable<String> {

    private String name;
    private int likes;
    private ArrayList<String> uniqueIDs;
    private final String userName;

    /**
     * Constructs a PlayList using playlistName and userName.
     *
     * @param playlistName the playlistName
     * @param userName the username of the person creating this Playlist.
     */
    public Playlist(String playlistName, String userName) {
        this.name = playlistName;
        likes = 0;
        uniqueIDs = new ArrayList<>();
        this.userName = userName;
    }


    /**
     * Constructs a PlayList using playlistName and userName.
     *
     * @param playlistName the name of the Playlist
     * @param likes the number of likes to the Playlist
     * @param uniqueIDs the list of uniqueIDs of videos stored in the Playlist.
     * @param userName the username of the person creating this Playlist.
     */
    public Playlist(String playlistName, int likes, ArrayList<String> uniqueIDs, String userName) {
        this.name = playlistName;
        this.likes = likes;
        this.uniqueIDs = uniqueIDs;
        this.userName = userName;
    }

    /**
     * Returns the name of the Playlist
     *
     * @return  the name of the Playlist
     */
    public String getPlaylistName() {
        return name;
    }

    /**
     * Sets the name of the Playlist.
     *
     * @param name the name of the Playlist
     */
    public void setPlaylistName(String name) {
        this.name = name;
    }

    /**
     * Returns the number of likes of the Playlist.
     *
     * @return the number of likes of the Playlist.
     */
    public int getLikes() {
        return likes;
    }

    /**
     * Sets the number of likes of the Playlist.
     *
     * @param likes the number of likes of the Playlist.
     */
    public void setLikes(int likes) {
        this.likes = likes;
    }

    /**
     * Returns the list of uniqueIDs of videos stored in the Playlist.
     *
     * @return  the list of uniqueIDs of videos stored in the Playlist.
     */

    public ArrayList<String> getUniqueIDs() {
        return uniqueIDs;
    }

    /**
     * Returns the username of the person creating this Playlist.
     *
     * @return  the username of the person creating this Playlist.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the list of uniqueIDs of videos stored in the Playlist.
     *
     * @param uniqueIDs the list of uniqueIDs of videos stored in the Playlist.
     */
    public void setUniqueIDs(ArrayList<String> uniqueIDs) {
        this.uniqueIDs = uniqueIDs;
    }

    /**
     * add a video into the Playlist
     *
     * @param uniqueID the uniqueID of the added video.
     */
    public void addUniqueID(String uniqueID) {
        this.uniqueIDs.add(uniqueID);
    }

    /**
     * remove a video from the Playlist
     *
     * @param uniqueID the uniqueID of the remove video.
     */
    public void removeUniqueID(String uniqueID) {
        uniqueIDs.remove(uniqueID);
    }

    /**
     * Return the string representation of Playlist.
     *
     * @return the string representation of Playlist.
     */
    @Override
    public String toString() {
        Iterator<String> it1 = uniqueIDs.iterator();
        StringBuilder s1 = new StringBuilder();
        while (it1.hasNext()) {
            s1.append(it1.next()).append("/");
        }

        return this.getPlaylistName() + "," + this.getLikes() + "," + s1 + "," + this.getUserName();
    }

    /**
     * Return whether p is equal to this playlist.
     *
     * @param p the target playlist
     * @return whether p is equal to this playlist
     */
    public boolean equals(Playlist p) {
        if (p.getUniqueIDs().size() == this.getUniqueIDs().size()) {

            for (String uniqueID : p.getUniqueIDs()) {
                if (!this.getUniqueIDs().contains(uniqueID)) {
                    return false;
                }
            }
            return true;

        } else {
            return false;
        }

    }

    @Override
    public Iterator<String> iterator() {
        return new PlaylistIterator();
    }

    private class PlaylistIterator implements Iterator<String> {
        private int current = 0;

        public boolean hasNext() {
            return current < uniqueIDs.size();
        }

        public String next() {
            String uniqueID = uniqueIDs.get(current);
            current += 1;
            return uniqueID;
        }
    }

}
