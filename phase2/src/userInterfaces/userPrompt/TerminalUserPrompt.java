package userInterfaces.userPrompt;

import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import presenters.menuPresenter.TerminalMenuPresenter;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalUserPrompt implements UserPrompt {
    MenuPresenter mp = new TerminalMenuPresenter();
    Scanner sc;

    public int getUserActionChoice(LanguagePresenter.MenuTextType type, List<String> actionList) {
        while (true){
            sc = new Scanner(System.in);
            mp.displayMenuOption(type, actionList);
            if (sc.hasNextInt()) {
                int result = sc.nextInt();
                if (result > 0 && result <= actionList.size()) {
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
        mp.displayRequest(type);
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
