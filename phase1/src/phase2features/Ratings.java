package phase2features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * THIS IS FOR PHASE 2
 */
public class Ratings {
    //Todo change userName to uniqueID instead.
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

    public boolean addUserName(String username, Integer value){
        if (!value.equals(0) && !value.equals(1)){
            return false;
        }
        else if (userNames.containsKey(username)){
            return false;
        }
        else {
            userNames.put(username, value);
            return true;
        }
    }

    public boolean editUserNames(String username, Integer value){
        if (!value.equals(0) && !value.equals(1)){
            return false;
        }
        else if(userNames.containsKey(username)){
            userNames.replace(username, value);
            return true;
        }
        else {
            return false;
        }
    }

    public void deleteUserName(String username){
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
