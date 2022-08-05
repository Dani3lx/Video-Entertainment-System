package presenters;

import entities.Playlist;
import usecase.runtimeDataManager.PlaylistManager;

import java.util.ArrayList;

public class PlaylistPresenter {


    public void listPlaylistNames(ArrayList<Playlist> playlists,PlaylistManager pm) {
        int num = 0;
        for (Playlist playlist : playlists) {
            System.out.println("\n" + num + ". " + pm.getPlName(playlist));
            num++;
        }
    }
    public void listStringNames(ArrayList<String> strlist) {
        int num = 0;
        for (String str : strlist) {
            System.out.println("\n" + num + ". " + str);
            num++;
        }
    }
}
