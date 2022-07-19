import java.util.Scanner;

public class PlaylistMenuActions {

    PlaylistManager pm;
    VideoManager vm;


    public Playlist SearchPlaylist(PlaylistManager pmm,User user,String plname) {
        try {
            Playlist pl = pmm.getPlaylistByName(plname);
            return pl;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Playlist CreateNewPlaylist(User user,String plname){
        String user_name = user.getUserName();
        Playlist new_playlist = new Playlist(plname,user_name);
        if(pm.getPlaylists().contains(new_playlist)) {
            pm.addPlaylist(new_playlist);
            return new_playlist;
        }
        else throw new RuntimeException("Playlist already exists"); //Is this correct?
    }

    public Boolean AddDeleteFromPlaylist(String Vidname,User user, Playlist pl,boolean Add) throws Exception {

        Video vid = vm.getByUniqueID(Vidname); //todo think we need to extend this to video name
        Boolean result;
        if(Add==true) {
            result = pm.addToPlaylist(pl.getPlaylistName(), vid.getUniqueID());
            return result;
        } else {
            result = pm.deleteFromPlaylist(pl.getPlaylistName(), vid.getUniqueID());
            return result;
        }
    }

    public String GetRatings(Playlist pl){
        int numlike = pl.getLikes();
        String outline = pl.getPlaylistName() + " has " + numlike + " likes! ";
        return outline;
    }


}
