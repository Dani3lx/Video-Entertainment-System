import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class VideoManager {
    private List<Video> vids;
    private List<String> allCategories;
    public void setAllVids(List<Video> vids) {
        this.vids = vids;
    }

    //format of video file, url
    //maybe format so that if uploader already in system, append vidLink to ArrayList<Video>
    //returns if video upload successful
    public boolean uploadVideo(String uploader, String title, String description, ArrayList<String> categories,String vidLink){
        ArrayList<String> vidID = new ArrayList<>();
        for(Video v: vids){
            vidID.add(v.getUniqueID());
        }

        String uniqueID = UUID.randomUUID().toString();


        while(vidID.contains(uniqueID)){
            uniqueID = UUID.randomUUID().toString();

        }
        String timeOfUpload = LocalDateTime.now().toString();
        ArrayList<Integer> ratings = new ArrayList<>();
        ratings.add(0);
        ratings.add(0);
        Video v1 = new Video(uploader, title, description, categories, vidLink, uniqueID, ratings, timeOfUpload);
        vids.add(v1);
        return true;
//        if (!isInAllCateogries(categories)){
//            return false; //TODO change this so that it can return correct which category
//        }

        // TODO update uploader's user profile to add an extra video onto it
//        Video v1 = new Video(uploader, title, description, categories, vidLink, vidLink); // TODO change uniqueID later
//        vids.add(v1);
//        return true;
    }

    //delete everything but uploader, same as how can create Youtube account but no vids
    //returns deletion successful
    public boolean deleteVideo(String vidLink){
        if (!vids.isEmpty()){
            for (Video v: vids){
                if (vidLink.equalsIgnoreCase(v.getContent())){
                    v.setCategories(null);
                    v.setName(null);
                    v.setDescription(null);
                    return true;
                }
            }
        }
        return false;
    }

    //need know what user wants to edit-title, description,categories
    //controller prompts user for t/f to change title, description, categories, need this order
    //Todo: get rid of the casting

    public List<String> getAllCategories(){
        return allCategories;
    }

    // https://stackoverflow.com/questions/22461470/if-x-in-array-in-java
    public Boolean isInAllCateogries(ArrayList<String> categories){
        for (String category: categories){
            if (!Arrays.asList(allCategories).contains(category)){
                 return false;
            }
        }
        return true;
    }

    public void editTitle(Video v, String newTitle){
        v.setName(newTitle);

    }

    public void editCategories(Video v, ArrayList<String> newCate){
        v.setCategories(newCate);
    }

    public void editDescription(Video v, String newDes){
        v.setDescription(newDes);
    }
    public ArrayList<Video> getByUploader(String uploader){
        ArrayList<Video> vid_list = new ArrayList<>();
        for (Video v: vids){

            if (uploader.equalsIgnoreCase(v.getUploader())){
                vid_list.add(v);

            }

        }
        return vid_list;
    }

    public ArrayList<Video> getByName(String name) {
        ArrayList<Video> videoList = new ArrayList<>();
        for (Video vid : vids) {
            if ((vid.getName().toLowerCase()).contains((name).toLowerCase())) {
                videoList.add(vid);
            }
        }

        return videoList;
    }

    public ArrayList<Video> getByCategory(ArrayList<String> categories) {
        ArrayList<Video> videoList = new ArrayList<>();
        for (Video vid : vids) {
            if (vid.getCategories().containsAll(categories)) {
                videoList.add(vid);
            }
        }

        return videoList;
    }

    public ArrayList<String> getVideoNames(ArrayList<Video> videos) {
        ArrayList<String> names = new ArrayList<>();
        for (Video vid : videos) {
            names.add(vid.getName());
        }

        return names;
    }

    public String[] displayVideo(Video vid) {
        return new String[]{vid.getName(), vid.getUploader(), vid.getDescription(), vid.getDate_upload(), vid.getContent()};
    }

}
