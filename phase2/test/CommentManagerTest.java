import entities.Comments;
import entities.Ratings;
import entities.Video;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.runtimeDataManager.CommentManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;

import static org.junit.Assert.*;

import java.util.List;

public class CommentManagerTest {
    private static final CommentManager CM = new CommentManager();
    private static final VideoManager VM = new VideoManager();
    @BeforeClass
    public static void addCommentSetup(){
        VM.uploadVideo("k","cool vid","amazing way to spend 25min", new ArrayList<>(List.of("humour")),"url");
        assertTrue(CM.addComment(VM.getVids().get(0),"person","nice"));
        assertTrue(CM.addComment(VM.getVids().get(0),"person2","good"));

        assertEquals("nice",VM.getVids().get(0).getComments().get(0).getComment());
    }

    @Test
    public void editCommentTest(){
        Comments c = new Comments("person", "nice", "today");
        CM.editComment(c,"terrible vid");
        assertEquals("terrible vid",c.getComment());
    }
    @Test
    public void deleteCommentTest(){
        CM.deleteComment(VM.getVids().get(0),VM.getVids().get(0).getComments().get(0));
        assertTrue(VM.getVids().get(0).getComments().isEmpty());
    }
}
