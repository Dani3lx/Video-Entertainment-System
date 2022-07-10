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


    //return type allows for video with same title
    public List<Video> retrieveVideobyTitle(String name){
        ArrayList<Video> vid_list = new ArrayList<>();
        for (Video v: vids){
            if (name.equalsIgnoreCase(v.getName())){
                vid_list.add(v);
            }

        }
        return vid_list;
    }

    public List<Video> retrieveVideobyCategory(String category){
        ArrayList<Video> vid_list = new ArrayList<>();
        for (Video v: vids){
            for (String cate: v.getCategories()){
                if (category.equalsIgnoreCase(cate)){
                    vid_list.add(v);
                }
            }

        }
        return vid_list;
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
            if (vid.getName().contains(name)) {
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

}
