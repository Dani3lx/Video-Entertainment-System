package controllers;

import entities.Playlist;
import entities.User;
import entities.Video;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.ArrayList;

public class PlaylistMenuActions {

    private PlaylistManager pm;
    private UserManager um;
    private final VideoManager vm;


    public PlaylistMenuActions(PlaylistManager pm, VideoManager vm){
        this.pm = pm;
        this.vm = vm;
        this.um = new UserManager(vm);
    }

    public Playlist SearchPlaylist(User user, String plname) {
        Playlist pl = pm.getPlaylistByName(plname);
        return pl;
    }

    public ArrayList<Playlist> pl_list(){
        return pm.getPlaylists();
    }

    public String playlistName(Playlist pl){
        return pm.getPlName(pl);
    }

    public void likePlaylist(Playlist pl){
        pm.likePlaylist(pl);
    }

    public String getRatings(Playlist pl){
        return pm.getRatings(pl);
    }

    public boolean isUser(User user,Playlist pl){
        String user_name = um.getUserName(user);
        String pl_name = pm.getPlName(pl);
        return user_name.equals(pl_name);
    }

    public ArrayList<String> videosinPL(Playlist pl){
        String name = playlistName(pl);
        ArrayList<String> vidname = pm.namesInPlaylist(name, vm);
        return vidname;
    }

    public Playlist CreateNewPlaylist(User user, String plname) {
        String user_name = um.getUserName(user);
        boolean check =  pm.checkPlaylistByName(plname);
        if (check) {
            return null;
        }
        Playlist new_playlist = new Playlist(plname, user_name);
        pm.addPlaylist(new_playlist);
        return new_playlist;
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
    
    public void reorderPL(Playlist pl, String choice){
        if (choice.equals("name")){
            pm.reorderPlaylistByName(pl);
        } else if (choice.equals("rating")) {
            pm.reorderPlaylistByRating(pl,vm);
        } else if (choice.equals("shuffle")) {
            pm.shufflePlaylist(pl,vm);
        }
    }



}
