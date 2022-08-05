package controllers.menuAction.menuActionFactories;

import controllers.menuAction.menuActions.videoBrowseMenu.ReturnMenu;
import entities.User;
import presenters.language.LanguagePresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoBrowseMenuActionFactory implements MenuActionFactory {
    private final UserPrompt userPrompt;
    private final User user;
    private final LanguagePresenter lp;

    public VideoBrowseMenuActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
    }

    public MenuAction getMenuAction(String type) {
        switch (type) {
            case "return":
                return new ReturnMenu(userPrompt, user, lp);
            default:
                return null;
        }
    }
}