package controllers.action.actions;

import controllers.action.actionFactories.Action;
import entities.User;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import presenters.menuPresenter.TerminalMenuPresenter;
import usecase.runtimeDataManager.PlaylistManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;
import userInterfaces.userPrompt.TerminalUserPrompt;
import userInterfaces.userPrompt.UserPrompt;

public abstract class MenuAction implements Action {
    protected User currentUser;

    protected UserManager um = UserManager.getInstance();
    protected VideoManager vm = VideoManager.getInstance();
    protected PlaylistManager pm = PlaylistManager.getInstance();
    protected LanguagePresenter lp = new EnglishPresenter();
    protected final MenuPresenter mp = new TerminalMenuPresenter(lp);
    protected UserPrompt userPrompt = new TerminalUserPrompt(lp);
}
