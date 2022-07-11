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