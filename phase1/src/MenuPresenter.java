import java.util.List;

public class MenuPresenter {
    private final UserManager um;

    private final VideoManager vm;
    public MenuPresenter(UserManager um, VideoManager vm) {
        this.um = um;
        this.vm = vm;
    }

    public void displayLoginHistory(User user, UserActionHandler userActionHandler){
        System.out.println(userActionHandler.getHistory(user));
        System.out.println("\n");
    }

    public void displayUsers(boolean banStatus) {
        AdminManager am = new AdminManager(um, vm);
        if (banStatus) {
            displayAlert("Here are all the banned users");
        } else {
            displayAlert("Here are all the unbanned users");
        }
        displayList(am.returnUsersByBan(um.getAllUsers(), banStatus));
    }

    public void displayUsers() {
        displayAlert("Here are all the users");
        AdminManager am = new AdminManager(um, vm);
        displayList(am.returnUsers(um.getAllUsers()));
    }

    public void displayAlert(String message) {
        System.out.println(alertText(message));
    }

    public void displayError(String message) {
        System.out.println(errorText(message));
    }

    public void displayRequest(String message) {
        System.out.println(requestText(message));
    }

    public void displayMenuOption(String message) {
        System.out.println(menuOption(message));
    }

    public void displayList(List<String> list) {
        for (String str : list) {
            System.out.println(str);
        }
    }

    private String requestText(String input) {
        String symbol = "=";
        int num = input.length();
        return "\n" + input + "\n" + symbol.repeat(num) + "\n";
    }

    private String menuOption(String input) {
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("*".repeat(num));
        return decorator + "\n" + input + "\n" + decorator;
    }

    private String alertText(String input) {
        String symbol = "-";
        int num = input.length();
        return "\n" + symbol.repeat(num) + "\n" + input + "\n" + symbol.repeat(num) + "\n";
    }

    private String errorText(String input) {
        String symbol = "#";
        int num = input.length();
        return "\n" + symbol.repeat(num) + "\n" + input + "\n" + symbol.repeat(num) + "\n";
    }
}
