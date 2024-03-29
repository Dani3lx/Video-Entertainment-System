package usecase;

import entities.Video;

import java.util.*;

/**
 * This comparator class is responsible for comparing two videos and returning an ordering based on rating
 * (used to reorder by rating)
 * @author Benedek Balla
 */

public class VideoRatingComparator implements Comparator<Video> {
    public int compare(Video v1, Video v2) {
        Integer v1Likes = v1.getRatings().getTotalLikes() - v1.getRatings().getTotalDislikes();
        Integer v2Likes = v2.getRatings().getTotalLikes() - v2.getRatings().getTotalDislikes();
        int i = v1Likes.compareTo(v2Likes);
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
