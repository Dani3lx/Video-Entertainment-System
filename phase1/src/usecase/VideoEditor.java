package usecase;

import entities.Video;

import java.util.ArrayList;

public class VideoEditor {

    /**
     * @param v        entities.Video object
     * @param newTitle The new title user wants their video to be
     */
    public void editTitle(Video v, String newTitle) {
        v.setName(newTitle);
    }

    /**
     * @param v       entities.Video object
     * @param newCate The new categories user wants their video to be
     */
    public void editCategories(Video v, ArrayList<String> newCate) {
        v.setCategories(newCate);
    }

    /**
     * @param v      entities.Video object
     * @param newDes The new description user wants their video to be
     */
    public void editDescription(Video v, String newDes) {
        v.setDescription(newDes);
    }

    public void likeVideo(Video v){
        v.addLikes();
    }

    public void dislikeVideo(Video v){
        v.addDislikes();
    }


    /**
     * @param vid entities.Video object
     * @return All attributes of the video
     */
    public String[] returnVideoInformation(Video vid) {
        return new String[]{vid.getName(), vid.getUploader(), vid.getDescription(), vid.getDate_upload(), vid.getContent(), vid.getRatings().get(0), vid.getRatings().get(1)};
    }
}