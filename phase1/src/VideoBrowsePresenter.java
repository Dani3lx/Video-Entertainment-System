import java.util.ArrayList;

/**
 * Responsible for formatting and outputting video related data to the user.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class VideoBrowsePresenter {
    VideoEditor ve;
    VideoManager vm;

    /**
     * Constructs a video browse presenter.
     *
     * @param vm video manager
     */
    public VideoBrowsePresenter(VideoManager vm) {
        this.vm = vm;
        ve = new VideoEditor();
    }

    /**
     * Formats and displays list of videos to the user.
     *
     * @param videos the videos being listed
     */
    public void listVideos(ArrayList<Video> videos) {
        int num = 0;
        for (String video : vm.getVideoNames(videos)) {
            System.out.println(num + ". " + video);
            num++;
        }
    }

    /**
     * Displays the video to the user.
     *
     * @param video the video being displayed
     */
    public void displayVideo(Video video) {
        String[] information = ve.returnVideoInformation(video);
        System.out.println("\nTitle: " + information[0]);
        System.out.println("Uploader : " + information[1]);
        System.out.println("Description: " + information[2]);
        System.out.println("Date Uploaded : " + information[3]);
        System.out.println("Content : " + information[4]);
        System.out.println("Likes : " + information[5]);
        System.out.println("Dislikes : " + information[6] + "\n");
    }

}