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
     * Likes the video
     *
     * @param v target video
     */
    public void likeVideo(Video v, String userUniqueID){
        if (v.getRatings().containsRating(userUniqueID)){
            v.getRatings().editRating(userUniqueID, true);
        }
        else{
            v.getRatings().addRating(userUniqueID, true);
        }
    }

    /**
     * Dislikes the video.
     *
     * @param v the target video
     */
    public void dislikeVideo(Video v,  String userUniqueID){
        if (v.getRatings().containsRating(userUniqueID)){
            v.getRatings().editRating(userUniqueID, false);
        }
        else{
            v.getRatings().addRating(userUniqueID, false);
        }
    }

    public void deleteRating(Video v, String userUniqueID){
        if (v.getRatings().containsRating(userUniqueID)){
            v.getRatings().deleteRating(userUniqueID);
        }
    }

    // return current Rating of the uniqueID. 0 = dislike, 1 = like, 2 = null
    public Integer currentRatingOfUserUniqueID(Video v, String userUniqueID){
        if (v.getRatings().getRatings().containsKey(userUniqueID)){
            if (v.getRatings().getRatings().get(userUniqueID)){
                return 1;
            }
            else{
                return 0;
            }
        }
        else{
            return 2;
        }
    }
    public void getTotalLikes(Video v){
        v.getRatings().getTotalLikes();
    }

    public void getTotalDislikes(Video v){
        v.getRatings().getTotalDislikes();
    }

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
                lp.getVideoDataText(LanguagePresenter.VideoDataType.LIKES) + vid.getRatings().getTotalLikes().toString(),
                lp.getVideoDataText(LanguagePresenter.VideoDataType.DISLIKES) + vid.getRatings().getTotalDislikes().toString()};
    }
}