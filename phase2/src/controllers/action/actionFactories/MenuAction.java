package controllers.action.actionFactories;

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

public abstract class MenuAction implements Action{
    protected User currentUser;
    protected UserPrompt userPrompt = new TerminalUserPrompt();
    protected LanguagePresenter lp = new EnglishPresenter();
    protected UserManager um = UserManager.getInstance();
    protected VideoManager vm = VideoManager.getInstance();
    protected PlaylistManager pm = PlaylistManager.getInstance();
    protected final MenuPresenter mp = new TerminalMenuPresenter();
}
