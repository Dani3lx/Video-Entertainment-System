import java.util.ArrayList;

public class NonAdminManager extends UserManager{
    private UserManager um;
    private VideoManager vm;

    private ArrayList<User> users;

    // we need to make sure that there only exists one VideoManager object during runtime
    public NonAdminManager(UserManager um, VideoManager vm){
        this.um = um;
        this.vm = vm;
        users = um.getAllUsers();
    }

    // start of overloaded uploadVideo functions region
    public boolean uploadVideo(User user, String title, String vidlink){
        return vm.uploadVideo(user.getUserName(), title, "", new ArrayList<>(), vidlink);
    }

    public boolean uploadVideo(User user, String title, String description, String vidlink){
        return vm.uploadVideo(user.getUserName(), title, description, new ArrayList<>(), vidlink);
    }

    public boolean uploadVideo(User user, String title, ArrayList<String> categories, String vidlink){
        return vm.uploadVideo(user.getUserName(), title, "", categories, vidlink);
    }

    public boolean uploadVideo(User user, String title, String description, ArrayList<String> categories,String vidLink){
        return vm.uploadVideo(user.getUserName(), title, description, categories, vidLink);
    }
    // end of overloaded uploadVideo functions region

    // todo: how will the user get the uniqueID of the video?
    public boolean deleteVideo(User user, String uniqueID) {
        return vm.deleteVideo(user.getUserName(), uniqueID);
    }

}
