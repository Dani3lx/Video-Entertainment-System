import java.util.ArrayList;
import java.util.Scanner;

public class VideoBrowser {
    VideoManager vm = new VideoManager();
    UserManager um;
    Scanner sc = new Scanner(System.in);

    User user;

    public VideoBrowser(User user) {
        this.user = user;
    }
    public ArrayList<Video> browseByName() {
        System.out.println("Please enter the name of the video");
        String name = sc.nextLine();
        if (um.getRole(user)) {
            return vm.getByName(name);
        } else {
            return new ArrayList<>();
        }
    }

    public ArrayList<Video> browseByUploader() {
        System.out.println("Please enter the name of the uploader");
        String name = sc.nextLine();
        return vm.getByUploader(name);
    }

//    public ArrayList<Video> browseByCategory() {
//        System.out.println("Please enter the categories that you wish to include. Type CONTINUE to proceed.");
//        ArrayList<String> categories = new ArrayList<>();
//        while (!(sc.nextLine().equals("CONTINUE"))) {
//            categories.add(sc.nextLine());
//        }
//        return vm.getByCategory(categories);
//    }
}
