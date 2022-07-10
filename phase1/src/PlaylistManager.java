import java.util.*;

public class PlaylistManager {

    public boolean addToPlaylist(Playlist playlist, Video video) {
        ArrayList<Video> videos = playlist.getVideos();
        for (Video v : videos) {
            if (v.getName().equals(video.getName())) {
                return false;
            }
        }
        videos.add(video);
        playlist.setVideos(videos);
        return true;
    }

    public boolean deleteFromPlaylist(Playlist playlist, Video video) {
        ArrayList<Video> videos = playlist.getVideos();
        for (Video v : videos) {
            if (v.getName().equals(video.getName())) {
                videos.remove(v);
                playlist.setVideos(videos);
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
