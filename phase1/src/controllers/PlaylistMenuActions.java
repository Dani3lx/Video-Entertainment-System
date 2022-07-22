package controllers;

import entities.Playlist;
import entities.User;
import entities.Video;
import usecase.PlaylistManager;
import usecase.VideoManager;

import java.util.ArrayList;

public class PlaylistMenuActions {

    private PlaylistManager pm;
    private final VideoManager vm;


    public PlaylistMenuActions(PlaylistManager pm, VideoManager vm){
        this.pm = pm;
        this.vm = vm;
    }

    public Playlist SearchPlaylist(User user, String plname) {
        Playlist pl = pm.getPlaylistByName(plname);
        return pl;
    }



    public Playlist CreateNewPlaylist(User user, String plname) {
        String user_name = user.getUserName(); // dependency?
        Playlist new_playlist = new Playlist(plname, user_name);
        if (pm.getPlaylists().contains(new_playlist)) {
            pm.addPlaylist(new_playlist);
            return new_playlist;
        } else return null;
    }

    public Boolean AddDeleteFromPlaylist(String Vidname,User user, Playlist pl, boolean Add) {
        Video vid = vm.getByUniqueID(Vidname);
        Boolean result;
        if (Add == true) {
            result = pm.addToPlaylist(pl,vid);
            return result;
        } else {
            result = pm.deleteFromPlaylist(user,pl, vid);
            return result;
        }
    }

    public Boolean AddDeleteFromPlaylist(Video vid,User user, Playlist pl, boolean Add) {
        Boolean result;
        if (Add == true) {
            result = pm.addToPlaylist(pl,vid);
            return result;
        } else {
            result = pm.deleteFromPlaylist(user,pl, vid);
            return result;
        }
    }



}
