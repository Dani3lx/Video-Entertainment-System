import entities.Ratings;
import entities.Playlist;
import entities.Video;
import entities.Comments;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlaylistManagerTest {

//    private static final Playlist play = new Playlist("Music", 0 , new ArrayList<>(Arrays.asList("2", "1", "3")), "k");
//    private static final Playlist play_dup = new Playlist("Music1", 0, new ArrayList<>(Arrays.asList("1", "3")), "k1");
//    private static final PlaylistManager PM = new PlaylistManager(new ArrayList<>(Arrays.asList(play, play_dup)));
    private static final VideoManager VM = new VideoManager();
    private static final PlaylistManager PM = new PlaylistManager();
    private static final Playlist playlist = new Playlist("name", "user");

    @BeforeClass
    public static void setUp() {
        // Initialize and populate categories
        ArrayList<String> categories = new ArrayList<>();
        categories.add("category1");

        // Initialize and populate comments
        ArrayList<Comments> comments = new ArrayList<>();
        comments.add(new Comments("", "", ""));

        // Initialize and populate ratings
        Ratings rating = new Ratings();
        rating.addRating("username1",  true);
        rating.addRating("username2", true);

        // Initialize a new videos and add it to playlist
        Video v1 = new Video("username", "b", "description", categories, "content",
                "uniqueID1", rating, "uploadDate", comments);
        Video v2 = new Video("username", "a", "description", categories, "content",
                "uniqueID2", new Ratings(), "uploadDate", comments);

        ArrayList<String> videoIDs = new ArrayList<>();
        videoIDs.add(v1.getUniqueID());
        videoIDs.add(v2.getUniqueID());

        playlist.setUniqueIDs(videoIDs);

        // Initialize VM
        ArrayList<Video> videos = new ArrayList<>();
        videos.add(v1);
        videos.add(v2);
        VM.setVids(videos);
    }


    @Test
    public void reorderPlaylistByRatingTest() {
        Playlist result = PM.reorderPlaylistByRating(playlist);
        Playlist expected = new Playlist("name", 0,
                new ArrayList<>(Arrays.asList("uniqueID2", "uniqueID1")), "user");
        assertEquals(result.getUniqueIDs(), expected.getUniqueIDs());


//        Playlist result1 = PM.getPlaylistByName("Music");
//        Playlist expect1 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("2", "1", "3")),"k");
//        assertTrue(result1.equals(expect1));
//        Playlist result2 = PM.reorderPlaylistByRating(play, VM);
//        Playlist expect2 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("1", "3", "2")),"k");
//        assertTrue(result2.equals(expect2));
    }

    @Test
    public void reorderPlaylistByNameTest() {


        Playlist result = PM.reorderPlaylistByName(playlist);
        Playlist expected = new Playlist("name", 0,
                new ArrayList<>(Arrays.asList("uniqueID2", "uniqueID1")), "user");
        assertEquals(result.getUniqueIDs(), expected.getUniqueIDs());


//        Playlist result1 = PM.getPlaylistByName("Music");
//        Playlist expect1 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("2", "1", "3")),"k");
//        assertTrue(result1.equals(expect1));
//        Playlist result2 = PM.reorderPlaylistByName(play);
//        Playlist expect2 = new Playlist( "Music", 0 ,  new ArrayList<>(Arrays.asList("1", "2", "3")),"k");
//        assertTrue(result2.equals(expect2));
    }
    @Test
    public void addToPlaylistTestPossible() {
        // Standardize playlist
        ArrayList<String> videoIDs = new ArrayList<>();
        videoIDs.add("uniqueID1");
        videoIDs.add("uniqueID2");
        playlist.setUniqueIDs(videoIDs);

        // Initialize and populate categories
        ArrayList<String> categories = new ArrayList<>();
        categories.add("category1");

        // Initialize and populate comments
        ArrayList<Comments> comments = new ArrayList<>();
        comments.add(new Comments("", "", ""));

        Video v3 = new Video("username", "name", "description", categories, "content",
                "uniqueID3", new Ratings(), "uploadDate", comments);

        PM.addToPlaylist(playlist, v3);
        assertEquals(playlist.getUniqueIDs().size(), 3);

//        Playlist play = new Playlist("Music", "k");
//        ArrayList<String> cates = new ArrayList<>(List.of("energizing"));
//        ArrayList<String> ratings = new ArrayList<>(Arrays.asList("0", "0"));
//        Video v = new Video("k", "popmusic", "amazing music", cates, "url", "1", ratings, "today");
//        PlaylistManager PM = new PlaylistManager();
//        PM.addPlaylist(play);
//        assertTrue(PM.addToPlaylist(play, v));
//        assertEquals(1, play.getUniqueIDs().size());
    }

    @Test
    public void addToPlaylistTestNotPossible() {
        // Standardize playlist
        ArrayList<String> videoIDs = new ArrayList<>();
        videoIDs.add("uniqueID1");
        videoIDs.add("uniqueID2");
        playlist.setUniqueIDs(videoIDs);

        // Initialize and populate categories
        ArrayList<String> categories = new ArrayList<>();
        categories.add("category1");

        // Initialize and populate comments
        ArrayList<Comments> comments = new ArrayList<>();
        comments.add(new Comments("", "", ""));

        Video v3 = new Video("username", "b", "description", categories, "content",
                "uniqueID1", new Ratings(), "uploadDate", comments);

        PM.addToPlaylist(playlist, v3);
        assertEquals(playlist.getUniqueIDs().size(), 2);


    }

    @Test
    public void likePlaylistTest() {
        PM.likePlaylist(playlist);
        assertEquals(playlist.getLikes(), 1);
        
//        Playlist play = new Playlist("Music", "k");
//        PM.likePlaylist(play);
//        assertEquals(1, play.getLikes());
    }

    @Test
    public void deleteFromPlaylistTest() {
        // Initialize and populate categories
        ArrayList<String> categories = new ArrayList<>();
        categories.add("category1");

        // Initialize and populate comments
        ArrayList<Comments> comments = new ArrayList<>();
        comments.add(new Comments("", "", ""));

        Video v2 = new Video("username", "a", "description", categories, "content",
                "uniqueID2", new Ratings(), "uploadDate", comments);

        PM.deleteFromPlaylist(playlist, v2);
        assertEquals(playlist.getUniqueIDs().size(), 1);

//        Playlist play2 = new Playlist("Music", 0 , new ArrayList<>(Arrays.asList("2", "1", "3")), "k");
//        Playlist play_dup2 = new Playlist("Music1", 0, new ArrayList<>(Arrays.asList("1", "3")), "k1");
//
//        PlaylistManager PM2 = new PlaylistManager(new ArrayList<>(Arrays.asList(play2, play_dup2)));
//
//        NonAdminUser u1 = new NonAdminUser("k","123");
//        ArrayList<String> cates2 = new ArrayList<>(List.of("fun"));
//        ArrayList<String> ratings2 = new ArrayList<>(Arrays.asList("2", "0"));
//        Video v2 = new Video("t", "rockmusic", "amazing music", cates2, "url", "2", ratings2, "today");
//
//        assertEquals(3, play2.getUniqueIDs().size());
//
//        assertTrue(PM2.deleteFromPlaylist(u1,play2, v2));
//        assertEquals(2, play2.getUniqueIDs().size());


    }
}



