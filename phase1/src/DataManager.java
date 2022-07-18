import java.util.*;
import java.io.*;

public class DataManager {

    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Video> videos = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();

    private UserManager um;
    private VideoManager vm;
    private PlaylistManager pm;

    public DataManager(UserManager um, VideoManager vm, PlaylistManager pm) {
        this.um = um;
        this.vm = vm;
        this.pm = pm;
    }

    public void loadData(String filePath) {

        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            String[] record;

            while (scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");
                if (record[3].equals("true")) {
//                  List<Integer> loginTimeInt = stringToListInt(record[4]);
//                  User adminUser = um.instantiateUser(record[0], record[1], Boolean.parseBoolean(record[2]),
//                            new HashSet<>(loginTimeInt), true);
                    User adminUser = um.instantiateUser(record[0], record[1], Boolean.parseBoolean(record[2]),
                            new HashSet<>(Arrays.asList(record[4].split("/"))), true);
                    users.add(adminUser);
                } else {
//                  List<Integer> loginTimeInt = stringToListInt(record[4]);
//                  User adminUser = um.instantiateUser(record[0], record[1], Boolean.parseBoolean(record[2]),
//                            new HashSet<>(loginTimeInt), true);
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

    public void saveData(String filePath) {
        setUsers((ArrayList<User>) um.getAllUsers());
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

    public ArrayList<User> getUsers() {
        return users;
    }


    public void setUsers(java.util.ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public void setPlaylists(ArrayList<Playlist> playlists){
        this.playlists = playlists;
    }

    public void saveVideoData(String filePath) {
        this.setVideos(vm.getVids());
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

    public void loadVideoData(String filePath) {

        try {
            Scanner scanner = new Scanner(new FileInputStream(filePath));
            String[] record;

            while (scanner.hasNextLine()) {
                record = scanner.nextLine().split(",");
                Video video = new Video(record[0], record[1], record[2], new ArrayList<>(Arrays.asList(record[3].split("/"))), record[4], record[5], new ArrayList<>(Arrays.asList((record[6]).split("/"))), record[7]);
                videos.add(video);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }

    public void savePlayListData(String filePath) {
        this.setPlaylists(pm.getPlaylists());
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

    public List<Integer> stringToListInt(String stringType) {
        List<String> listString = Arrays.asList(stringType.split("/"));
        List<Integer> listInt = new ArrayList<Integer>();
        for (int i = 0; i < listString.size(); i++) {
            listInt.add(i, Integer.parseInt(listString.get(i)));
        }
        return listInt;
    }

}
