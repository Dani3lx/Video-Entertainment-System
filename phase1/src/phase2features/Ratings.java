package phase2features;

import java.util.ArrayList;
//todo remove this for phase 1, try to incorporate in phase 2
public class Ratings {
    private ArrayList<String> likeUserName;
    private Integer totalLikes;

    public Ratings(ArrayList<String> likeUserName){
        this.totalLikes = likeUserName.size();
        this.likeUserName = likeUserName;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void addLikes(String username){
        likeUserName.add(username);
        totalLikes += 1;
    }
    public void addDislikes(String username){
        likeUserName.add(username);
        totalLikes += 1;
    }

    public ArrayList<String> getLikeUserName() {
        return likeUserName;
    }
}