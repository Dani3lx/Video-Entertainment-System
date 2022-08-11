import entities.Comments;
import entities.Playlist;
import entities.Ratings;
import entities.Video;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PlaylistManagerTest {
    private static final VideoManager VM = VideoManager.getInstance();
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
        rating.addRating("username1", true);
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
        Playlist result = PM.reorderPlaylistByRating(playlist, "username");
        Playlist expected = new Playlist("name", 0,
                new ArrayList<>(Arrays.asList("uniqueID2", "uniqueID1")), "user");
        assertEquals(result.getUniqueIDs(), expected.getUniqueIDs());
    }

    @Test
    public void reorderPlaylistByNameTest() {
        Playlist result = PM.reorderPlaylistByName(playlist, "username");
        Playlist expected = new Playlist("name", 0,
                new ArrayList<>(Arrays.asList("uniqueID2", "uniqueID1")), "user");
        assertEquals(result.getUniqueIDs(), expected.getUniqueIDs());
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
    }
}



