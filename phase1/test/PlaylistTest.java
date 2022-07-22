import entities.Playlist;
import entities.Video;
import org.junit.Test;
import org.junit.BeforeClass;
import usecase.PlaylistManager;
import usecase.VideoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlaylistTest {

    private static final PlaylistManager PM = new PlaylistManager();
    private static final Playlist play = new Playlist("Music", "k");
    private static final Playlist play_dup = new Playlist("Music1", "k1");
    private static final VideoManager VM = new VideoManager();

    @BeforeClass
    public static void setUp() {
        PM.addPlaylist(play);
        PM.addPlaylist(play_dup);

        ArrayList<String> cates = new ArrayList<>(List.of("energizing"));
        ArrayList<String> ratings = new ArrayList<>(Arrays.asList("10", "0"));
        Video v1 = new Video("k", "popmusic", "amazing music", cates, "url", "1", ratings, "today");

        ArrayList<String> ratings3 = new ArrayList<>(Arrays.asList("1", "0"));
        Video v3 = new Video("k", "moremusic", "amazing music", cates, "url", "3", ratings3, "today");

        ArrayList<String> cates2 = new ArrayList<>(List.of("fun"));
        ArrayList<String> ratings2 = new ArrayList<>(Arrays.asList("2", "0"));
        Video v2 = new Video("t", "rockmusic", "amazing music", cates2, "url", "2", ratings2, "today");

        PM.addToPlaylist(play, v2);
        PM.addToPlaylist(play, v1);
        PM.addToPlaylist(play, v3);

        PM.addToPlaylist(play_dup, v1);
        PM.addToPlaylist(play_dup, v3);
    }

    @Test
    public void addToPlaylistTest() {
        Playlist play = new Playlist("Music", "k");
        ArrayList<String> cates = new ArrayList<>(List.of("energizing"));
        ArrayList<String> ratings = new ArrayList<>(Arrays.asList("0", "0"));
        Video v = new Video("k", "popmusic", "amazing music", cates, "url", "1", ratings, "today");
        PlaylistManager PM = new PlaylistManager();
        PM.addPlaylist(play);
        assertTrue(PM.addToPlaylist(play, v));
        assertEquals(1, play.getUniqueIDs().size());

    }

    /*@Test
    todo user needs to have made the playlist to delete vids from it
    public void deleteFromPlaylistTest() {


        assertEquals(3, play.getUniqueIDs().size());

        assertTrue(PM.deleteFromPlaylist(play, play.getUniqueIDs().get(0)));
        assertEquals(2, play.getUniqueIDs().size());


    }*/


    @Test
    public void reorderPlaylistByRatingTest() {


        assertTrue(PM.reorderPlaylistByRating(play, VM).equals(play_dup));
    }

    @Test
    public void reorderPlaylistByNameTest() {
        Playlist result1 = PM.getPlaylistByName("Music");
        Playlist expect1 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("2", "1", "3")),"k");
        assertTrue(result1.equals(expect1));
        Playlist result2 = PM.reorderPlaylistByName(play);
        Playlist expect2 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("1", "2", "3")),"k");
        assertTrue(result2.equals(expect2));
    }


    @Test
    public void likePlaylistTest() {
        PlaylistManager PM = new PlaylistManager();
        Playlist play = new Playlist("Music", "k");
        PM.likePlaylist(play);
        assertEquals(1, play.getLikes());
    }
}



