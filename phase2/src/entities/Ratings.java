package entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

/**
 * THIS IS FOR PHASE 2
 */
public class Ratings {
    private HashMap<String, Boolean> ratings; // 0 = dislike, 1 = like

    public Ratings(HashMap<String, Boolean> ratings){
        this.ratings = ratings;
    } // userName, like/dislike
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

    public boolean containsRating(String userName){
        return ratings.containsKey(userName);
    }

    public void addRating(String userName, Boolean like){
        ratings.put(userName, like);
    }

    public void editRating (String userName, Boolean like){
        ratings.replace(userName, like);
    }

    public void deleteRating(String userName){
        ratings.remove(userName);
    }

    public ArrayList<String> getLikeUserName() {
        ArrayList<String> likeUserNames = new ArrayList<>();
        for (String key: ratings.keySet()){
            if (ratings.get(key).equals(true)){
                likeUserNames.add(key);
            }
        }
        return likeUserNames;
    }

    public ArrayList<String> getDislikeUserNames() {
        ArrayList<String> DislikeUserNames = new ArrayList<>();
        for (String key: ratings.keySet()){
            if (ratings.get(key).equals(false)){
                DislikeUserNames.add(key);
            }
        }
        return DislikeUserNames;
    }
//    @Override
//    public String toString(){
//        Iterator<String> usernames = likeUserName.iterator();
//        StringBuilder s = new StringBuilder();
//        while (usernames.hasNext()) {
//            s.append(usernames.next()).append("/");
//        }
//
//        return s.toString(); // don't need to include likes since the constructor sets that automatically
//    }

}
