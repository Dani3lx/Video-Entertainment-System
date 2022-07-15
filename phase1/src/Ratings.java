import java.util.ArrayList;

public class Ratings {
    ArrayList<String> likeUserName;
    Integer totalLikes;

    public Ratings(ArrayList<String> likeUserName){
        this.totalLikes = likeUserName.size();
        this.likeUserName = likeUserName;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void addTotalLikes(){
        totalLikes += 1;
    }

    public ArrayList<String> getLikeUserName() {
        return likeUserName;
    }
}
