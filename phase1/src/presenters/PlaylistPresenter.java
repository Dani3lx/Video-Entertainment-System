package presenters;

import entities.Playlist;
import usecase.PlaylistManager;

import java.util.ArrayList;

public class PlaylistPresenter {

    PlaylistManager pm;





    public void listPlaylistNames(ArrayList<Playlist> playlists) {
        int num = 0;
        for (Playlist playlist : playlists) {
            System.out.println("\n" + num + ". " + pm.getPlName(playlist));
            num++;
        }
    }
}
