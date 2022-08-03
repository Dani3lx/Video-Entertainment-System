import entities.NonAdminUser;
import entities.Video;
import org.junit.Test;
import usecase.NonAdminManager;
import usecase.VideoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class NonAdminManagerTest {
    private static final VideoManager VM = new VideoManager();
    private static final NonAdminManager NAM = new NonAdminManager(VM);
    private static final NonAdminUser u1 = new NonAdminUser("k", "1");

    @BeforeClass
    public static void setUp_UploadVid() {

        NAM.uploadVideo(u1, "vid", "", new ArrayList<>(), "url");
        ArrayList<String> ratings = new ArrayList<>();
        ratings.add("0");
        ratings.add("0");
        Video v1 = new Video("k", "vid", "", new ArrayList<>(), "url", VM.getVids().get(0).getUniqueID(),
                ratings, VM.getVids().get(0).getDate_upload());
        assertTrue(VM.getVids().get(0).equals(v1));
    }


    @Test
    public void deleteVideoTest() {


        assertTrue(NAM.deleteVideo(u1, VM.getVids().get(0).getUniqueID()));


    }

    @Test
    public void editTitleTest() {

        NAM.editTitle(u1, VM.getVids().get(0).getUniqueID(), "new");
        assertEquals("new", VM.getVids().get(0).getName());

    }

    @Test
    public void editCategoriesTest() {

        ArrayList<String> newCates = new ArrayList<>(List.of("newCate"));
        NAM.editCategories(u1, VM.getVids().get(0).getUniqueID(), newCates);
        assertEquals(newCates, VM.getVids().get(0).getCategories());

    }

    @Test
    public void editDescriptionTest() {

        NAM.editDescription(u1, VM.getVids().get(0).getUniqueID(), "newDescrip");
        assertEquals("newDescrip", VM.getVids().get(0).getDescription());

    }
    @Test
    public void displayAllVidsTest(){
        Video v1 = new Video("k","vid","great",new ArrayList<>(List.of("funny")),"url","ID",
                new ArrayList<>(Arrays.asList("0","0")),"today");
        assertEquals("Title: vid (ID: ID)",NAM.displayAllVideos(u1, new ArrayList<>(List.of(v1))).get(0));
    }
}
