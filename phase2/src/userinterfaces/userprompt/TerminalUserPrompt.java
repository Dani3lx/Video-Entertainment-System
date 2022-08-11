package userinterfaces.userprompt;

import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A terminal user prompt that handles taking in user inputs.
 */
public class TerminalUserPrompt implements UserPrompt {
    private final MenuPresenter mp;
    private Scanner sc;

    /**
     * Creates a terminal user prompt with the given menu presenter.
     *
     * @param mp the program's menu presenter
     */
    public TerminalUserPrompt(MenuPresenter mp) {
        this.mp = mp;
    }

    /**
     * Request and returns the user's choice base on the choices and type through the terminal.
     *
     * @param type    type of choices
     * @param choices the choices
     * @return the user's choice
     */
    public int getUserChoice(LanguagePresenter.ChoiceTextType type, List<String> choices) {
        while (true) {
            sc = new Scanner(System.in);
            mp.displayChoiceOption(type, choices);
            if (sc.hasNextInt()) {
                int result = sc.nextInt();
                if (result > 0 && result <= choices.size()) {
                    return result;
                }
            }
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDINPUT);
        }
    }

    /**
     * Request and returns the user's string input base on the request type through the terminal.
     *
     * @param type the type of request
     * @return the user's input
     */
    public String getUserStringInput(LanguagePresenter.RequestTextType type) {
        sc = new Scanner(System.in);
        mp.displayRequest(type);
        return sc.nextLine();
    }

    /**
     * Request and returns multiple user inputs base on the request base on the request type through the terminal.
     *
     * @param type the type of request
     * @return the user's inputs
     */
    public List<String> getMultipleInputs(LanguagePresenter.RequestTextType type) {
        sc = new Scanner(System.in);
        mp.displayRequestMultiple(type);
        List<String> list = new ArrayList<>();
        while (true) {
            String item = sc.nextLine();
            if (item.equals("CONTINUE")) {
                break;
            }
            list.add(item);
        }
        return list;
    }

}
