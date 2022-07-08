import java.util.List;

public class Playlist {

    private String playlist_name;
    private int likes, length;
    private List<Video> videos;

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String name) {
        this.playlist_name = name;
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

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
