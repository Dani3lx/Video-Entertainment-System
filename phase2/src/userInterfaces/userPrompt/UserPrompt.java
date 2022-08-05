package userInterfaces.userPrompt;

import presenters.language.LanguagePresenter;

import java.util.List;

public interface UserPrompt {
    int getUserActionChoice(LanguagePresenter.MenuTextType type, List<String> actionList);
    String getUserStringInput(LanguagePresenter.RequestTextType type);
    int getUserIntInput(LanguagePresenter.RequestTextType type);
    List<String> getMultipleInputs(LanguagePresenter.RequestTextType type);
}
