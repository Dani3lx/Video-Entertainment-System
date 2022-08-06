package controllers.action.actionFactories;

import controllers.action.actions.videoBrowseMenu.BrowseByName;
import controllers.action.actions.videoBrowseMenu.Return;
import controllers.action.actions.videoBrowseMenu.SelectVideo;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;


public class VideoBrowseActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    UserPrompt userPrompt;

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
            case "select video":
                return new SelectVideo(userPrompt, user, lp, mp);
            case "return":
                return new Return(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}