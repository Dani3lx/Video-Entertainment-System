package controllers.action.actionFactories;

import controllers.action.actions.startMenu.AccountCreation;
import controllers.action.actions.startMenu.ExitProgram;
import controllers.action.actions.startMenu.UserLogin;
import controllers.action.actions.videoBrowseMenu.Return;
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