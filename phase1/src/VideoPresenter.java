import java.util.ArrayList;

public class VideoPresenter {
    VideoManager vm = new VideoManager();
    public void listVideos(ArrayList<Video> videos) {
        int num = 1;
        for (String video : vm.getVideoNames(videos)) {
            System.out.println(num + ". " + video);
            num++;
        }
    }
}
    public void displayVideo(Video video) {
        // todo need to wait for vids to fill up in videoManager
    }

    public void listPlaylist(){
        int num = 1;
        Playlist p = new Playlist();
        for (Video video : p.getVideos()) {
            System.out.println(num + ". " + video.getContent());
            num++;
        }

    }
    public void searchedVid(Video v){
        System.out.println(v.getContent());
    }
