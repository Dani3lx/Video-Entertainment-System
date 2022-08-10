package controllers.actionFactories;

import controllers.actions.videoBrowseActions.BrowseByCategory;
import controllers.actions.videoBrowseActions.BrowseByName;
import controllers.actions.videoBrowseActions.BrowseByUploader;
import controllers.actions.videoBrowseActions.Return;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;


public class VideoBrowseActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    public VideoBrowseActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

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