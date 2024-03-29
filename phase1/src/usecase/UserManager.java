package usecase;

import entities.*;

import java.util.*;

/**
 * This class is responsible for performing all direct interactions with the User entity classes.
 *
 * @author Benedek Balla, Daniel Xu, ...
 * @version 1.0
 * @since 2022-07-15
 */
public class UserManager {
    VideoManager vm;
    private final VideoEditor ve = new VideoEditor();
    private ArrayList<User> users;

    /**
     * This constructs a user manager that manages user.
     *
     * @param vm video manager for managing videos
     */
    public UserManager(VideoManager vm) {
        this.vm = vm;
        users = new ArrayList<>();
    }

    /**
     * Validate and return a user.
     *
     * @param username username
     * @param password password
     * @return validated user
     */
    public User validateUser(String username, String password) {
        if (Objects.isNull(users)) {
            return null;
        } else {
            for (User user : users) {
                if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                    if (!user.getBanStatus()) {
                        return user;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Changes the user's password.
     *
     * @param user     target user
     * @param password the new password user wants to change to
     */
    public void changePassword(User user, String password) {
        user.setPassword(password);
    }

    /**
     * Returns the user login history.
     *
     * @param user target user
     * @return login history of user
     */
    public List<String> getHistory(User user) {
        List<String> history = new ArrayList<>(user.getLoginHistory());
        Collections.sort(history);
        return history;
    }

    /**
     * Updates the user's login history.
     *
     * @param user target user
     * @param date new login date to add
     */
    public void updateHistory(User user, String date) {
        user.getLoginHistory().add(date);
    }

    // Overloaded instantiateUser methods

    /**
     * Instantiate a new user with the following properties.
     *
     * @param userName    of the user
     * @param password    of the user
     * @param banStatus   of the user
     * @param history     of the user
     * @param adminStatus of the user
     * @return User object for the new user
     */
    public User instantiateUser(String userName, String password, boolean banStatus, HashSet<String> history,
                                boolean adminStatus) {
        if (adminStatus) {
            return new AdminUser(userName, password, banStatus, history);
        } else {
            return new NonAdminUser(userName, password, banStatus, history);
        }
    }

    /**
     * Instantiate a new user with the following properties.
     *
     * @param userName    of the user
     * @param password    of the user
     * @param adminStatus of the user
     * @return User object for the new user
     */
    public User instantiateUser(String userName, String password, boolean adminStatus) {
        if (adminStatus) {
            return new AdminUser(userName, password);
        } else {
            return new NonAdminUser(userName, password);
        }
    }

    /**
     * Returns whether user's name is equal to name.
     *
     * @param user target user
     * @param name name
     * @return if the username of user matches name
     */
    public boolean validateUserName(User user, String name) {
        return user.getUserName().equals(name);
    }

    /**
     * Returns user's ban status.
     *
     * @param user target user
     * @return the ban status of the user
     */
    public boolean validateBanStatus(User user) {
        return user.getBanStatus();
    }

    /**
     * Adds user to the list of all users.
     *
     * @param user target user
     */
    public void updateData(User user) {
        users.add(user);
    }

    /**
     * Return all users.
     *
     * @return all user
     */
    public ArrayList<User> getAllUsers() {
        return users;
    }


    public void setAllUsers(ArrayList<User> allUsers) {
        users = allUsers;
    }

    /**
     * Return whether user is admin.
     *
     * @param user target user
     * @return whether user is admin
     */
    public boolean getRole(User user) {
        return user.isAdminInd();
    }

    /**
     * Rates a video. If the parameter "like" is true, change the video's ratings to like if the video
     * is not originally liked by that uniqueID, or else delete that rating for the video.
     * If the parameter "dislike" is true, change the video's ratings to dislike if the video
     * is not originally disliked by that uniqueID, or else delete that rating for the video.
     *
     * @param v    target video
     * @param like whether to like the video or not
     */
    public void rateVideo(Video v, String userUniqueID, boolean like) {
        if (like) {
            if (!ve.currentRatingOfUserUniqueID(v, userUniqueID).equals(1)){
                ve.likeVideo(v, userUniqueID);
            }
            else{
                ve.deleteRating(v, userUniqueID);
            }
        } else {
            if (!ve.currentRatingOfUserUniqueID(v, userUniqueID).equals(0)){
                ve.dislikeVideo(v, userUniqueID);
            }
            else{
                ve.deleteRating(v, userUniqueID);
            }
        }
    }

    /**
     * Return list of videos.
     *
     * @param info   list of names/category/uploader
     * @param method collect videos by name/category/uploader
     * @return list of videos corresponding to the name/category/uploader
     */
    public ArrayList<Video> returnVideos(ArrayList<String> info, String method) {
        switch (method) {
            case "name":
                return vm.getByName(info.get(0));

            case "category":
                return vm.getByCategory(info);

            case "uploader":
                return vm.getByUploader(info.get(0));

            default:
                return new ArrayList<>();
        }
    }

    /**
     * Return user's username.
     *
     * @param user target user
     * @return username
     */
    public String getUserName(User user) {
        return user.getUserName();
    }
}