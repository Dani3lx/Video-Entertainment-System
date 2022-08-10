package userInterfaces.userPrompt;

import presenters.language.LanguagePresenter;

import java.util.List;

/**
 * A user prompt that handles taking in user inputs.
 */
public interface UserPrompt {
    /**
     * Request and returns the user's choice base on the choices and type.
     *
     * @param type    type of choices
     * @param choices the choices
     * @return the user's choice
     */
    int getUserChoice(LanguagePresenter.ChoiceTextType type, List<String> choices);

    /**
     * Request and returns the user's string input base on the request type.
     *
     * @param type the type of request
     * @return the user's input
     */
    String getUserStringInput(LanguagePresenter.RequestTextType type);

    /**
     * Request and returns multiple user inputs base on the request base on the request type.
     *
     * @param type the type of request
     * @return the user's inputs
     */
    List<String> getMultipleInputs(LanguagePresenter.RequestTextType type);
}
