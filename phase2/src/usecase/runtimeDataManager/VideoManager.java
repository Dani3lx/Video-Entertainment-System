package usecase.runtimeDataManager;

import entities.Comments;
import entities.Ratings;
import entities.Video;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Use case class responsible for interacting with the Video entity class
 *
 * @author Kate Ma, Daniel
 * @version 1.0
 * @since 2022-07-23
 */

public class VideoManager{
    private ArrayList<Video> vids;
    private static VideoManager instance;

    /**
     * Instantiate new VideoManager if not yet created, or return instance
     * @return VideoManager if it already exists
     */
    public static VideoManager getInstance(){
        if (instance == null)
            instance = new VideoManager();
        return instance;
    }

    /**
     * Instantiate new VideoManager
     */
    public VideoManager(){
        vids = new ArrayList<>();
    }

    /**
     * Return all videos.
     *
     * @return all videos
     */
    public ArrayList<Video> getVids() {
        return vids;
    }

    /**
     * Set all videos.
     *
     * @param videos a list of videos
     */
    public void setVids(ArrayList<Video> videos) {
        vids = videos;
    }

    /**
     * Upload a video with the following properties.
     *
     * @param uploader    the name of the person uploading video
     * @param title       the title of the video
     * @param description what the video is about
     * @param categories  categories video falls under
     * @param vidLink     a link to the video
     */
    public void uploadVideo(String uploader, String title, String description, ArrayList<String> categories, String vidLink) {
        ArrayList<String> vidID = new ArrayList<>();
        if (!vids.isEmpty()) {
            for (Video v : vids) {
                vidID.add(v.getUniqueID());
            }
        }
        String uniqueID = UUID.randomUUID().toString();
        while (vidID.contains(uniqueID)) {
            uniqueID = UUID.randomUUID().toString();

        }
        Ratings ratings = new Ratings();
        ArrayList<Comments> comments = new ArrayList<>();
        Comments c = new Comments("","","");
        comments.add(c);
        Video v1 = new Video(uploader, title, description, categories, vidLink, uniqueID, ratings, LocalDateTime.now().toString(), comments);
        vids.add(v1);
    }

    /**
     * Delete a video.
     *
     * @param v target video
     * @return whether deletion was successful
     */
    public boolean deleteVideo(Video v) {
        vids.remove(v);
        return true;
    }

    /**
     * Return a video by uniqueID.
     *
     * @param uniqueID the uniqueID of the video
     * @return video with correspond UniqueID
     */
    public Video getByUniqueID(String uniqueID) {
        for (Video v : vids) {
            if (uniqueID.equalsIgnoreCase(v.getUniqueID())) {
                return v;
            }
        }
        return null;
    }

    /**
     * Return a list of videos uploaded by uploader.
     *
     * @param uploader the name of the person who uploaded video
     * @return all the videos the uploader has uploaded
     */
    public ArrayList<Video> getByUploader(String uploader) {
        ArrayList<Video> vid_list = new ArrayList<>();
        for (Video v : vids) {

            if (uploader.equalsIgnoreCase(v.getUploader())) {
                vid_list.add(v);

            }

        }
        return vid_list;
    }

    /**
     * Return a list of videos that contains name in the title.
     *
     * @param name the title of the video
     * @return all the videos that correspond to the title
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
     * Return a list of videos with the following categories.
     *
     * @param categories the categories of the video
     * @return all the videos belonging to the same categories
     */
    public List<Video> getByCategory(List<String> categories) {
        ArrayList<Video> videoList = new ArrayList<>();
        for (Video vid : vids) {

            if (vid.getCategories().containsAll(categories)) {

                videoList.add(vid);
            }
        }

        return videoList;
    }

    /**
     * Return a list of video names.
     *
     * @param videos the list of videos
     * @return list of video names
     */
    public List<String> getVideoNames(List<Video> videos) {
        ArrayList<String> names = new ArrayList<>();
        for (Video vid : videos) {
            names.add(vid.getName());
        }

        return names;
    }

    public List<String> getVideoComments(Video vid){
        ArrayList<String> comments = new ArrayList<>();
        for (Comments c: vid.getComments()){
            comments.add(c.toString());
        }
        return comments;
    }

}
