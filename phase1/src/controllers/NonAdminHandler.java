package controllers;

import entities.User;
import usecase.NonAdminManager;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.ArrayList;

/**
 * Responsible for handling non admin users' choices.
 *
 * @author Daniel Xu, Wing Zou
 * @version 1.0
 * @since 2022-07-15
 */
public class NonAdminHandler extends UserActionHandler {

    private final NonAdminManager nm;

    /**
     * Constructs a non admin handler with a record of all the users and videos.
     *
     * @param um this is the user manager which keep tracks of all the users
     * @param vm this is the video manager which keep tracks of all the videos
     */
    public NonAdminHandler(UserManager um, VideoManager vm) {
        super(um);
        nm = new NonAdminManager(vm);
    }

    /**
     * Uploads a video with currentUser, title, description, categories and vidlink
     *
     * @param currentUser the current user
     * @param title       the title of the video
     * @param description the description of the video
     * @param categories  the categories of the video
     * @param vidLink     the video link
     */
    public boolean uploadVideo(User currentUser, String title, String description, ArrayList<String> categories, String vidLink) {
        if (title.equals("") || vidLink.equals("")){
            return false;
        } else if (categories.isEmpty()){
            nm.uploadVideo(currentUser, title, description, new ArrayList<>(), vidLink);
        } else {
            nm.uploadVideo(currentUser, title, description, categories, vidLink);
        }
        return true;
    }

    /**
     * Deletes a video using user and uniqueID
     *
     * @param user     the current user
     * @param uniqueID the video ID
     * @return whether the deletion was successful
     */
    public boolean deleteVideo(User user, String uniqueID) {
        return nm.deleteVideo(user, uniqueID);
    }

    /**
     * Edits the title of the video
     *
     * @param user     the crrent user
     * @param uniqueID the video ID
     * @param newTitle the new title
     */
    public void editTitle(User user, String uniqueID, String newTitle) {
        nm.editTitle(user, uniqueID, newTitle);
    }

    /**
     * Edits the categories of the video
     *
     * @param user     the current user
     * @param uniqueID the video ID
     * @param newCate  the new categories
     */
    public void editCategories(User user, String uniqueID, ArrayList<String> newCate) {
        nm.editCategories(user, uniqueID, newCate);
    }

    /**
     * Edits the description of the video
     *
     * @param user     the current user
     * @param uniqueID the video ID
     * @param newDes   the new description
     */
    public void editDescription(User user, String uniqueID, String newDes) {
        nm.editDescription(user, uniqueID, newDes);
    }
}
