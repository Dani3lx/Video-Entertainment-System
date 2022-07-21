package usecase;

import entities.*;

import java.util.*;

/**
 * This class is responsible for performing all direct interactions with the User entity classes.
 * @author Benedek Balla, ...
 */
public class UserManager {
    VideoManager vm;
    private final VideoEditor ve = new VideoEditor();

    public UserManager(VideoManager vm) {
        this.vm = vm;
        users = new ArrayList<>();

    }

    private ArrayList<User> users;

    /**
     * @param username of the user
     * @param password of the user
     * @return the entire User object of the user
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
     * @param user     of type User
     * @param password the new password user wants to change to
     */
    public void changePassword(User user, String password) {
        user.setPassword(password);
    }

    /**
     * @param user of type User
     * @return login history of user
     */
    public List<String> getHistory(User user) {
        List<String> history = new ArrayList<>(user.getLoginHistory());
        Collections.sort(history);
        return history;
    }

    /**
     * @param user of type User
     * @param date new login date to add
     */
    public void updateHistory(User user, String date) {
        user.getLoginHistory().add(date);
    }

    // Overloaded instantiateUser methods

    /**
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
     * @param user of type User
     * @param name of the user
     * @return if the username of user matches name
     */
    public boolean validateUserName(User user, String name) {
        return user.getUserName().equals(name);
    }

    /**
     * @param user of type User
     * @return the ban status of the user
     */
    public boolean validateBanStatus(User user) {
        return user.getBanStatus();
    }

    /**
     * Adds user to the list of all users
     *
     * @param user of type User
     */
    public void updateData(User user) {
        users.add(user);
    }

    public ArrayList<User> getAllUsers() {
        return users;
    }


    public void setAllUsers(ArrayList<User> allUsers) {
        users = allUsers;
    }

    public boolean getRole(User user) {
        return user.isAdminInd();
    }

    /**
     * @param v    Video object
     * @param like if want to like the video or not
     */
    public void rateVideo(Video v, boolean like) {
        if (like) {
            ve.likeVideo(v);
        } else {
            ve.dislikeVideo(v);
        }
    }

    /**
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

    public String getUserName(User user){
        return user.getUserName();
    }
}