package userInterfaces.userPrompt;

import presenters.language.LanguagePresenter;

import java.util.List;

public interface UserPrompt {
    int getUserChoice(LanguagePresenter.ChoiceTextType type, List<String> choices);
    String getUserStringInput(LanguagePresenter.RequestTextType type);
    int getUserIntInput(LanguagePresenter.RequestTextType type);
    List<String> getMultipleInputs(LanguagePresenter.RequestTextType type);
}
