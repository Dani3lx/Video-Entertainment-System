import entities.NonAdminUser;
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

    private static Video v1 =  new Video("k", "popmusic", "amazing music", new ArrayList<>(List.of("energizing"))
            , "url", "1", new ArrayList<>(Arrays.asList("10", "0")), "today");

    private static Video v2 = new Video("t", "rockmusic", "amazing music",
            new ArrayList<>(List.of("fun")), "url", "2", new ArrayList<>(Arrays.asList("2", "0")), "today");

    private static Video v3 = new Video("k", "moremusic", "amazing music", new ArrayList<>(List.of("energizing"))
            , "url", "3", new ArrayList<>(Arrays.asList("1", "0")), "today");
    private static final Playlist play = new Playlist("Music", 0 , new ArrayList<>(Arrays.asList("2", "1", "3")), "k");
    private static final Playlist play_dup = new Playlist("Music1", 0, new ArrayList<>(Arrays.asList("1", "3")), "k1");
    private static PlaylistManager PM = new PlaylistManager(new ArrayList<Playlist>(Arrays.asList(play, play_dup)));
    private static final VideoManager VM = new VideoManager();
    @Test
    public void reorderPlaylistByRatingTest() {
        Playlist result1 = PM.getPlaylistByName("Music");
        Playlist expect1 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("2", "1", "3")),"k");
        assertTrue(result1.equals(expect1));
        Playlist result2 = PM.reorderPlaylistByRating(play, VM);
        Playlist expect2 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("1", "3", "2")),"k");
        assertTrue(result2.equals(expect2));
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

    @Test
    public void likePlaylistTest() {
        PlaylistManager PM = new PlaylistManager();
        Playlist play = new Playlist("Music", "k");
        PM.likePlaylist(play);
        assertEquals(1, play.getLikes());
    }

    @Test
    public void deleteFromPlaylistTest() {
        Playlist play2 = new Playlist("Music", 0 , new ArrayList<>(Arrays.asList("2", "1", "3")), "k");
        Playlist play_dup2 = new Playlist("Music1", 0, new ArrayList<>(Arrays.asList("1", "3")), "k1");

        PlaylistManager PM2 = new PlaylistManager(new ArrayList<Playlist>(Arrays.asList(play2, play_dup2)));

        NonAdminUser u1 = new NonAdminUser("k","123");
        ArrayList<String> cates2 = new ArrayList<>(List.of("fun"));
        ArrayList<String> ratings2 = new ArrayList<>(Arrays.asList("2", "0"));
        Video v2 = new Video("t", "rockmusic", "amazing music", cates2, "url", "2", ratings2, "today");

        assertEquals(3, play2.getUniqueIDs().size());

        assertTrue(PM2.deleteFromPlaylist(u1,play2, v2));
        assertEquals(2, play2.getUniqueIDs().size());


    }
}



