import java.util.ArrayList;
import java.util.Scanner;

public class VideoManagementMenuDisplayer {
    VideoPresenter vp = new VideoPresenter();
    Presenter presenter;
    MenuDisplayer menuDisplayer;

    Scanner sc = new Scanner(System.in);
    public VideoManagementMenuDisplayer(Presenter presenter, MenuDisplayer menuDisplayer){
        this.presenter = presenter;
        this.menuDisplayer = menuDisplayer;
    }
    public void videoBrowseMenu(User user) {
        int result = menuDisplayer.getUserActionChoice("Please input one of the following number to proceed " +
                "\n 1 - Browse by name \n 2 - Browse by categories \n 3 - Browse by uploader \n 4 - Return");

        ArrayList<Video> videos;
        switch (result) {
            case 1:
                presenter.displayRequest("Please enter the name of the video");
                videos = menuDisplayer.userActionHandler.browseByName(sc.nextLine());
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 2:
                presenter.displayRequest("Please enter the name of the user, type CONTINUE to proceed");
                ArrayList<String> categories = new ArrayList<>();
                while (true) {
                    String item = sc.nextLine();
                    if (item.equals("CONTINUE")) {
                        break;
                    }
                    categories.add(item);
                }
                videos = menuDisplayer.userActionHandler.browseByCategories(categories);
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 3:
                presenter.displayRequest("Please enter the name of the uploader");
                videos = menuDisplayer.userActionHandler.browseByUploader(sc.nextLine());
                vp.listVideos(videos);
                viewVideo(videos, user);
                break;
            case 4:
                menuDisplayer.callMenu(user, menuDisplayer.userActionHandler.validateUserPermission(user));
                break;
        }
    }

    public void viewVideo(ArrayList<Video> videos, User user) {
        if (videos.size() == 0) {
            presenter.displayAlert("No video can be found, try again");
            videoBrowseMenu(user);
        }
        Scanner sc = new Scanner(System.in);
        presenter.displayRequest("Please enter a number to choose video you want to view");
        if (sc.hasNextInt()) {
            int choice = sc.nextInt();
            if (choice >= 0 && choice < videos.size()) {
                vp.displayVideo(videos.get(choice));
                userVideoInteraction(videos.get(choice), user);
            }
        }
        presenter.displayError("Invalid input");
        videoBrowseMenu(user);

    }

    public void userVideoInteraction(Video video, User user) {
        System.out.println("video");
        menuDisplayer.callMenu(user, menuDisplayer.userActionHandler.validateUserPermission(user));
    }
}
