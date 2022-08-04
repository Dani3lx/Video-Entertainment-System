package userInterfaces.userPrompt;

import presenters.menuPresenter.MenuPresenter;
import presenters.menuPresenter.TerminalMenuPresenter;
import usecase.UserManager;
import usecase.VideoManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TerminalUserPrompt implements UserPrompt {
    MenuPresenter mp = new TerminalMenuPresenter(new UserManager(new VideoManager()), new VideoManager());
    Scanner sc;

    public int getUserActionChoice(String type, List<String> actionList) {
        while (true){
            sc = new Scanner(System.in);
            mp.displayMenuOption(type, actionList);
            if (sc.hasNextInt()) {
                int result = sc.nextInt();
                if (result > 0 && result <= actionList.size()) {
                    return result;
                }
            }
            mp.displayError("invalidInput");
        }
    }

    public String getUserStringInput(String text) {
        sc = new Scanner(System.in);
        mp.displayRequest(text);
        return sc.nextLine();
    }

    public int getUserIntInput(String text) {
        sc = new Scanner(System.in);
        mp.displayRequest(text);
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return -1;
        }
    }

    public List<String> getMultipleInputs(String text) {
        sc = new Scanner(System.in);
        mp.displayRequest(text + ". Type CONTINUE to proceed.");
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
