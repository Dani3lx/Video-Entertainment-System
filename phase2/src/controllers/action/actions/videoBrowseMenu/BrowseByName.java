package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actionFactories.ActionFactory;
import controllers.action.actionFactories.VideoBrowseActionFactory;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class BrowseByName extends MenuAction implements Action {
    List<Video> videos;
    public BrowseByName(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        String videoName = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.VIDEONAME);
        videos = vm.getByName(videoName);
        next();
    }

    @Override
    public void next() {
    }
}
