package usecase;

import entities.Video;

import java.util.*;

public class VideoRatingComparator implements Comparator<Video> {
    public int compare(Video v1, Video v2) {
        Integer v1Likes = Integer.parseInt(v1.getRatings().get(0));
        Integer v2Likes = Integer.parseInt(v2.getRatings().get(0));
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