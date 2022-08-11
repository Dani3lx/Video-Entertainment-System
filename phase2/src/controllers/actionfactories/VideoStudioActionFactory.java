package controllers.actionfactories;

import controllers.actions.videostudioactions.*;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.userprompt.UserPrompt;

/**
 * A concrete action factory that creates video studio related actions base on type.
 */
public class VideoStudioActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    /**
     * Creates a video studio action factory with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public VideoStudioActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    /**
     * Generates and returns an action base on the type.
     *
     * @param type the type of action
     * @return an action with the given type
     */
    public Action getAction(String type) {
        switch (type) {
            case "view videos uploaded":
                return new ViewVideoUploaded(userPrompt, user, lp, mp);
            case "upload video":
                return new UploadVideo(userPrompt, user, lp, mp);
            case "delete video":
                return new DeleteVideo(userPrompt, user, lp, mp);
            case "edit title":
                return new EditTitle(userPrompt, user, lp, mp);
            case "edit categories":
                return new EditCategories(userPrompt, user, lp, mp);
            case "edit description":
                return new EditDescription(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}