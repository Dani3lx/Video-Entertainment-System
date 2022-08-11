package usecase;

import entities.Video;

import java.util.Comparator;

/**
 * This comparator class is responsible for comparing two videos and returning an ordering based on rating
 * (used to reorder by rating)
 */

public class VideoRatingComparator implements Comparator<Video> {
    /**
     * Return positive or negative integer, or zero, according to which video is higher rated
     *
     * @param v1 the first Video object to be compared.
     * @param v2 the second Video object to be compared.
     * @return int
     */
    public int compare(Video v1, Video v2) {
        Integer v1Likes = v1.getRatings().getTotalLikes() - v1.getRatings().getTotalDislikes();
        Integer v2Likes = v2.getRatings().getTotalLikes() - v2.getRatings().getTotalDislikes();
        int i = v1Likes.compareTo(v2Likes);
        return Integer.compare(i, 0);
    }

}
