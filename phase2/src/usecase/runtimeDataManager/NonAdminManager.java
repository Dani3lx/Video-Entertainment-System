package usecase.runtimeDataManager;

import entities.Comments;
import entities.User;
import entities.Video;
import usecase.VideoEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for specific actions that only pertain to non-admin users.
 *
 * @author Wing Zou
 * @version 1.0
 * @since 2022-07-15
 */
public class NonAdminManager extends UserManager {
    private final VideoEditor ve;
    private final CommentManager CM;

    /**
     * Responsible for managing non admin users
     *
     // * @param vm the video manager that manages videos
     */
    public NonAdminManager() {
        super();
        ve = new VideoEditor();
        CM = new CommentManager();
    }

    /**
     * Uploads a video with user, title, description, categories and vidlink
     *
     * @param user        of type User
     * @param title       name of the video being uploaded
     * @param description what the video is about
     * @param categories  what genres the video belongs to
     * @param vidLink     link to the video
     */
    public boolean uploadVideo(User user, String title, String description, List<String> categories, String vidLink) {
        ArrayList<String> cat = new ArrayList<>(categories);
        if (title.equals("") || vidLink.equals("")){
            return false;
        } else if (categories.isEmpty()){
            vm.uploadVideo(user.getUserName(), title, description, new ArrayList<>(), vidLink);
        } else {
            vm.uploadVideo(user.getUserName(), title, description, cat, vidLink);
        }
        return true;
    }

    /**
     * Deletes a video with user and uniqueID
     *
     * @param user     of type User
     * @param uniqueID identifier of the video
     * @return if deletion was successful or not
     */
    public boolean deleteVideo(User user, String uniqueID) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                return vm.deleteVideo(video);
            }
        }
        return false;
    }

    /**
     * Edits the title of the video with uniqueID and user to newTitle
     *
     * @param user     of type User
     * @param uniqueID identifier of the video
     * @param newTitle the title user wants to change to
     */
    public boolean editTitle(User user, String uniqueID, String newTitle) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editTitle(video, newTitle);
                return true;
            }
        }
        return false;
    }

    /**
     * Edits the categories of the video with uniqueID and user to newCate
     *
     * @param user     of type User
     * @param uniqueID identifier of the video
     * @param categories  categories the user wants to change to
     */
    public Boolean editCategories(User user, String uniqueID, List<String> categories) {
        ArrayList<String> newCate = new ArrayList<>(categories);
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editCategories(video, newCate);
                return true;
            }
        }
        return false;
    }

    /**
     * Edits the description of the video with uniqueID and user to newDes
     *
     * @param user     of type User
     * @param uniqueID identifier of the video
     * @param newDes   description the user wants to change to
     */
    public Boolean editDescription(User user, String uniqueID, String newDes) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editDescription(video, newDes);
                return true;
            }
        }
        return false;
    }

    /**
     * Edit comments by a user of a video accessed through its uniqueID
     * @param uniqueID of video to be edited
     * @param user accessing the video
     * @param newComm contents of new comment
     * @return boolean if the operation was successful
     */
    public Boolean editComment(String uniqueID, User user, String newComm) {
        ArrayList<Comments> comments = new ArrayList<>(vm.getByUniqueID(uniqueID).getComments());
        for (Comments c : comments) {
            if (c.getCommenter().equals(user.getUserName())) {
                return CM.editComment(c,newComm);

            }
        }
        return false;
    }

    /**
     * Delete comments by user of specific video accessed through uniqueID
     * @param uniqueID of video
     * @param user accessing video
     * @return boolean if the operation was successful
     */
    public Boolean deleteComment(String uniqueID, User user){

        ArrayList<Comments> comments = new ArrayList<>(vm.getByUniqueID(uniqueID).getComments());
        for (Comments c: comments){
            if (c.getCommenter().equals(user.getUserName())){
                return CM.deleteComment(vm.getByUniqueID(uniqueID), c);
            }
        }

        return false;
    }

    /**
     * Add comment by user to video corresponding to uniqueID
     * @param uniqueID of video
     * @param user user adding comment
     * @param comment contents of the comment to be added
     * @return boolean if the operation was successful
     */
    public Boolean addComment(String uniqueID, User user, String comment){
        ArrayList<Video> vids = new ArrayList<>(vm.getVids());
        for (Video v: vids){
            if (v.getUniqueID().equals(uniqueID)){
                return CM.addComment(v,user.getUserName(),comment);
            }
        }
        return false;
    }

    /**
     * Collects all the videos the user has uploaded
     *
     * @param user of type User
     * @param vids collection of all the videos uploaded
     * @return the title and ID of all the videos uploaded by user
     */
    public ArrayList<String> ReturnUserVideos(User user, ArrayList<Video> vids) {
        ArrayList<String> newList = new ArrayList<>();
        for (Video video : vids) {
            if (user.getUserName().equals(video.getUploader())) {
                newList.add("Title: " + video.getName() + " (ID: " + video.getUniqueID() + ")");
            }
        }
        return newList;
    }
}
