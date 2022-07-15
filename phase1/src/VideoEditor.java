import java.util.ArrayList;

public class VideoEditor {

    /**
     * @param v        Video object
     * @param newTitle The new title user wants their video to be
     */
    public void editTitle(Video v, String newTitle) {
        v.setName(newTitle);
    }

    /**
     * @param v       Video object
     * @param newCate The new categories user wants their video to be
     */
    public void editCategories(Video v, ArrayList<String> newCate) {
        v.setCategories(newCate);
    }

    /**
     * @param v      Video object
     * @param newDes The new description user wants their video to be
     */
    public void editDescription(Video v, String newDes) {
        v.setDescription(newDes);
    }


    /**
     * @param vid Video object
     * @return All attributes of the video
     */
    public String[] returnVideoInformation(Video vid) {
        return new String[]{vid.getName(), vid.getUploader(), vid.getDescription(), vid.getDate_upload(), vid.getContent()};
    }
}