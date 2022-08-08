package controllers.deprecated;

import entities.User;
import entities.Video;
import usecase.runtimeDataManager.UserManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Responsible for handling all users' actions.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-21
 */
public class UserActionHandler {
    UserManager um;

    /**
     * Constructs a user action handler with a user manager.
     *
     * @param um user manager that manages all users
     */
    public UserActionHandler(UserManager um) {
        this.um = um;
    }

    /**
     * Returns a list of videos that contains name in their title.
     *
     * @param name the name of the video being searched
     * @return list of video that contains name in their title
     */
//    public ArrayList<Video> browseByName(String name) {
//        ArrayList<String> info = new ArrayList<>();
//        info.add(name);
//        return um.returnVideos(info, "name");
//    }

    /**
     * Returns a list of videos that contains all the categories.
     *
     * @param categories the categories being searched
     * @return list of video that contains all the categories
     */
//    public ArrayList<Video> browseByCategories(ArrayList<String> categories) {
//        return um.returnVideos(categories, "category");
//    }

    /**
     * Returns a list of videos uploaded by uploader.
     *
     * @param uploader the uploader being searched
     * @return list of videos uploaded by uploader
     */
//    public ArrayList<Video> browseByUploader(String uploader) {
//        ArrayList<String> info = new ArrayList<>();
//        info.add(uploader);
//        return um.returnVideos(info, "uploader");
//    }

    /**
     * Logs in and return a user.
     *
     * @param userName the username
     * @param password the password
     * @return the user with the username and password
     */
    public User loginUser(String userName, String password) {
        return um.validateUser(userName, password);
    }

    /**
     * Creates and return a user.
     *
     * @param userName the username
     * @param password the password
     * @return the user created
     */
//    public User createUser(String userName, String password) {
//
////        List<User> all_users = um.getAllUsers();
////        if (!(Objects.isNull(all_users))) {
////            for (User u : all_users) {
////                if (um.validateUserName(u, userName)) {
////                    return null;
////                }
////            }
////        }
////        User newUser = um.instantiateUser(userName, password, false);
////
////        um.updateData(newUser);
////        return newUser;
//
//        if (um.noUserExist(userName)) {
//            // Creates a new user through useCase method
//            User newUser = um.instantiateUser(userName, password, false);
//            um.updateData(newUser);
//            return newUser;
//        } else {
//            return null;
//        }
//    }

    /**
     * Updates the user's history.
     *
     * @param user the targeted user
     */
    public void updateUserHistory(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        um.updateHistory(user, LocalDateTime.now().format(formatter));
    }

    /**
     * Return whether the user is admin.
     *
     * @param user the targeted user
     * @return whether the user is admin
     */
    public boolean isAdmin(User user) {
        return um.getRole(user);
    }

    /**
     * Changes the user's password.
     *
     * @param user        the targeted user
     * @param newPassword the new password
     */
    public void changePassword(User user, String newPassword) {
        um.changePassword(user, newPassword);
    }

    /**
     * Likes or dislikes the video.
     *
     * @param v    the video being rated
     * @param like whether to like or dislike
     */
    public void rateVideo(Video v, boolean like) {
        um.rateVideo(v, like);
    }

}
