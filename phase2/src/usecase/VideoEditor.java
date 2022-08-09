package usecase;

import entities.Comments;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;

import java.util.ArrayList;

/**
 * This class is responsible for performing all direct interactions with the User entity classes.
 *
 * @author Daniel Xu, ...
 * @version 1.0
 * @since 2022-07-23
 */
public class VideoEditor {

    /**
     * Edits the video title.
     *
     * @param v        target video
     * @param newTitle the new title user wants their video to be
     */
    public void editTitle(Video v, String newTitle) {
        v.setName(newTitle);
    }

    /**
     * Edits the categories.
     *
     * @param v       target video
     * @param newCate the new categories that user wants their video to be
     */
    public void editCategories(Video v, ArrayList<String> newCate) {
        v.setCategories(newCate);
    }

    /**
     * @param v      target user
     * @param newDes the new description user wants their video to be
     */
    public void editDescription(Video v, String newDes) {
        v.setDescription(newDes);
    }

    /**
     * Likes the video if the user hasn't liked it already.
     * @param user user who likes video
     * @param v target video
     */
    public void likeVideo(Video v, String user) {
        ArrayList<String> usernames = v.getRatingUsers();
        for (String username : usernames) {
            if (user.equals(username)) {
                return;
            }
        }
        v.addLikes(user);
    }

//    /**
//     * Dislikes the video.
//     *
//     * @param v the target video
//     */
//    public void dislikeVideo(Video v) {
//        v.addDislikes();
//    }

    public void editComment(Comments c, String newComm) {
        c.setComment(newComm);
    }

    /**
     * Return the video's information.
     *
     * @param vid target video
     * @return video's information
     */
    public String[] returnVideoInformation(Video vid, LanguagePresenter lp) {
        return new String[]{lp.getVideoDataText(LanguagePresenter.VideoDataType.TITLE) + vid.getName(),
                lp.getVideoDataText(LanguagePresenter.VideoDataType.UPLOADER) + vid.getUploader(),
                lp.getVideoDataText(LanguagePresenter.VideoDataType.DESCRIPTION) + vid.getDescription(),
                lp.getVideoDataText(LanguagePresenter.VideoDataType.DATEUPLOADED) + vid.getDate_upload(),
                lp.getVideoDataText(LanguagePresenter.VideoDataType.CONTENT) + vid.getContent(),
                lp.getVideoDataText(LanguagePresenter.VideoDataType.LIKES) + Integer.toString(vid.getRating()),
                lp.getVideoDataText(LanguagePresenter.VideoDataType.DISLIKES) + "0"};
    }
}