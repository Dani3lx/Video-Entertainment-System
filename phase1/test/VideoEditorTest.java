import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

import java.lang.*;

public class VideoEditorTest {

    @Test
    public void editTitleTest() {
        VideoEditor VE = new VideoEditor();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        ArrayList<String> ratings = new ArrayList<>();
        ratings.add("0");
        ratings.add("0");
        Video v1 = new Video("K", "things", "nice", cates, "url", "ID", ratings, "today");
        VE.editTitle(v1, "new");
        assertEquals("new", v1.getName());
    }

    @Test
    public void editDescriptionTest() {
        VideoEditor VE = new VideoEditor();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("humour");
        ArrayList<String> ratings = new ArrayList<>();
        ratings.add("0");
        ratings.add("0");
        Video v1 = new Video("K", "things", "nice", cates, "url", "ID", ratings, "today");
        VE.editDescription(v1, "newdescrip");
        assertEquals("newdescrip", v1.getDescription());
    }

    @Test
    public void editCategoriesTest() {
        VideoEditor VE = new VideoEditor();
        ArrayList<String> cates = new ArrayList<>();
        cates.add("nothumour");
        ArrayList<String> ratings = new ArrayList<>();
        ratings.add("0");
        ratings.add("0");
        Video v1 = new Video("K", "things", "nice", cates, "url", "ID", ratings, "today");
        VE.editCategories(v1, cates);
        assertEquals(cates, v1.getCategories());
    }
}

