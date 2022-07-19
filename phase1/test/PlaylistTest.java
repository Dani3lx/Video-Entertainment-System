import entities.Playlist;
import entities.Video;
import org.junit.Test;
import usecase.PlaylistManager;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class PlaylistTest {

    @Test
    public void addToPlaylistTest() {
        Playlist play = new Playlist("Music","k");
        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("0","0"));
        Video v = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
        PlaylistManager PM = new PlaylistManager();
        PM.addPlaylist(play);
        assertTrue(PM.addToPlaylist(play.getPlaylistName(), v.getUniqueID()));
        assertEquals(1, play.getUniqueIDs().size());

    }
    @Test
    public void deleteFromPlaylistTest(){
        Playlist play = new Playlist("Music","k");
        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("0","0"));
        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
        PlaylistManager PM = new PlaylistManager();
        PM.addPlaylist(play);
        assertTrue(PM.addToPlaylist(play.getPlaylistName(), v1.getUniqueID()));

        ArrayList<String> cates2 = new ArrayList<String>(Arrays.asList("fun"));
        ArrayList<String> ratings2 = new ArrayList<String>(Arrays.asList("0","0"));
        Video v2 = new Video("t","rockmusic","amazing music",cates2, "url", "2",ratings2,"today");
        assertTrue(PM.addToPlaylist(play.getPlaylistName(), v2.getUniqueID()));

        assertEquals(2, play.getUniqueIDs().size());

        assertTrue(PM.deleteFromPlaylist(play.getPlaylistName(), v2.getUniqueID()));
        assertEquals(1, play.getUniqueIDs().size());


    }
    //phase2features.Ratings not implemented yet
    @Test
    public void reorderPlaylistByRatingTest(){
        Playlist play = new Playlist("Music","k");
        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("10","0"));
        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
        PlaylistManager PM = new PlaylistManager();
        PM.addPlaylist(play);
        PM.addToPlaylist(play.getPlaylistName(), v1.getUniqueID());

//        ArrayList<String> cates2 = new ArrayList<String>(Arrays.asList("fun"));
//        ArrayList<String> ratings2 = new ArrayList<String>(Arrays.asList("2","0"));
//        entities.Video v2 = new entities.Video("t","rockmusic","amazing music",cates2, "url", "2",ratings2,"today");
//        PM.addToPlaylist(play, v2);

        Playlist play_dup = new Playlist("Music1","k1");
        play_dup.addUniqueID(v1.getUniqueID());
//        play_dup.addVideo(v2);

        assertTrue(play_dup.equals(PM.reorderPlaylistByName(play)));
    }

    @Test
    public void reorderPlaylistByNameTest(){
        Playlist play = new Playlist("Music","k");
        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("10","0"));
        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
        PlaylistManager PM = new PlaylistManager();
        PM.addPlaylist(play);

        ArrayList<String> cates2 = new ArrayList<String>(Arrays.asList("fun"));
        ArrayList<String> ratings2 = new ArrayList<String>(Arrays.asList("2","0"));
        Video v2 = new Video("t","rockmusic","amazing music",cates2, "url", "2",ratings2,"today");

        PM.addToPlaylist(play.getPlaylistName(), v2.getUniqueID());
        PM.addToPlaylist(play.getPlaylistName(), v1.getUniqueID());

        Playlist play_dup = new Playlist("Music1","k1");
        PM.addPlaylist(play_dup);
        PM.addToPlaylist(play_dup.getPlaylistName(), v1.getUniqueID());
        PM.addToPlaylist(play_dup.getPlaylistName(), v2.getUniqueID());

        assertTrue(play_dup.equals(PM.reorderPlaylistByName(play)));
    }


    @Test(timeout = 80)
    public void likePlaylistTest(){
        PlaylistManager PM = new PlaylistManager();
        Playlist play = new Playlist("Music","k");
        PM.likePlaylist(play);
        assertEquals(1,play.getLikes());
    }
}



