package presenters.menupresenter;

import presenters.language.LanguagePresenter;

import java.util.List;

/**
 * Responsible for formatting and outputting data to the user
 */
public class TerminalMenuPresenter implements MenuPresenter {
    private final LanguagePresenter lp;

    public TerminalMenuPresenter(LanguagePresenter lp) {
        this.lp = lp;
    }

    /**
     * Displays an alert.
     */
    public void displayAlert(LanguagePresenter.AlertTextType type) {
        System.out.println(alertText(lp.getAlertText(type)));
    }

    /**
     * Displays an error.
     */
    public void displayError(LanguagePresenter.ErrorTextType type) {
        System.out.println(errorText(lp.getErrorText(type)));
    }

    /**
     * Displays a request to the user.
     */
    public void displayRequest(LanguagePresenter.RequestTextType type) {
        System.out.println(requestText(lp.getRequestText(type)));
    }

    public void displayRequestMultiple(LanguagePresenter.RequestTextType type) {
        System.out.println(requestText(lp.getRequestText(type)) + "Type CONTINUE to proceed.");
    }

    /**
     * Displays a menu option.
     */
    public void displayChoiceOption(LanguagePresenter.ChoiceTextType type, List<String> actionList) {
        System.out.println(menuOption(lp.getChoiceText(type)));
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
        int count = 1;
        for (String str : list) {
            System.out.println(count + " - " + str);
            count++;
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
