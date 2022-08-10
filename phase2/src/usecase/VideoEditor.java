package usecase;

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
     * Like the video if the video is not originally liked by that user, delete that rating
     * for the video by that user if it is originally liked.
     *
     * @param v target video
     * @param user target user
     */
    public void likeVideo(Video v, User user){
            if (!this.currentRatingOfUser(v, user).equals(1)){
                if (v.getRatings().containsRating(user.getUserName())){
                    v.getRatings().editRating(user.getUserName(), true);
                }
                else{
                    v.getRatings().addRating(user.getUserName(), true);
                }
            }
            else{
                this.deleteRating(v, user);
            }
    }

    /**
     * Dislike the video if the video is not originally disliked by that user, delete that rating
     * for the video by that user if it is originally disliked
     *
     * @param v the target video
     * @param user the target user
     */
    public void dislikeVideo(Video v,  User user){
        if (!this.currentRatingOfUser(v, user).equals(0)){
            if (v.getRatings().containsRating(user.getUserName())){
                v.getRatings().editRating(user.getUserName(), false);
            }
            else{
                v.getRatings().addRating(user.getUserName(), false);
            }
        }
        else{
            this.deleteRating(v, user);
        }
    }

    /**
     * Delete a user's rating of a video
     *
     * @param v the target video
     * @param user the target user
     */
    public void deleteRating(Video v, User user){
        if (v.getRatings().containsRating(user.getUserName())){
            v.getRatings().deleteRating(user.getUserName());
        }
    }

    /**
     * Return an Integer representing the current state of the user, either like, dislike, or no
     * current rating of the respective user.
     *
     * @param v the target video
     * @param user the target user
     * @return Return the current rating of a user to the video. 0 represent dislike,
     *         1 represent like, 2 represent no current rating.
     */
    public Integer currentRatingOfUser(Video v, User user){
        if (v.getRatings().getRatings().containsKey(user.getUserName())){
            if (v.getRatings().getRatings().get(user.getUserName())){
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