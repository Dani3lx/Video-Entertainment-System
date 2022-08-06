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
    ActionFactory actionFactory;
    SelectVideo action;
    public BrowseByName(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        actionFactory = new VideoBrowseActionFactory(userPrompt, user, lp, mp);
        action = new SelectVideo(userPrompt, user, lp, mp);
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
        action.getVideos(videos);
        action.run();
    }
}
