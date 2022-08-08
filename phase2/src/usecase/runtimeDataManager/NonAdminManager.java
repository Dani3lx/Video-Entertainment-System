package usecase.runtimeDataManager;

import entities.User;
import entities.Video;
import usecase.VideoEditor;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Responsible for specific actions that only pertain to non-admin users.
 *
 * @author Wing Zou
 * @version 1.0
 * @since 2022-07-15
 */
public class NonAdminManager extends UserManager {
    private final VideoEditor ve;

    /**
     * Responsible for managing non admin users
     *
     * @param vm the video manager that manages videos
     */
    public NonAdminManager(VideoManager vm) {
        super(vm);
        ve = new VideoEditor();
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
    public void uploadVideo(User user, String title, String description, ArrayList<String> categories, String vidLink) {
        vm.uploadVideo(user.getUserName(), title, description, categories, vidLink);
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
     * @param newCate  genres the user wants to change to
     */
    public Boolean editCategories(User user, String uniqueID, String categories) {
        ArrayList<String> newCate = new ArrayList<>(Arrays.asList(categories.split(",")));
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
