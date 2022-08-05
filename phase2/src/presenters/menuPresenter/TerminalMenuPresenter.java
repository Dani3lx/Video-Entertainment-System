package presenters.menuPresenter;

import entities.User;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.runtimeDataManager.AdminManager;
import usecase.runtimeDataManager.NonAdminManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.List;

/**
 * Responsible for formatting and outputting data to the user
 *
 * @author Daniel Xu, Wing Zou
 * @version 1.0
 * @since 2022-07-21
 */
public class TerminalMenuPresenter implements MenuPresenter{
    private final UserManager um;
    private final AdminManager am;
    private final NonAdminManager nm;
    private final VideoManager vm;

    private final LanguagePresenter lp = new EnglishPresenter(); //todo use a factory instead maybe??

    /**
     * Constructs a menu presenter with a record of all the users and videos.
     *
     * @param um this is the user manager which keep tracks of all the users
     * @param vm this is the video manager which keep tracks of all the videos
     */
    public TerminalMenuPresenter(UserManager um, VideoManager vm) {
        this.um = um;
        this.vm = vm;
        this.am = new AdminManager(um, vm);
        this.nm = new NonAdminManager(vm);
    }

    /**
     * Displays the user's login history.
     *
     * @param user the target user
     */
    public void displayLoginHistory(User user) {
        System.out.println(um.getHistory(user));
        System.out.println("\n");
    }

    /**
     * Displays all the banned/unbanned users.
     *
     * @param banStatus whether to display banned or unbanned users
     */
    public void displayUsers(boolean banStatus) {

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
        displayList(am.returnUsers(um.getAllUsers()));
    }

    /**
     * Displays all the videos uploaded by user.
     */
    public void displayVideos(User user) {
        displayAlert("Here are all the videos uploaded by " + um.getUserName(user));
        displayList(nm.displayAllVideos(user, vm.getVids()));
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
        System.out.println(errorText(lp.getErrorText(message)));
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
    public void displayMenuOption(String message, List<String> actionList) {
        System.out.println(menuOption(lp.getMenuText(message)));
        int counter = 1;
        for (String item : actionList) {
            System.out.println(counter + ": " + item);
            counter++;
        }
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

    /*
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

    /*
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

    /*
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

    /*
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
