package controllers;

import entities.Playlist;
import entities.User;
import entities.Video;
import usecase.PlaylistManager;
import usecase.VideoManager;

import java.util.ArrayList;

public class PlaylistMenuActions {

    PlaylistManager pm;
    VideoManager vm;

    public PlaylistMenuActions(PlaylistManager pm, VideoManager vm){
        this.pm = pm;
        this.vm = vm;
    }

    public Playlist SearchPlaylist(User user, String plname) {
        Playlist pl = pm.getPlaylistByName(plname);
        return pl;
    }

    //todo move to a presenter


    public Playlist CreateNewPlaylist(User user, String plname) {
        String user_name = user.getUserName(); // dependency?
        Playlist new_playlist = new Playlist(plname, user_name);
        if (pm.getPlaylists().contains(new_playlist)) {
            pm.addPlaylist(new_playlist);
            return new_playlist;
        } else return null;
    }

    public Boolean AddDeleteFromPlaylist(String Vidname,User user, Playlist pl, boolean Add) {
        //todo need to turn this to controller -> make use cases with getters
        Video vid = vm.getByUniqueID(Vidname); //todo think we need to extend this to video name
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
