package userInterfaces.menuAction;

import controllers.UserActionHandler;
import presenters.language.EnglishPresenter;
import presenters.language.LanguagePresenter;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.userPrompt.UserPrompt;

public class AccountCreation implements MenuAction{
    UserPrompt userPrompt;
    UserActionHandler userActionHandler;
    MenuBuilder menuBuilder;
    public AccountCreation(UserManager um, VideoManager vm, PlaylistManager pm, UserPrompt userPrompt) {
        userActionHandler = new UserActionHandler(um);
        menuBuilder = new MenuBuilder(um, vm, pm, userPrompt);
        this.userPrompt = userPrompt;
    }
    public void run(){
        System.out.println("Create account");
    }
    public void navigateMenu(){
        menuBuilder.getMenu("nonAdmin").run();
    }
}
