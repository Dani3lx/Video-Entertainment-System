package controllers.action.actions.videoBrowseMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class BrowseByCategory extends MenuAction implements Action {
    List<Video> videos;
    Video video;
    public BrowseByCategory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp){
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
    }

    @Override
    public void run() {
        List<String> categories = userPrompt.getMultipleInputs(LanguagePresenter.RequestTextType.CATEGORY);
        videos = vm.getByCategory(categories);
        if (!videos.isEmpty()) {
            int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEO, vm.getVideoNames(videos));
            video = videos.get(result - 1);
        } else {
            mp.displayError(LanguagePresenter.ErrorTextType.NORESULT);
        }
        next();
    }

    @Override
    public void next() {
        if (videos.isEmpty()) {
            System.out.println("No videos");
        } else {
            System.out.println(video);
        }

    }
}
