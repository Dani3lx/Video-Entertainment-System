import java.util.ArrayList;

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

    public void addTotalLikes(String username){
        likeUserName.add(username);
        totalLikes += 1;
    }

    public ArrayList<String> getLikeUserName() {
        return likeUserName;
    }
}
