import entities.Comments;
import entities.NonAdminUser;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.runtimedatamanager.NonAdminManager;
import usecase.runtimedatamanager.VideoManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class NonAdminManagerTest {
    private static final VideoManager VM = VideoManager.getInstance();
    private static final NonAdminManager NAM = new NonAdminManager();
    private static final NonAdminUser u1 = new NonAdminUser("k", "1");
    private static final NonAdminUser u2 = new NonAdminUser("t", "1");


    @BeforeClass
    public static void setUp_UploadVid() {

        assertTrue(NAM.uploadVideo(u1, "vid", "", new ArrayList<>(), "url"));
        VM.getVids().get(0).getComments().add(new Comments("k", "hello", "today"));
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
    public void editCommentTest() {
        NAM.editComment(VM.getVids().get(0), u1, "great");
        assertEquals("", VM.getVids().get(0).getComments().get(0).getComment());
    }

    @Test
    public void deleteCommentTest() {
        assertTrue(NAM.deleteComment(VM.getVids().get(0), u1));
    }

    @Test
    public void addCommentTest() {
        NAM.addComment(VM.getVids().get(0), u2, "hello");
        assertEquals("hello", VM.getVids().get(0).getComments().get(1).getComment());
    }
}
