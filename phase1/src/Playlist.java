import java.lang.reflect.Array;
import java.util.*;

public class Playlist {

    private String name;
    private int likes, length;
    private ArrayList<Video> videos = new ArrayList<>();

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public String toString() {
        ArrayList<String> lst = new ArrayList<>();
        for (Video v : videos) {
            lst.add(v.getName());
        }
        return lst.toString();
    }

}
