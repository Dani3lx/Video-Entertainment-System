package controllers.action.actionFactories;

import controllers.action.actions.videoStudioActions.EditCategories;
import controllers.action.actions.videoStudioActions.EditDescription;
import controllers.action.actions.videoStudioActions.EditTitle;
import controllers.action.actions.videoStudioActions.ViewVideoUploaded;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoStudioActionFactory implements ActionFactory {
    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    public VideoStudioActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    public Action getAction(String type) {
        switch (type) {
            case "view videos uploaded":
                return new ViewVideoUploaded(userPrompt, user, lp, mp);
            case "upload video":
            case "delete video":
            case "edit title":
                return new EditTitle(userPrompt, user, lp, mp);
            case "edit categories":
                return new EditCategories(userPrompt, user, lp, mp);
            case "edit description":
                return new EditDescription(userPrompt, user, lp, mp);
            case "edit comment":
            case "delete comment":
            default:
                return null;
        }
    }
}