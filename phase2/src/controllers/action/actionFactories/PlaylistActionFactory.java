package controllers.action.actionFactories;

import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

public class PlaylistActionFactory implements ActionFactory {
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public PlaylistActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
    }

    public Action getAction(String type) {
        switch (type) {
            default:
                return null;
        }
    }
}