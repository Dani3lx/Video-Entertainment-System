package presenters.menupresenter;

import presenters.language.LanguagePresenter;

import java.util.List;

/**
 * Essential presenter actions for menus
 */
public interface MenuPresenter {

    /**
     * Asks the user for a single input
     */
    void displayRequest(LanguagePresenter.RequestTextType type);

    /**
     * Asks the user for multiple inputs
     */
    void displayRequestMultiple(LanguagePresenter.RequestTextType type);

    /**
     * Sends the user a bad response
     */
    void displayChoiceOption(LanguagePresenter.ChoiceTextType var, List<String> actionList);

    /**
     * Sends the user a bad response
     */
    void displayError(LanguagePresenter.ErrorTextType type);

    /**
     * Sends the user a good response
     */
    void displayAlert(LanguagePresenter.AlertTextType type);

    /**
     * Sends the user a list of strings
     */
    void displayList(List<String> list);
}
