import entities.Comments;
import entities.Ratings;
import entities.Video;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class VideoManagerTest {
    private static final VideoManager VM = new VideoManager();
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
        video = new Video("username", "title", "description", categories, "content",
                "uniqueID", new Ratings(), "uploadDate", comments);
    }

    @Test
    public void deleteVideoTest() {
        ArrayList<Video> videos = new ArrayList<>();
        videos.add(video);
        VM.setVids(videos);

        VM.deleteVideo(video);
        assertTrue(VM.getVids().isEmpty());
    }

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

    @Test
    public void getVideoCommentsTest(){
        ArrayList<String> a = new ArrayList<>();
        a.add(":  ");
        assertEquals(a, VM.getVideoComments(video));
    }

}
