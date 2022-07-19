package usecase;

import entities.Video;

import java.util.*;

public class RandomComparator implements Comparator<Video> {
    public int compare(Video v1, Video v2) {
        Random rand = new Random();
        return rand.nextInt(2 + 2) - 2; // generates random int in range [-1, 1]
    }
}