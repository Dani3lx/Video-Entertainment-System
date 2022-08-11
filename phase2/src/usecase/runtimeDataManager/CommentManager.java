package usecase.runtimeDataManager;

import entities.Comments;
import entities.Video;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class is responsible for performing all direct interactions with the Comments entity class.
 *
 * @author Kate
 */

public class CommentManager {

    /**
     * Edit existing comment.
     * @param c comment to be edited
     * @param newComm new comment to be added
     * @return true to indicate operation was successful
     */
    public Boolean editComment(Comments c, String newComm) {
        c.setComment(newComm);
        c.setComment_date(LocalDateTime.now().toString());
        return true;

    }

    /**
     * Delete comment c from video v
     * @param v video containing comment
     * @param c comment to be deleted
     * @return true to indicate operation was successful
     */
    public Boolean deleteComment(Video v, Comments c){
        v.deleteComment(c);
        return true;
    }

    /**
     * Add new comment to video
     * @param v video for which comment is to be added
     * @param commenter username of user adding comment
     * @param comment contents of comment
     * @return true to indicate operation was successful
     */
    public Boolean addComment(Video v, String commenter, String comment){
        v.addComment(new Comments(commenter,comment, LocalDateTime.now().toString()));
        return true;
    }
}
