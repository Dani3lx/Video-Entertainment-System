import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class VideoManager {
    private ArrayList<Video> vids;

    public VideoManager(){
        vids = new ArrayList<>();
    }

    public ArrayList<Video> getVids(){
        return vids;
    }

    public void setVids(ArrayList<Video> videos) { vids = videos; }

    /**
     *
     * @param uploader The name of the person uploading video
     * @param title The title of the video
     * @param description What the video is about
     * @param categories Categories video falls under
     * @param vidLink A link to the video
     * @return Whether or not upload was successful
     */
    public boolean uploadVideo(String uploader, String title, String description, ArrayList<String> categories,String vidLink){

        ArrayList<String> vidID = new ArrayList<>();

        if (!vids.isEmpty()){
            for (Video v : vids) {
                vidID.add(v.getUniqueID());
            }
        }


        String uniqueID = UUID.randomUUID().toString();


        while (vidID.contains(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();

        }
        ArrayList<String> ratings = new ArrayList<>();
        ratings.add("0");
        ratings.add("0");
        Video v1 = new Video(uploader, title, description, categories, vidLink, uniqueID, ratings, LocalDateTime.now().toString());
        vids.add(v1);
        return true;

    }


    /**
     *
     * @param v Video object
     * @return Whether or not deletion was successful
     */
    public boolean deleteVideo(Video v){
        vids.remove(v);
        return true;
    }

    public void likeVideo(String userName, Video v){
        v.addRatings(userName);
    }

    /**
     *
     * @param uniqueID The name of the person who uploaded video
     * @return video with correspond UniqueID
     */
    public Video getByUniqueID(String uniqueID) throws Exception {
        for (Video v : vids) {
            if (uniqueID.equalsIgnoreCase(v.getUniqueID())) {
                return v;
            }
        }
        throw new Exception("Don't find video with the corresponding uniqueID");
    }

    /**
     *
     * @param uploader The name of the person who uploaded video
     * @return All the videos the uploader has uploaded
     */
    public ArrayList<Video> getByUploader(String uploader){
        ArrayList<Video> vid_list = new ArrayList<>();
        for (Video v: vids){

            if (uploader.equalsIgnoreCase(v.getUploader())){
                vid_list.add(v);

            }

        }
        return vid_list;
    }

    /**
     *
     * @param name The title of the video
     * @return All the videos that correspond to the title
     */
    public ArrayList<Video> getByName(String name) {
        ArrayList<Video> videoList = new ArrayList<>();
        for (Video vid : vids) {
            if ((vid.getName().toLowerCase()).contains((name).toLowerCase())) {
                videoList.add(vid);
            }
        }

        return videoList;
    }

    /**
     *
     * @param categories The categories of the video
     * @return All the videos belonging to the same categories
     */
    public ArrayList<Video> getByCategory(ArrayList<String> categories) {
        ArrayList<Video> videoList = new ArrayList<>();
        for (Video vid : vids) {

            if (vid.getCategories().containsAll(categories)) {

                videoList.add(vid);
            }
        }

        return videoList;
    }

    /**
     *
     * @param videos The list of videos
     * @return Video names
     */
    public ArrayList<String> getVideoNames(ArrayList<Video> videos) {
        ArrayList<String> names = new ArrayList<>();
        for (Video vid : videos) {
            names.add(vid.getName());
        }

        return names;
    }

}
