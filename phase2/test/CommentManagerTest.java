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
    private static final Ratings r = new Ratings();
    private static final Video v = new Video("k", "vid", "", new ArrayList<>(), "url", "unique",
            r, "today",new ArrayList<Comments>(List.of(new Comments("", "", ""))));
    @BeforeClass
    public static void addCommentSetup(){

//        VM.getVids().add(new Video("k", "vid", "", new ArrayList<>(), "url", VM.getVids().get(0).getUniqueID(),
//                r, VM.getVids().get(0).getDate_upload(),new ArrayList<Comments>(List.of(new Comments("", "", "")))));
//        VM.uploadVideo("k","cool vid","amazing way to spend 25min", new ArrayList<>(List.of("humour")),"url");
        assertTrue(CM.addComment(v,"person","nice"));
        assertTrue(CM.addComment(v,"person2","good"));
//        System.out.println(VM.getVids().get(0).getComments().get(0).getComment());

        assertEquals("nice",v.getComments().get(1).getComment());
    }

    @Test
    public void editCommentTest(){
        Comments c = new Comments("person", "nice", "today");
        CM.editComment(c,"terrible vid");
        assertEquals("terrible vid",c.getComment());
    }
    @Test
    public void deleteCommentTest(){
        CM.deleteComment(v,v.getComments().get(0));
        assertEquals("person",v.getComments().get(0).getCommenter());
        assertEquals("person2",v.getComments().get(1).getCommenter());

    }
}
