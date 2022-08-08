package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class BrowseByName extends MenuAction implements Action {
    List<Video> videos;
    Video video;
    public BrowseByName(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        String videoName = userPrompt.getUserStringInput(LanguagePresenter.RequestTextType.VIDEONAME);
        videos = vm.getByName(videoName);
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEO, vm.getVideoNames(videos));
        video = videos.get(result - 1);
        next();
    }

    @Override
    public void next() {
        System.out.println(video);
    }
}
