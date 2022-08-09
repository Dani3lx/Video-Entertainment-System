package presenters;

import entities.Video;
import usecase.VideoEditor;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;

/**
 * Responsible for formatting and outputting video related data to the user.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class VideoBrowsePresenter {
    private final VideoEditor ve;
    private final VideoManager vm;

    /**
     * Constructs a video browse presenter.
     *
     * @param vm video manager
     */
    public VideoBrowsePresenter(VideoManager vm) {
        this.vm = vm;
        this.ve = new VideoEditor();
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

    }
}