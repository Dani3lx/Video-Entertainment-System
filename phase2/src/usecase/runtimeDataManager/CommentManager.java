package usecase.runtimeDataManager;

import entities.Comments;
import entities.Video;

import java.util.ArrayList;

public class CommentManager {

    private static CommentManager instance;

    public static CommentManager getInstance(){
        if (instance == null)
            instance = new CommentManager();
        return instance;
    }

    public Boolean deleteComment(Video v, Comments c){
        ArrayList<Comments> coms = v.getComments();
        coms.remove(c);
        return true;
    }
}
