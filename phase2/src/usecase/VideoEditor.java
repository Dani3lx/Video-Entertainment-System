package usecase;

import entities.Video;

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
     * Likes the video.
     *
     * @param v target video
     */
    public void likeVideo(Video v){
        v.addLikes();
    }

    /**
     * Dislikes the video.
     *
     * @param v the target video
     */
    public void dislikeVideo(Video v){
        v.addDislikes();
    }

    /**
     * Return the video's information.
     *
     * @param vid target video
     * @return video's information
     */
    public String[] returnVideoInformation(Video vid) {
        return new String[]{vid.getName(), vid.getUploader(), vid.getDescription(), vid.getDate_upload(), vid.getContent(), vid.getRatings().get(0), vid.getRatings().get(1)};
    }
}