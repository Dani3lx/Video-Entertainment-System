package controllers;

import entities.User;
import usecase.NonAdminManager;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.ArrayList;

/**
 * Responsible for handling non admin users' actions.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class NonAdminHandler extends UserActionHandler {

    NonAdminManager nm;

    /**
     * Constructs a non admin handler with a record of all the users and videos.
     *
     * @param um this is the user manager which keep tracks of all the users
     * @param vm this is the video manager which keep tracks of all the videos
     */
    public NonAdminHandler(UserManager um, VideoManager vm) {
        super(um);
        nm = new NonAdminManager(um, vm);
    }

    // todo add the controller methods required for user interaction with playlist, and uploading videos and stuff
    public void uploadVideo(User currentUser,String title, String description, ArrayList<String> categories, String vidLink){
        if (description.equals("") && categories.isEmpty()){
            nm.uploadVideo(currentUser,title, vidLink);
        }else if (description.equals("") ) {
            nm.uploadVideo(currentUser, title, categories, vidLink);
        } else if (categories.isEmpty())   {
            nm.uploadVideo(currentUser,title,description,vidLink);
        }else{
            nm.uploadVideo(currentUser,title, description,categories,vidLink);
        }
    }

    public boolean deleteVideo(User user, String uniqueID){
        return nm.deleteVideo(user, uniqueID);
    }

    public void editTitle(User user, String uniqueID, String newTitle) {
        nm.editTitle(user, uniqueID, newTitle);
    }

    public void editCategories(User user, String uniqueID, ArrayList<String> newCate) {
        nm.editCategories(user, uniqueID, newCate);
    }

    public void editDescription(User user, String uniqueID, String newDes) {
        nm.editDescription(user, uniqueID, newDes);
    }
}
