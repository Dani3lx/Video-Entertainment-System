package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * This represents video.
 */
public class Video implements Comparable<Video> {
    private final String content;
    private final String uniqueID;
    private final String uploader;
    private final String date_upload;
    private final Ratings ratings;
    private String description;
    private ArrayList<String> categories;
    private String name;
    private ArrayList<Comments> comments;

    /**
     * Constructs a video with uploader, name, description, categories, content, uniqueID, ratings and date_upload.
     *
     * @param uploader    the uploader of the video
     * @param name        the name of the video
     * @param description the description of the video
     * @param categories  the categories of the video
     * @param content     the content of the video
     * @param uniqueID    the unique ID for the video
     * @param ratings     the ratings for the video
     * @param date_upload the date the video is uploaded
     */
    public Video(String uploader, String name, String description, ArrayList<String> categories, String content, String uniqueID,
                 Ratings ratings, String date_upload, ArrayList<Comments> comments) {
        this.name = name;
        this.uploader = uploader;
        this.content = content;
        this.date_upload = date_upload;

        this.description = description;
        this.categories = categories;
        this.uniqueID = uniqueID;
        this.ratings = ratings;
        this.comments = comments;
    }

    /**
     * Returns the content of the video.
     *
     * @return the content of the video
     */
    public String getContent() {
        return content;
    }

    /**
     * Return the video's rating.
     *
     * @return video rating
     */
    public Ratings getRatings() {
        return ratings;
    }

    /**
     * Return the uniqueID.
     *
     * @return the uniqueID
     */
    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * Return the video description.
     *
     * @return the video description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the video.
     *
     * @param description the description of the video
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the video name.
     *
     * @return video name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the video.
     *
     * @param name the name of the video
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the uploader.
     *
     * @return the uploader
     */
    public String getUploader() {
        return uploader;
    }

    /**
     * Return the date the video is uploaded.
     *
     * @return the date the video is uploaded.
     */
    public String getDate_upload() {
        return date_upload;
    }

    /**
     * Return the video categories.
     *
     * @return the video categories
     */
    public ArrayList<String> getCategories() {
        return categories;
    }

    /**
     * Sets the categories of the video.
     *
     * @param categories the video's categories
     */
    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    /**
     * Return the whole list of comments for the video.
     *
     * @return the whole list of comments for the video
     */
    public ArrayList<Comments> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comments> comments) {
        this.comments = comments;
    }

    /**
     * Add new comment to comments of this video.
     *
     * @param comment to be added
     */
    public void addComment(Comments comment) {
        this.comments.add(comment);
    }

    /**
     * Remove comment from comments of this video.
     *
     * @param comment to be removed
     */
    public void deleteComment(Comments comment) {
        this.comments.remove(comment);
    }

    /**
     * Return whether v is equal to this video.
     *
     * @param v the target video
     * @return whether v is equal to this video
     */
    public boolean equals(Video v) {
        return (v.getUploader().equals(this.getUploader()) && v.getName().equals(this.getName()) && v.getContent().equals(this.getContent()) &&
                v.getCategories().equals(this.getCategories()) && v.getUniqueID().equals(this.getUniqueID()) && v.getDescription().equals(this.description) &&
                v.getDate_upload().equals(this.date_upload) && v.getRatings().equals(this.getRatings()));
    }

    /**
     * Compares v to this video and returns the result.
     *
     * @param v the video being compared
     * @return the result of v comparing with this video
     */
    @Override
    public int compareTo(Video v) {
        int i = this.getName().compareTo(v.getName());
        return Integer.compare(i, 0);
    }

    /**
     * Returns the string representation of the video.
     *
     * @return the string representation of the video
     */
    @Override
    public String toString() {
        Iterator<String> it1 = categories.iterator();
        StringBuilder s1 = new StringBuilder();
        while (it1.hasNext()) {
            s1.append(it1.next()).append("/");
        }

        StringBuilder s2 = new StringBuilder();
        for (Map.Entry<String, Boolean> set : ratings.getRatings().entrySet()) {
            s2.append(set.getKey()).append("=").append(set.getValue()).append("/");
        }

        Iterator<Comments> it2 = comments.iterator();
        StringBuilder s3 = new StringBuilder();
        while (it2.hasNext()) {
            s3.append(it2.next()).append("/");
        }

        return this.getUploader() + "," + this.getName() + "," + this.getDescription() + "," +
                s1 + "," + this.getContent() + "," + this.getUniqueID() + "," + s2 + "," + this.getDate_upload() + "," + s3;
    }
}

