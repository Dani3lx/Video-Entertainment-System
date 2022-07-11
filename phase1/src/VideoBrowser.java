import java.util.ArrayList;
import java.util.Scanner;

public class VideoBrowser {
    VideoManager vm = new VideoManager();
    Scanner sc = new Scanner(System.in);
    public ArrayList<Video> browseByName() {
        System.out.println("Please enter the name of the video");
        String name = sc.nextLine();
        return vm.getByName(name);
    }

    public ArrayList<Video> browseByUploader() {
        System.out.println("Please enter the name of the uploader");
        String name = sc.nextLine();
        return vm.getByUploader(name);
    }

    public ArrayList<Video> browseByCategory() {
        System.out.println("Please enter the categories that you wish to include. Type CONTINUE to proceed.");
        ArrayList<String> categories = new ArrayList<>();
        while (!(sc.nextLine().equals("CONTINUE"))) {
            categories.add(sc.nextLine());
        }
        return vm.getByCategory(categories);
    }
}
