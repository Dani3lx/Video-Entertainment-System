import java.util.*;

public class VideoRatingComparator implements Comparator<Video> {
    public int compare(Video v1, Video v2) {
        int i = v1.getRatings().compareTo(v2.getRatings());
        if (i == 0) {
            return 0;
        }
        else if (i > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }

}
