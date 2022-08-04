package userInterfaces.userPrompt;

import java.util.List;

public interface UserPrompt {
    int getUserActionChoice(String text, List<String> actionList);
    String getUserStringInput(String text);
    int getUserIntInput(String text);
    List<String> getMultipleInputs(String text);
}
