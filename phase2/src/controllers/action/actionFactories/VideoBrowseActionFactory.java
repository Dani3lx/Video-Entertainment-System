package controllers.action.actionFactories;

import controllers.action.actions.videoBrowseMenu.BrowseByCategory;
import controllers.action.actions.videoBrowseMenu.BrowseByName;
import controllers.action.actions.videoBrowseMenu.BrowseByUploader;
import controllers.action.actions.videoBrowseMenu.Return;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;


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