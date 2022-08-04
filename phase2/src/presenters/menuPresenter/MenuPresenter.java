package presenters.menuPresenter;

import java.util.List;

public interface MenuPresenter {
    void displayRequest(String text);
    void displayMenuOption(String message, List<String> actionList);
    void displayError(String message);
    void displayAlert(String message);
}
