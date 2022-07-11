import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;


public class VideoTest {
    @Test(timeout = 80)
    public void VidTest() {
        Video v1 = new Video("Akmar","Top NBA Highlights","These are the top NBA highlights of 2022",
                new ArrayList<String>(Arrays.asList("NBA","Basketball","2022")),"C://Users/Akmar/Videos/NBAhighlights2022.mp4",
                "VidNo1",new ArrayList<Integer>(Arrays.asList(35,10)));
    }
}
