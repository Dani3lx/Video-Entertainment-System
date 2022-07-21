package entities;

import java.util.*;

public class Playlist implements Iterable<String> {

    private String name;
    private int likes;
    private ArrayList<String> uniqueIDs;
    private String userName;  // the name of the user who made the playlist

    public Playlist(String playlistName, String userName){
        this.name = playlistName;
        likes = 0;
        uniqueIDs = new ArrayList<>();
        this.userName = userName;
    }

    public Playlist(String playlistName, int likes, ArrayList<String> uniqueIDs, String userName){
        this.name = playlistName;
        this.likes = likes;
        this.uniqueIDs = uniqueIDs;
        this.userName = userName;
    }

    public String getPlaylistName() {
        return name;
    }

    public void setPlaylistName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

//    public int getLength() {
//        return length;
//    }

//    public void setLength(int length) {
//        this.length = length;
//    }

    public ArrayList<String> getUniqueIDs() {
        return uniqueIDs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUniqueIDs(ArrayList<String> uniqueIDs) {
        this.uniqueIDs = uniqueIDs;
    }

    public void addUniqueID(String uniqueID) {
        this.uniqueIDs.add(uniqueID);
    }

    public void removeUniqueID(String uniqueID) {
        uniqueIDs.remove(uniqueID);
    }

    @Override
    public String toString() {
        Iterator<String> it1 = uniqueIDs.iterator();
        StringBuilder s1 = new StringBuilder();
        while (it1.hasNext()) {
            s1.append(it1.next()).append("/");
        }

        return this.getPlaylistName() + "," + this.getLikes() + "," + s1 + "," +  this.getUserName() ;
    }

    public boolean equals(Playlist p){
        if (p.getUniqueIDs().size() == this.getUniqueIDs().size()){

            for (String uniqueID: p.getUniqueIDs()) {
                if (!this.getUniqueIDs().contains(uniqueID)) {
                    return false;
                }
            }
            return true;

        } else{
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