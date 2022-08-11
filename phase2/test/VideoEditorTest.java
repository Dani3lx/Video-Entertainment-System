import entities.Comments;
import entities.NonAdminUser;
import entities.Ratings;
import entities.Video;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.VideoEditor;

import java.util.ArrayList;

import static org.junit.Assert.*;

import java.lang.*;
import java.util.List;

public class VideoEditorTest {
    private static final VideoEditor VE = new VideoEditor();
    private static Video v1;
    private static NonAdminUser u1;

    @BeforeClass
    public static void setUp() {
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        Ratings r = new Ratings();
        v1 = new Video("K", "things", "nice", cates, "url", "ID", r, "today", new ArrayList<>(List.of(new Comments("", "", ""))));
        u1 = new NonAdminUser("person", "123");

    }

    @Test
    public void editTitleTest() {
        VE.editTitle(v1, "new");
        assertEquals("new", v1.getName());
    }

    @Test
    public void editDescriptionTest() {

        VE.editDescription(v1, "newdescrip");
        assertEquals("newdescrip", v1.getDescription());
    }

    @Test
    public void editCategoriesTest() {
        ArrayList<String> cates = new ArrayList<>();
        cates.add("nothumour");
        VE.editCategories(v1, cates);
        assertEquals(cates, v1.getCategories());
    }

    @Test
    public void dislikeVideoTest() {

        VE.dislikeVideo(v1, u1);
        assertEquals(Integer.valueOf(1), v1.getRatings().getTotalDislikes());
        assertEquals(Integer.valueOf(0), v1.getRatings().getTotalLikes());
    }

    @Test
    public void likeVideoTest() {
        VE.likeVideo(v1, u1);
        assertEquals(Integer.valueOf(1), v1.getRatings().getTotalLikes());
        assertEquals(Integer.valueOf(0), v1.getRatings().getTotalDislikes());
    }

    @Test
    public void currentRatingTest() {
        assertEquals(Integer.valueOf(0), VE.currentRatingOfUser(v1, u1));

    }

    @Test
    public void deleteRatingTest() {
        VE.deleteRating(v1, u1);
        assertFalse(v1.getRatings().containsRating(u1.getUserName()));
    }
}

