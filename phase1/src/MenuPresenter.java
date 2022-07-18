import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for formatting and outputting data to the user
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class MenuPresenter {
    private final UserManager um;
    private final VideoManager vm;

    /**
     * Constructs a menu presenter with a record of all the users and videos.
     *
     * @param um this is the user manager which keep tracks of all the users
     * @param vm this is the video manager which keep tracks of all the videos
     */
    public MenuPresenter(UserManager um, VideoManager vm) {
        this.um = um;
        this.vm = vm;
    }

    /**
     * Displays the user's login history.
     *
     * @param user              the target user
     * @param userActionHandler the handler that handle user actions
     */
    public void displayLoginHistory(User user, UserActionHandler userActionHandler) {
        System.out.println(userActionHandler.getHistory(user));
        System.out.println("\n");
    }

    /**
     * Displays all the banned/unbanned users.
     *
     * @param banStatus whether to display banned or unbanned users
     */
    public void displayUsers(boolean banStatus) {
        AdminManager am = new AdminManager(um, vm);
        if (banStatus) {
            displayAlert("Here are all the banned users");
        } else {
            displayAlert("Here are all the unbanned users");
        }
        displayList(am.returnUsersByBan(um.getAllUsers(), banStatus));
    }

    /**
     * Displays all the users.
     */
    public void displayUsers() {
        displayAlert("Here are all the users");
        AdminManager am = new AdminManager(um, vm);
        displayList(am.returnUsers(um.getAllUsers()));
    }

    /**
     * Displays all the videos uploaded by user.
     */
    public void displayVideos(User user, ArrayList<Video> vids) {
        displayAlert("Here are all the videos uploaded by " + user.getUserName());
        NonAdminManager nam = new NonAdminManager(um, vm);
        displayList(nam.displayAllVideos(user, vids));

    }

    /**
     * Displays an alert.
     *
     * @param message the message being displayed
     */
    public void displayAlert(String message) {
        System.out.println(alertText(message));
    }

    /**
     * Displays an error.
     *
     * @param message the message being displayed
     */
    public void displayError(String message) {
        System.out.println(errorText(message));
    }

    /**
     * Displays a request to the user.
     *
     * @param message the message being displayed
     */
    public void displayRequest(String message) {
        System.out.println(requestText(message));
    }

    /**
     * Displays a menu option.
     *
     * @param message the message being displayed
     */
    public void displayMenuOption(String message) {
        System.out.println(menuOption(message));
    }

    /**
     * Displays a list.
     *
     * @param list the list being displayed
     */
    public void displayList(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    /**
     * Formats the text for request.
     *
     * @param input the text to be formatted
     * @return the formatted text
     */
    private String requestText(String input) {
        String symbol = "=";
        int num = input.length();
        return "\n" + input + "\n" + symbol.repeat(num) + "\n";
    }

    /**
     * Formats the text for menu option.
     *
     * @param input the text to be formatted
     * @return the formatted text
     */
    private String menuOption(String input) {
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("*".repeat(num));
        return decorator + "\n" + input + "\n" + decorator;
    }

    /**
     * Formats the text for alert.
     *
     * @param input the text to be formatted
     * @return the formatted text
     */
    private String alertText(String input) {
        String symbol = "-";
        int num = input.length();
        return "\n" + symbol.repeat(num) + "\n" + input + "\n" + symbol.repeat(num) + "\n";
    }

    /**
     * Formats the text for error.
     *
     * @param input the text to be formatted
     * @return the formatted text
     */
    private String errorText(String input) {
        String symbol = "#";
        int num = input.length();
        return "\n" + symbol.repeat(num) + "\n" + input + "\n" + symbol.repeat(num) + "\n";
    }
}
