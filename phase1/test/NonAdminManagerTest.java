import org.junit.Test;
import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class NonAdminManagerTest {
    @Test
    public void uploadVideoTest(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k","1");
        NonAdminManager NAM = new NonAdminManager(UM, VM);
        assertTrue(NAM.uploadVideo(u1, "vid","url"));

    }

    @Test
    public void deleteVideoTest(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k","1");
        NonAdminManager NAM = new NonAdminManager(UM, VM);
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        ArrayList<String> ratings = new ArrayList<>();
        new ArrayList<String>(Arrays.asList("10","0"));
        NAM.uploadVideo(u1, "vid","url");

        assertTrue(NAM.deleteVideo(u1, VM.getVids().get(0).getUniqueID()));
        //Video vid = new Video("k","hello", "greatvideo", cates, "url" ,"ID",ratings,"today");


    }

    @Test
    public void editTitleTest(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k","1");
        NonAdminManager NAM = new NonAdminManager(UM, VM);
        NAM.uploadVideo(u1, "vid","url");
        NAM.editTitle(u1,VM.getVids().get(0).getUniqueID(),"new");
        assertEquals("new",VM.getVids().get(0).getName());

    }

    @Test
    public void editCategoriesTest(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k","1");
        NonAdminManager NAM = new NonAdminManager(UM, VM);
        NAM.uploadVideo(u1, "vid","url");
        ArrayList<String> newCates= new ArrayList<String>(Arrays.asList("newCate"));
        NAM.editCategories(u1,VM.getVids().get(0).getUniqueID(),newCates);
        assertEquals("newCate",VM.getVids().get(0).getCategories().toString());

    }

    @Test
    public void editDescriptionTest(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k","1");
        NonAdminManager NAM = new NonAdminManager(UM, VM);
        NAM.uploadVideo(u1, "vid","url");
        NAM.editDescription(u1,VM.getVids().get(0).getUniqueID(),"newDescrip");
        assertEquals("newDescrip",VM.getVids().get(0).getDescription());

    }
}
