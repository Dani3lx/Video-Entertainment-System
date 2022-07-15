import java.util.*;

public class Playlist implements Iterable<Video> {

    private String name;
    private int likes, length;
    private ArrayList<Video> videos;
    private String userName;  // the name of the user who made the playlist

    public Playlist(String playlistName, String userName){
        this.name = playlistName;
        likes = 0;
        length = 0;
        videos = new ArrayList<>();
        this.userName = userName;
    }

    public String getPlaylistName() {
        return name;
    }

    public void setPlaylistName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public ArrayList<Video> getVideos() {
        return videos;
    }

    public void setVideos(ArrayList<Video> videos) {
        this.videos = videos;
    }

    public void addVideo(Video video) {
        videos.add(video);
    }

    public void removeVideo(Video video) {
        videos.remove(video);
    }

    public String toString() {
        ArrayList<String> lst = new ArrayList<>();
        for (Video v : videos) {
            lst.add(v.getName());
        }
        return lst.toString();
    }
    public boolean equals(Playlist p){
        if (p.getLength()== this.getLength()){

            for (Video v: p.getVideos()) {
                if (!this.getVideos().contains(v)) {
                    return false;
                }
            }
            return true;

        } else{
            return false;
        }

    }

    public static void main(String[] args) {
        Playlist play = new Playlist("Music", "k");
        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("10","0"));
        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
        play.addVideo(v1);


        Playlist play2 = new Playlist("Music", "k");

        play2.addVideo(v1);
        System.out.println(play.equals(play2));

    }

    @Override
    public Iterator<Video> iterator() {
        return new PlaylistIterator();
    }

    private class PlaylistIterator implements Iterator<Video> {
        private int current = 0;

        public boolean hasNext() {
            return current < videos.size();
        }

        public Video next() {
            Video video = videos.get(current);
            current += 1;
            return video;
        }
    }

}
