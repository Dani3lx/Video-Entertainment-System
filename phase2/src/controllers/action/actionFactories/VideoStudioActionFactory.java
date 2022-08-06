package controllers.action.actionFactories;

import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoStudioActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    UserPrompt userPrompt;

    public VideoStudioActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    public Action getAction(String type) {
        switch (type) {
            default:
                return null;
        }
    }
}