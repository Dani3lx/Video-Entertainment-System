package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * THIS IS FOR PHASE 2
 */
public class Ratings {
    private HashMap<String, Boolean> ratings; // 0 = dislike, 1 = like

    public Ratings(HashMap<String, Boolean> ratings){
        this.ratings = ratings;
    } // userUniqueID, like/dislike
    public Ratings(){
        this.ratings = new HashMap<>(ratings);
    }

    public HashMap<String, Boolean> getRatings() {
        return ratings;
    }

    public Integer getTotalLikes() {
        int count = 0;
        for (Boolean like: ratings.values()){
            if (like.equals(true)){
                count++;
            }
        }
        return count;
    }

    public Integer getTotalDislikes(){
        int count = 0;
        for (Boolean like: ratings.values()){
            if (like.equals(false)){
                count++;
            }
        }
        return count;
    }
    public boolean containsRating(String userUniqueID){
        return ratings.containsKey(userUniqueID);
    }

    public void addRating(String userUniqueID, Boolean like){
        ratings.put(userUniqueID, like);
    }

    public void editRating (String userUniqueID, Boolean like){
            ratings.replace(userUniqueID, like);
    }

    public void deleteRating(String userUniqueID){
        ratings.remove(userUniqueID);
    }

    public ArrayList<String> getLikeUserUniqueIDs() {
        ArrayList<String> likeUserUniqueIDs = new ArrayList<>();
        for (String key: ratings.keySet()){
            if (ratings.get(key).equals(true)){
                likeUserUniqueIDs.add(key);
            }
        }
        return likeUserUniqueIDs;
    }
    public ArrayList<String> getDislikeUserUniqueIDs() {
        ArrayList<String> DislikeUserUniqueIDs = new ArrayList<>();
        for (String key: ratings.keySet()){
            if (ratings.get(key).equals(false)){
                DislikeUserUniqueIDs.add(key);
            }
        }
        return DislikeUserUniqueIDs;
    }

}
