package presenters.menuPresenter;

import presenters.language.LanguagePresenter;

import java.util.List;

public interface MenuPresenter {
    void displayRequest(LanguagePresenter.RequestTextType type);
    void displayRequestMultiple(LanguagePresenter.RequestTextType type);
    void displayMenuOption(LanguagePresenter.MenuTextType var, List<String> actionList);
    void displayError(LanguagePresenter.ErrorTextType type);
    void displayAlert(LanguagePresenter.AlertTextType type);
    void displayList(List<String> list);
}
