package controllers.menuAction.menuActionFactories;

import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

public class PlaylistMenuActionFactory implements MenuActionFactory{
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public PlaylistMenuActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            default:
                return null;
        }
    }
}