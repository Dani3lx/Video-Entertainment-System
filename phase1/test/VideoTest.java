import org.junit.*;


import java.util.ArrayList;

import static org.junit.Assert.*;


import java.util.Arrays;
import java.util.*;
import java.lang.*;

public class VideoTest {
    @Test
    public void VidGetterTest() {
        Video v1 = new Video("Akmar", "Top NBA Highlights", "These are the top NBA highlights of 2022",
                new ArrayList<>(Arrays.asList("NBA", "Basketball", "2022")), "C://Users/Akmar/Videos/NBAhighlights2022.mp4",
                "VidNo1", new ArrayList<>(Arrays.asList("35", "10")), "March 23, 2021");
        assertEquals("Akmar", v1.getUploader());
        assertEquals("Top NBA Highlights", v1.getName());
        assertEquals("These are the top NBA highlights of 2022", v1.getDescription());
        assertEquals("March 23, 2021", v1.getDate_upload());
        assertEquals("VidNo1", v1.getUniqueID());
        assertEquals("C://Users/Akmar/Videos/NBAhighlights2022.mp4", v1.getContent());
        assertEquals(new ArrayList<>(Arrays.asList("35", "10")), v1.getRatings());
        assertEquals(new ArrayList<>(Arrays.asList("NBA", "Basketball", "2022")), v1.getCategories());

    }

    @Test
    public void uploadVideoTest() {
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        ArrayList<String> ratings = new ArrayList<>();
        ratings.add("0");
        ratings.add("0");
        Video v1 = new Video("K", "hello", "greatvideo", cates, "url", VM.getVids().get(0).getUniqueID(),
                ratings, VM.getVids().get(0).getDate_upload());
        assertTrue(VM.getVids().get(0).equals(v1));
    }

    @Test
    public void deleteVideoTest() {
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        assertTrue(VM.deleteVideo(VM.getByName("hello").get(0)));
    }

    @Test
    public void getByIDTest() throws Exception {
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        assertEquals(VM.getVids().get(0), VM.getByUniqueID(VM.getVids().get(0).getUniqueID()));
    }

    @Test
    public void getByUploaderTest() {
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        assertEquals(VM.getVids(), VM.getByUploader(VM.getVids().get(0).getUploader()));
    }

    @Test
    public void getByNameTest() {
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        assertEquals(VM.getVids(), VM.getByName(VM.getVids().get(0).getName()));
    }

    @Test
    public void getByCategoryTest() {
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        assertEquals(VM.getVids(), VM.getByCategory(VM.getVids().get(0).getCategories()));
    }

    @Test
    public void getVidNamesTest() {
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        ArrayList<String> names = new ArrayList<>(List.of("hello"));
        assertEquals(names, VM.getVideoNames(VM.getVids()));
    }


}
