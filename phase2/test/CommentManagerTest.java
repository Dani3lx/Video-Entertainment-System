import entities.Comments;
import entities.Ratings;
import entities.Video;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.runtimedatamanager.CommentManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommentManagerTest {
    private static final CommentManager CM = new CommentManager();
    private static final Ratings r = new Ratings();
    private static final Video v = new Video("k", "vid", "", new ArrayList<>(), "url", "unique",
            r, "today", new ArrayList<>(List.of(new Comments("", "", ""))));

    @BeforeClass
    public static void addCommentSetup() {
        assertTrue(CM.addComment(v, "person", "nice"));
        assertTrue(CM.addComment(v, "person2", "good"));
        assertEquals("nice", v.getComments().get(1).getComment());
    }

    @Test
    public void editCommentTest() {
        Comments c = new Comments("person", "nice", "today");
        CM.editComment(c, "terrible vid");
        assertEquals("terrible vid", c.getComment());
    }

    @Test
    public void deleteCommentTest() {
        CM.deleteComment(v, v.getComments().get(0));
        assertEquals("person", v.getComments().get(0).getCommenter());
        assertEquals("person2", v.getComments().get(1).getCommenter());

    }
}
