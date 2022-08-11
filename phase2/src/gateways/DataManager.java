package gateways;

import entities.*;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * This class is responsible for reading and writing data from and to the csv files contained in phase2/datasets
 *
 * @author Benedek Balla, Shu Fan Nicholas Au
 */
public class DataManager {

    private final UserManager um = UserManager.getInstance();
    private final VideoManager vm = VideoManager.getInstance();
    private final PlaylistManager pm = PlaylistManager.getInstance();

    /**
     * Instantiate new DataManager
     */
    public DataManager() {
    }

    /**
     * Reads User data from phase2/datasets/Data.csv into the program by instantiating Users and adding them
     * into the list of all Users within UserManager.
     *
     * @param filePath the path to Data.csv
     */
    public void loadData(String filePath) {
        ArrayList<User> users = um.getAllUsers();
        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            String[] record;

            while (scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");
                if (record[3].equals("true")) {
                    User adminUser = um.instantiateUser(record[0], record[1], Boolean.parseBoolean(record[2]),
                            new HashSet<>(Arrays.asList(record[4].split("/"))), true);
                    users.add(adminUser);
                } else {
                    User nonAdminUser = um.instantiateUser(record[0], record[1], Boolean.parseBoolean(record[2]),
                            new HashSet<>(Arrays.asList(record[4].split("/"))), false);
                    users.add(nonAdminUser);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Writes User data from the list of all users within UserManager to phase2/datasets/Data.csv
     *
     * @param filePath the path to Data.csv
     */
    public void saveData(String filePath) {
        ArrayList<User> users = um.getAllUsers();
        try {
            FileWriter writer = new FileWriter(filePath, false);

            for (User user : users) {
                writer.write(user.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Writes Video data from the list of all videos within VideoManager to phase2/datasets/VideoData.csv
     *
     * @param filePath the path to VideoData.csv
     */
    public void saveVideoData(String filePath) {
        ArrayList<Video> videos = vm.getVids();
        try {
            FileWriter writer = new FileWriter(filePath, false);

            for (Video video : videos) {
                writer.write(video.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Reads Video data from phase2/datasets/VideoData.csv into the program by instantiating Videos and adding them
     * into the list of all Videos within VideoManager.
     *
     * @param filePath the path to VideoData.csv
     */
    public void loadVideoData(String filePath) {
        ArrayList<Video> videos = vm.getVids();
        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            String[] record;

            while (scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");

                // Read Ratings

                HashMap<String, Boolean> ratingsMap = new HashMap<>();
                for (String ratingString : new ArrayList<>(Arrays.asList(record[6].split("/")))) {
                    ArrayList<String> ratingSplit = new ArrayList<>(Arrays.asList(ratingString.split("=")));
                    ratingsMap.put(ratingSplit.get(0), Boolean.parseBoolean(ratingSplit.get(1)));
                }

                // Read Comments

                ArrayList<Comments> comments = new ArrayList<>();
                String[] indComment = record[8].split("/");

                for (String s : indComment) {
                    String[] commentAttributes = s.split(";");
                    Comments comment = new Comments(commentAttributes[0], commentAttributes[1], commentAttributes[2]);

                    comments.add(comment);
                }

                // Instantiate Video

                Video video = new Video(record[0], record[1], record[2], new ArrayList<>(Arrays.asList(record[3].split("/"))), record[4], record[5], new Ratings(ratingsMap), record[7], comments);
                videos.add(video);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Writes Playlist data from the list of all playlists within PlaylistManager to phase2/datasets/PlaylistData.csv
     *
     * @param filePath the path to PlaylistData.csv
     */
    public void savePlayListData(String filePath) {
        ArrayList<Playlist> playlists = pm.getPlaylists();
        try {
            FileWriter writer = new FileWriter(filePath, false);

            for (Playlist playlist : playlists) {
                writer.write(playlist.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    /**
     * Reads Playlist data from phase2/datasets/PlaylistData.csv into the program by instantiating Playlists and adding
     * them into the list of all Playlists within PlaylistManager.
     *
     * @param filePath the path to PlaylistData.csv
     */
    public void loadPlaylistData(String filePath) {
        ArrayList<Playlist> playlists = pm.getPlaylists();
        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            String[] record;

            while (scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");
                Playlist p = new Playlist(record[0], Integer.parseInt(record[1]), new ArrayList<>(Arrays.asList(record[2].split("/"))), record[3]);
                playlists.add(p);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

}
