import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;


public class VideoTest {


    @Test(timeout = 80)
    public void VidTest() {
        ArrayList<String> cates = new ArrayList<>();
        cates.add("N");

        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(0);
        ratings.add(0);
//        new ArrayList<String>(Arrays.asList("NBA","Basketball","2022"))
        Video v1 = new Video("Akmar","Top NBA Highlights","These are the top NBA highlights of 2022",cates,
                "C://Users/Akmar/Videos/NBAhighlights2022.mp4",
                "VidNo1",ratings, "today");
    }

    @Test
    public void uploadVideoTest(){
        VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");

        assertTrue(VM.uploadVideo("K", "hello", "greatvideo", cates, "url"));
    }

    public void deleteVideoTest(){
       VideoManager VM = new VideoManager();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        VM.uploadVideo("K", "hello", "greatvideo", cates, "url");
        VM.deleteVideo(VM.getByName("hello"));
    }

    public void editTitleTest(){

    }

    public void editDescriptionTest(){

    }

    public void getByNameTest(){

    }


}
