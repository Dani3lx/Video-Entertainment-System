import java.util.ArrayList;
import java.util.List;

public class VideoManager {
    private List<Video> vids;



    public void setAllVids(List<Video> vids) {
        this.vids = vids;
    }
    //Consider storing instance of Video in User
    //format of video file, url
    //maybe format so that if uploader already in system, append vidLink to ArrayList<Video>
    //returns if video upload successful
    public boolean uploadVideo(String uploader, String title, String description, ArrayList<String> categories,String vidLink){
        for (Video v:vids){
            if(vidLink.equalsIgnoreCase(v.getContent())){
                return false;
            }
        }

        Video v1 = new Video(uploader, title, description, categories, vidLink);
        return true;
    }


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


    public List<Video> retrieveVideobyUploader(String uploader){
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
