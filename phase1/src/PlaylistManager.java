import java.util.*;

public class PlaylistManager {

    public boolean addToPlaylist(Playlist playlist, Video video) {
        for (Video v : playlist) {
            if (v.getName().equals(video.getName())) {
                return false;
            }
        }
        playlist.addVideo(video);
        return true;
    }

    public boolean deleteFromPlaylist(Playlist playlist, Video video) {
        for (Video v : playlist) {
            if (v.getName().equals(video.getName())) {
                playlist.removeVideo(v);
                return true;
            }
        }
        return false;
    }

    public Playlist reorderPlaylistByRating(Playlist playlist) {
        ArrayList<Video> videos = playlist.getVideos();
        Collections.sort(videos, new VideoRatingComparator());
        playlist.setVideos(videos);
        return playlist;
    }

    public Playlist reorderPlaylistByName(Playlist playlist) {
        ArrayList<Video> videos = playlist.getVideos();
        Collections.sort(videos);
        playlist.setVideos(videos);
        return playlist;
    }

    public Playlist shufflePlaylist(Playlist playlist) {
        ArrayList<Video> videos = playlist.getVideos();
        Collections.sort(videos, new RandomComparator());
        playlist.setVideos(videos);
        return playlist;
    }

    public void likePlaylist(Playlist playlist){
        playlist.setLikes(playlist.getLikes() + 1);
    }

}
