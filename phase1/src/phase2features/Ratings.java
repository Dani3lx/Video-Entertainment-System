package phase2features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * THIS IS FOR PHASE 2
 */
public class Ratings {
    private HashMap<String, Integer> userNames; // 0 = dislike, 1 = like

    public Ratings(HashMap<String, Integer> userNames){
        this.userNames = userNames;
    }
    public Ratings(){
        this.userNames = new HashMap<>(userNames);
    }

    public Integer getTotalLikes() {
        int count = 0;
        for (Integer value: userNames.values()){
            if (value.equals(1)){
                count++;
            }
        }
        return count;
    }

    public Integer getTotalDislikes(){
        int count = 0;
        for (Integer value: userNames.values()){
            if (value.equals(0)){
                count++;
            }
        }
        return count;}

    public void addOrChangeToLikes(String username){
        if (userNames.containsKey(username)){
            userNames.replace(username, 1);
        }
        else {
            userNames.put(username, 1);
        }
    }
    public void addOrChangeToDislikes(String username){
        if (userNames.containsKey(username)){
            userNames.replace(username, 0);
        }
        else {
            userNames.put(username, 0);
        }
    }

    public void undoLikesOrDislikes(String username){
        userNames.remove(username);
    }

    public ArrayList<String> getLikeUserNames() {
        ArrayList<String> likeUserNames = new ArrayList<>();
        for (String key: userNames.keySet()){
            if (userNames.get(key).equals(1)){
                likeUserNames.add(key);
            }
        }
        return likeUserNames;
    }
    public ArrayList<String> getDislikeUserNames() {
        ArrayList<String> DislikeUserNames = new ArrayList<>();
        for (String key: userNames.keySet()){
            if (userNames.get(key).equals(0)){
                DislikeUserNames.add(key);
            }
        }
        return DislikeUserNames;
    }

}
