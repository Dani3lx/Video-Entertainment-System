package usecase;

import entities.Video;

import java.util.*;

/**
 * This comparator class is responsible for comparing two videos and returning a random ordering (used to shuffle)
 */

public class RandomComparator implements Comparator<Video> {
    /**
     * Return a random integer: -1, 0, 1
     *
     * @param v1 the first Video object to be compared.
     * @param v2 the second Video object to be compared.
     * @return int
     */
    public int compare(Video v1, Video v2) {
        Random rand = new Random();
        return rand.nextInt(2 + 2) - 2; // generates random int in range [-1, 1]
    }
}