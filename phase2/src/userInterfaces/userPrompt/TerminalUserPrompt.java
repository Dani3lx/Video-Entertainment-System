package userInterfaces.userPrompt;

import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalUserPrompt implements UserPrompt {
    MenuPresenter mp;
    Scanner sc;

    public TerminalUserPrompt(MenuPresenter mp){
        this.mp = mp;
    }

    public int getUserChoice(LanguagePresenter.ChoiceTextType type, List<String> choices) {
        while (true){
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

    public String getUserStringInput(LanguagePresenter.RequestTextType type) {
        sc = new Scanner(System.in);
        mp.displayRequest(type);
        return sc.nextLine();
    }

    public int getUserIntInput(LanguagePresenter.RequestTextType type) {
        sc = new Scanner(System.in);
        mp.displayRequest(type);
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return -1;
        }
    }

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
