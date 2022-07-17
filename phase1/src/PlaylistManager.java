import java.lang.reflect.Array;
import java.util.*;


public class PlaylistManager {
    private ArrayList<Playlist> playlists;
//    public PlaylistManager(){
//        playlists = new ArrayList<>();
//    }
    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Playlist> getPlaylists(){
        return playlists;
    }


//    public ArrayList<Playlist> playlists;

    public PlaylistManager(){
        this.playlists = new ArrayList<Playlist>();
    }

    public boolean addToPlaylist(Playlist playlist, Video video) {
        for (String uniqueID : playlist) {
            if (uniqueID.equals(video.getUniqueID())) {
                return false;
            }
        }
        playlist.addUniqueID(video.getUniqueID());
//        playlist.setLength(playlist.getLength()+1);
        return true;
    }

    public ArrayList<String> namesInPlaylist(Playlist playlist, VideoManager vm){
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        ArrayList<String> videoName = new ArrayList<>();
        try {
            for (String uniqueID : uniqueIDs) {
                videoName.add(vm.getByUniqueID(uniqueID).getName());
            }
            return videoName;
        }
        catch(Exception e){
            System.out.println("uniqueID not find in VideoManager, will be returning the old playlist");
            return videoName;
        }
    }


    public Playlist getPlaylistByName(String playlistName) throws Exception{
        for (int i = 0; i < playlists.size(); i++){
            if (playlists.get(i).getPlaylistName().equalsIgnoreCase(playlistName)){
                return playlists.get(i);
            }
        }
        throw new Exception("Didn't find matching Playlist Name");
    }

    public boolean deleteFromPlaylist(Playlist playlist, Video video) {
        for (String uniqueID : playlist) {
            if (uniqueID.equals(video.getUniqueID())) {
                playlist.removeUniqueID(uniqueID);
//                playlist.setLength(playlist.getLength()-1);
                return true;
            }
        }
        return false;
    }

    public Playlist reorderPlaylistByRating(Playlist playlist, VideoManager vm) {
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        ArrayList<Video> videos = new ArrayList<>();
        try {
            for (String uniqueID : uniqueIDs) {
                videos.add(vm.getByUniqueID(uniqueID));
            }
            Collections.sort(videos, new VideoRatingComparator());

            ArrayList<String> newUniqueIDs = new ArrayList<>();
            for (Video video: videos){
                newUniqueIDs.add(video.getUniqueID());
            }
            playlist.setUniqueIDs(newUniqueIDs);
            return playlist;
        }
        catch(Exception e){
            System.out.println("uniqueID not find in VideoManager, will be returning the old playlist");
            return playlist;
        }
    }

    public Playlist reorderPlaylistByName(Playlist playlist) {
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        Collections.sort(uniqueIDs);
        playlist.setUniqueIDs(uniqueIDs);
        return playlist;
    }

    public Playlist shufflePlaylist(Playlist playlist, VideoManager vm) {
        ArrayList<String> uniqueIDs = playlist.getUniqueIDs();
        ArrayList<Video> videos = new ArrayList<>();
        try {
            for (String uniqueID : uniqueIDs) {
                videos.add(vm.getByUniqueID(uniqueID));
            }
            ArrayList<String> newUniqueIDs = new ArrayList<>();
            for (Video video: videos){
                newUniqueIDs.add(video.getUniqueID());
            }
            playlist.setUniqueIDs(newUniqueIDs);
            return playlist;
        }
        catch(Exception e){
            System.out.println("uniqueID not find in VideoManager, will be returning the old playlist");
            return playlist;
        }
    }

    public void likePlaylist(Playlist playlist){
        playlist.setLikes(playlist.getLikes() + 1);
    }

}
