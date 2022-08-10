package controllers.actionFactories;

import controllers.actions.videoBrowseActions.BrowseByCategory;
import controllers.actions.videoBrowseActions.BrowseByName;
import controllers.actions.videoBrowseActions.BrowseByUploader;
import controllers.actions.videoBrowseActions.Return;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

/**
 * A concrete action factory that creates video browsing related actions base on type.
 */
public class VideoBrowseActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    /**
     * Creates a video browse action factory with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public VideoBrowseActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
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
            case "browse by name":
                return new BrowseByName(userPrompt, user, lp, mp);
            case "browse by category":
                return new BrowseByCategory(userPrompt, user, lp, mp);
            case "browse by uploader":
                return new BrowseByUploader(userPrompt, user, lp, mp);
            case "return":
                return new Return(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}