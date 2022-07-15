import java.util.ArrayList;

public class VideoBrowsePresenter {
    VideoEditor ve = new VideoEditor();
    VideoManager vm;
    public VideoBrowsePresenter(VideoManager vm){
        this.vm = vm;
    }


    public void listVideos(ArrayList<Video> videos) {
        int num = 0;
        for (String video : vm.getVideoNames(videos)) {
            System.out.println(num + ". " + video);
            num++;
        }
    }

    public void displayVideo(Video video) {
        String[] information = ve.returnVideoInformation(video);
        System.out.println("\nTitle: " + information[0]);
        System.out.println("Uploader : " + information[1]);
        System.out.println("Description: " + information[2]);
        System.out.println("Date Uploaded : " + information[3]);
        System.out.println("Content : " + information[4] + "\n");
    }

}