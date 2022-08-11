package entities;

import java.util.HashMap;

/**
 * This represents Ratings for video.
 *
 * @author Shu Fan Nicholas Au
 * @version 1.0
 * @since 2022-08-05
 */
public class Ratings {
    /**
     * This variable ratings is a HashMap. The keys are storing userName with type String. The values
     * are storing like/dislike with type boolean where true represent like and false represent dislike.
     */
    private final HashMap<String, Boolean> ratings; // 0 = dislike, 1 = like

    /**
     * Constructs a hashmap which stores content of Ratings.
     *
     * @param ratings a hashmap which stores content of Ratings.
     */
    public Ratings(HashMap<String, Boolean> ratings) {
        this.ratings = ratings;
    } // userName, like/dislike

    /**
     * Constructs a hashmap which stores content of Ratings
     */
    public Ratings() {
        this.ratings = new HashMap<>();
    }

    /**
     * Returns the content of the Ratings.
     *
     * @return the content of Ratings
     */
    public HashMap<String, Boolean> getRatings() {
        return ratings;
    }

    /**
     * Returns the number of likes for the video.
     *
     * @return the number of likes for the video.
     */
    public Integer getTotalLikes() {
        int count = 0;
        for (Boolean like : ratings.values()) {
            if (like.equals(true)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns the number of dislikes for the video.
     *
     * @return the number of dislikes for the video.
     */
    public Integer getTotalDislikes() {
        int count = 0;
        for (Boolean like : ratings.values()) {
            if (like.equals(false)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Returns whether a userName liked/disliked or didn't liked/disliked a video.
     *
     * @param userName the name of the user
     * @return boolean of weather a username liked/disliked or didn't liked/disliked a video.
     */
    public boolean containsRating(String userName) {
        return ratings.containsKey(userName);
    }

    /**
     * Return whether a userName liked/disliked or didn't liked/disliked a video.
     *
     * @param userName the name of the user
     * @param like     true represent liked, false represent disliked
     */
    public void addRating(String userName, Boolean like) {
        ratings.put(userName, like);
    }

    /**
     * Edit a rating with the respective userName.
     *
     * @param userName the name of the user
     * @param like     true represent liked, false represent disliked
     */
    public void editRating(String userName, Boolean like) {
        ratings.replace(userName, like);
    }

    /**
     * Delete a rating with the respective userName.
     *
     * @param userName the name of the user
     */
    public void deleteRating(String userName) {
        ratings.remove(userName);
    }
}
