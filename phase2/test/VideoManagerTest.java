import entities.*;
import org.junit.*;
import usecase.runtimeDataManager.VideoManager;
import static org.junit.Assert.*;
import java.util.*;
import java.lang.*;

public class VideoManagerTest {
    private static VideoManager VM = new VideoManager();
    private static Video video;

    @BeforeClass
    public static void setUp() {
        // Initialize and populate categories
        ArrayList<String> categories = new ArrayList<>();
        categories.add("category1");

        // Initialize and populate comments
        ArrayList<Comments> comments = new ArrayList<>();
        comments.add(new Comments("", "", ""));

        // Initialize a new video
        Video v = new Video("username", "title", "description", categories, "content",
                "uniqueID", new Ratings(), "uploadDate", comments);
        video = v;
    }

//    I DON'T THINK TESTING GETTERS IS NECESSARY

//    @Test
//    public void VidGetterTest() {
//        Video v1 = new Video("Akmar", "Top NBA Highlights", "These are the top NBA highlights of 2022",
//                new ArrayList<>(Arrays.asList("NBA", "Basketball", "2022")), "C://Users/Akmar/Videos/NBAhighlights2022.mp4",
//                "VidNo1", new ArrayList<>(Arrays.asList("35", "10")), "March 23, 2021");
//        assertEquals("Akmar", v1.getUploader());
//        assertEquals("Top NBA Highlights", v1.getName());
//        assertEquals("These are the top NBA highlights of 2022", v1.getDescription());
//        assertEquals("March 23, 2021", v1.getDate_upload());
//        assertEquals("VidNo1", v1.getUniqueID());
//        assertEquals("C://Users/Akmar/Videos/NBAhighlights2022.mp4", v1.getContent());
//        assertEquals(new ArrayList<>(Arrays.asList("35", "10")), v1.getRating());
//        assertEquals(new ArrayList<>(Arrays.asList("NBA", "Basketball", "2022")), v1.getCategories());
//
//    }

    @Test
    public void deleteVideoTest() {
        ArrayList<Video> videos = new ArrayList<>();
        videos.add(video);
        VM.setVids(videos);

        VM.deleteVideo(video);
        assertTrue(VM.getVids().isEmpty());
    }

//        ArrayList<String> cates = new ArrayList<>();
//        cates.add("humour");
//        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
//        ArrayList<String> ratings = new ArrayList<>();
//        ratings.add("0");
//        ratings.add("0");
//        Video v1 = new Video("K", "hello", "greatvideo", cates, "url", VM.getVids().get(0).getUniqueID(),
//                ratings, VM.getVids().get(0).getDate_upload());
//        assertTrue(VM.getVids().get(0).equals(v1));

    @Test
    public void uploadVideoTestEmpty() {
        VM.uploadVideo(video.getUploader(), video.getName(), video.getDescription(),
                video.getCategories(), video.getContent());
        assertEquals(VM.getVids().size(), 1);
    }

    @Test
    public void uploadVideoTestNonEmpty() {
        ArrayList<Video> videos = new ArrayList<>();
        videos.add(video);
        VM.setVids(videos);

        VM.uploadVideo(video.getUploader(), video.getName(), video.getDescription(),
                video.getCategories(), video.getContent());
        assertEquals(VM.getVids().size(), 2);
        assertNotEquals(VM.getVids().get(0).getUniqueID(), VM.getVids().get(1).getUniqueID());
    }

    @Test
    public void getByUniqueIDTest() {
        assertEquals(VM.getVids().get(0), VM.getByUniqueID(video.getUniqueID()));
    }

    @Test
    public void getByUploaderTest() {
        assertEquals(VM.getVids().get(0), VM.getByUploader(video.getUploader()).get(0));
    }

    @Test
    public void getByNameTest() {
        assertEquals(VM.getVids().get(0), VM.getByName(video.getName()).get(0));
    }

    @Test
    public void getByCategoryTest() {
        assertEquals(VM.getVids().get(0), VM.getByCategory(video.getCategories()).get(0));
    }

//    I DON'T THINK TESTING GETTERS IS NECESSARY

//    @Test
//    public void getVidNamesTest() {
//        ArrayList<String> names = new ArrayList<>(List.of("hello"));
//        assertEquals(names, VM.getVideoNames(VM.getVids()));
//    }

}
