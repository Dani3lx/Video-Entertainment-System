import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VideoManager {
    private List<Video> vids;
    private List<String> allCategories;
    // Nicholas: I am thinking should we store uniqueID instead of Video,
    // wondering if storing Video in the collection cause any problem

    public void setAllVids(List<Video> vids) {
        this.vids = vids;
    }
    //Consider storing instance of Video in User
    //format of video file, url
    //maybe format so that if uploader already in system, append vidLink to ArrayList<Video>
    //returns if video upload successful
    public boolean uploadVideo(String uploader, String title, String description, ArrayList<String> categories,String vidLink){
        if (!vids.isEmpty()){
            for (Video v : vids) {
                if (vidLink.equalsIgnoreCase(v.getContent())) {
                    return false;
                }
            }
        }
        if (!isInAllCateogries(categories)){
            return false; //TODO change this so that it can return correct which category
        }
        // TODO if vidLink exist, return false
        // TODO update uploader's user profil to add an extra video onto it
        Video v1 = new Video(uploader, title, description, categories, vidLink);
        vids.add(v1);
        return true;
    }
    // Nicholas: there is a difference in naming variables. In video class we use "name" instead of "title",
    // "content" instead of "vidLink". I think title and vidLink is better variable names, however
    // we already used name too much already in other classes.


    public boolean deleteVideo(String vidLink){
        for (Video v: vids){
            if (vidLink.equalsIgnoreCase(v.getContent())){

            }
        }
    }

    //need know what user wants to edit
    //ex.
    public boolean editVideo(String vidLink){
        for (Video v: vids){
            if (vidLink.equalsIgnoreCase(v.getContent())){

            }
        }
    }
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

}