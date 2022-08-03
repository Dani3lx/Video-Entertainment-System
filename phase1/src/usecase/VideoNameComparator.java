package usecase;

import entities.Video;

import java.util.*;

/**
 * This comparator class is responsible for comparing two videos and returning an alphabetical ordering based on
 * their names (used to reorder according to name)
 * @author Benedek Balla
 */

public class VideoNameComparator implements Comparator<Video>{
    public int compare(Video v1, Video v2) {
        int i = v1.getName().compareTo(v2.getName());
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
