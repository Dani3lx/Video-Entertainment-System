import java.util.ArrayList;

public class NonAdminManager extends UserManager {
    private UserManager um;
    private ArrayList<User> users;

    private VideoEditor ve;

    // we need to make sure that there only exists one VideoManager object during runtime
    public NonAdminManager(UserManager um, VideoManager vm) {
        super(vm);
        this.um = um;
        users = um.getAllUsers();
        ve = new VideoEditor();
    }

    // start of overloaded uploadVideo functions region
    public boolean uploadVideo(User user, String title, String vidlink) {
        return vm.uploadVideo(user.getUserName(), title, "", new ArrayList<>(), vidlink);
    }

    public boolean uploadVideo(User user, String title, String description, String vidlink) {
        return vm.uploadVideo(user.getUserName(), title, description, new ArrayList<>(), vidlink);
    }

    public boolean uploadVideo(User user, String title, ArrayList<String> categories, String vidlink) {
        return vm.uploadVideo(user.getUserName(), title, "", categories, vidlink);
    }

    public boolean uploadVideo(User user, String title, String description, ArrayList<String> categories, String vidLink) {
        return vm.uploadVideo(user.getUserName(), title, description, categories, vidLink);
    }
    // end of overloaded uploadVideo functions region

    public boolean deleteVideo(User user, String uniqueID) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                return vm.deleteVideo(video);
            }
        }
        return false;
    }

    public void editTitle(User user, String uniqueID, String newTitle) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editTitle(video, newTitle);
            }
        }
    }

    public void editCategories(User user, String uniqueID, ArrayList<String> newCate) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editCategories(video, newCate);
            }
        }
    }

    public void editDescription(User user, String uniqueID, String newDes) {
        for (Video video : vm.getVids()) {
            if (video.getUploader().equals(user.getUserName()) && video.getUniqueID().equals(uniqueID)) {
                ve.editDescription(video, newDes);
            }
        }
    }

    // for displaying all the videos uploaded by a user
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
