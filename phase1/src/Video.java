import java.time.LocalDateTime;
import java.util.ArrayList;

public class Video {

    private String content;
    private String uniqueID;
    private String description;
    private ArrayList<String> categories;
    private String name;
    private String uploader;
    private LocalDateTime date_upload;
    private ArrayList<String> history; // datetime + description
    // private ratings

    public Video(){
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
}

