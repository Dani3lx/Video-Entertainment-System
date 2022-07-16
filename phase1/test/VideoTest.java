import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.*;
import java.lang.*;

public class VideoTest {
    @Test(timeout = 80)
    public void VidGetterTest() {
        Video v1 = new Video("Akmar","Top NBA Highlights","These are the top NBA highlights of 2022",
                new ArrayList<String>(Arrays.asList("NBA","Basketball","2022")),"C://Users/Akmar/Videos/NBAhighlights2022.mp4",
                "VidNo1",new ArrayList<String>(Arrays.<String>asList("35","10")),"March 23, 2021");
        assertEquals("Akmar",v1.getUploader());
        assertEquals("Top NBA Highlights",v1.getName());
        assertEquals("These are the top NBA highlights of 2022",v1.getDescription());
        assertEquals("March 23, 2021",v1.getDate_upload());
        assertEquals("VidNo1",v1.getUniqueID());
        assertEquals("C://Users/Akmar/Videos/NBAhighlights2022.mp4",v1.getContent());
        assertEquals(new ArrayList<String>(Arrays.<String>asList("35","10")),v1.getRatings());
        assertEquals(new ArrayList<String>(Arrays.asList("NBA","Basketball","2022")),v1.getCategories());

    }

}
