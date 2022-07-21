package usecase;

import entities.User;
import entities.Video;

import java.util.ArrayList;

public class NonAdminManager extends UserManager {
    private UserManager um;
    private ArrayList<User> users;

    private final VideoEditor ve;

    public NonAdminManager(UserManager um, VideoManager vm) {
        super(vm);
        this.um = um;
        users = um.getAllUsers();
        ve = new VideoEditor();
    }

    // start of overloaded uploadVideo functions region

    /**
     * @param user    of type User
     * @param title   name of video being uploaded
     * @param vidlink link to the video
     */
    public void uploadVideo(User user, String title, String vidlink) {
        vm.uploadVideo(user.getUserName(), title, "", new ArrayList<>(), vidlink);
    }

    /**
     * @param user        of type User
     * @param title       name of video being uploaded
     * @param description what the video is about
     * @param vidlink     link to the video
     */
    public void uploadVideo(User user, String title, String description, String vidlink) {
        vm.uploadVideo(user.getUserName(), title, description, new ArrayList<>(), vidlink);
    }

    /**
     * @param user       of type User
     * @param title      name of the video being uploaded
     * @param categories what genres the video belongs to
     * @param vidlink    link to the video
     */
    public void uploadVideo(User user, String title, ArrayList<String> categories, String vidlink) {
        vm.uploadVideo(user.getUserName(), title, "", categories, vidlink);
    }

    /**
     * @param user        of type User
     * @param title       name of the video being uploaded
     * @param description what the video is about
     * @param categories  what genres the video belongs to
     * @param vidLink     link to the video
     */
    public void uploadVideo(User user, String title, String description, ArrayList<String> categories, String vidLink) {
        vm.uploadVideo(user.getUserName(), title, description, categories, vidLink);
    }
    // end of overloaded uploadVideo functions region

    /**
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
     * @param user     of type User
     * @param uniqueID identifier of the video
     * @param newTitle the title user wants to change to
     */
    public void editTitle(User user, String uniqueID, String newTitle) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editTitle(video, newTitle);
            }
        }
    }

    /**
     * @param user     of type User
     * @param uniqueID identifier of the video
     * @param newCate  genres the user wants to change to
     */
    public void editCategories(User user, String uniqueID, ArrayList<String> newCate) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editCategories(video, newCate);
            }
        }
    }

    /**
     * @param user     of type User
     * @param uniqueID identifier of the video
     * @param newDes   description the user wants to change to
     */
    public void editDescription(User user, String uniqueID, String newDes) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editDescription(video, newDes);
            }
        }
    }

    /**
     * Collects all the videos the user has uploaded
     *
     * @param user of type User
     * @param vids collection of all the videos uploaded
     * @return the title and ID of all the videos uploaded by user
     */
    public ArrayList<String> displayAllVideos(User user, ArrayList<Video> vids) {
        ArrayList<String> newList = new ArrayList<>();
        for (Video video : vids) {
            if (user.getUserName().equals(video.getUploader())) {
                newList.add("Title: " + video.getName() + " (ID: " + video.getUniqueID() + ")");
            }
        }
        return newList;
    }
}
