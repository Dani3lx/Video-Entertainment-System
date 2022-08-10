import entities.Comments;
import entities.NonAdminUser;
import entities.Ratings;
import entities.Video;
import org.junit.Test;
import usecase.runtimeDataManager.NonAdminManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.BeforeClass;

public class NonAdminManagerTest {
    private static final VideoManager VM = VideoManager.getInstance();

//    private static final VideoManager VM = new VideoManager();
    private static final NonAdminManager NAM = new NonAdminManager();
    private static final NonAdminUser u1 = new NonAdminUser("k", "1");
    private static final NonAdminUser u2 = new NonAdminUser("t", "1");


    @BeforeClass
    public static void setUp_UploadVid() {

        assertTrue(NAM.uploadVideo(u1, "vid", "", new ArrayList<>(), "url"));
        VM.getVids().get(0).getComments().add(new Comments("k","hello","today"));
//        Ratings r = new Ratings();
//        Video v1 = new Video("k", "vid", "", new ArrayList<>(), "url", VM.getVids().get(0).getUniqueID(),
//                r, VM.getVids().get(0).getDate_upload(),new ArrayList<Comments>(List.of(new Comments("", "", ""))));
//        assertTrue(VM.getVids().get(0).equals(v1));
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
    public void editCommentTest(){
        NAM.editComment(VM.getVids().get(0).getUniqueID(),u1,"great");
        assertEquals("", VM.getVids().get(0).getComments().get(0).getComment());
    }

    @Test
    public void deleteCommentTest(){
        assertTrue(NAM.deleteComment(VM.getVids().get(0).getUniqueID(), u1));
    }

    @Test
    public void addCommentTest(){
        NAM.addComment(VM.getVids().get(0).getUniqueID(),u2,"hello");
        assertEquals("hello", VM.getVids().get(0).getComments().get(1).getComment());
    }

//    @Test
//    public void displayAllVidsTest(){
//        Video v1 = new Video("k","vid","great",new ArrayList<>(List.of("funny")),"url","ID",
//                new ArrayList<>(Arrays.asList("0","0")),"today");
//        assertEquals("Title: vid (ID: ID)",NAM.ReturnUserVideos(u1, new ArrayList<>(List.of(v1))).get(0));
//    }
}
