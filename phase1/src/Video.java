import java.time.LocalDateTime;
import java.util.ArrayList;

public class Video implements Comparable<Video> {

    private String content; // This is the URL/Video
    private String uniqueID; // This is a unique ID for every video
    private String description; // This is the description of the video, it can be changed
    private ArrayList<String> categories; // this is the categories for the video, used to browse
    private String name; // this is the title of the video, does not have to be unique, can be changed
    private String uploader; // this is the person who creates/uploads the video
    private LocalDateTime date_upload; // this is the time of creation/upload
    private ArrayList<String> history; // datetime + description
    // private ratings

    public Video(String name,String uploader, String description,ArrayList<String> categories,String content,
                 LocalDateTime date_upload,String uniqueID, ArrayList<String> history){

        //todo When creating/uploading video, we create a uniqueID and date_upload (localdatetime.now) on creation (history included)

        this.name = name;
        this.uploader = uploader;
        this.description = description;
        this.categories = categories;
        this.content = content;
        this.date_upload = date_upload;
        this.uniqueID = uniqueID;
        this.history = history;
        //need to decide how we initialize the video construct
        //it will heavily depend on upload video in videomanager

    }

    // getters - retrieve video information


    public String getContent() {
        return content;
    }

    public String getUniqueID() {
        return uniqueID;
        //Todo check if it is wise to allow this to be public, maybe protected?
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getUploader() {
        return uploader;
    }

    public LocalDateTime getDate_upload() {
        return date_upload;
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    // Setters - We will allow users/programs change these data fields
    public void setContent(String content) {
        this.content = content;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    //todo include append and remove categories as well since setCategories only adds a predetermined arraylist to replace the current one
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    @Override
    public int compareTo(Video v) {
        int i = this.getName().compareTo(v.getName());
        if (i == 0) {
            return 0;
        }
        else if (i > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
}

