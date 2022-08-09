package controllers.action.actionFactories;

import controllers.action.actions.videoStudioMenu.ViewVideoUploaded;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

public class VideoInteractionActionFactory implements ActionFactory{

    private final User user;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final UserPrompt userPrompt;

    public VideoInteractionActionFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.user = user;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public Action getAction(String type) {
        switch (type) {
            case "like video":
                return new ViewVideoUploaded(userPrompt, user, lp, mp);
            case "dislike video":
            case "delete comment":
            case "edit comment":
            default:
                return null;
        }
    }
}
