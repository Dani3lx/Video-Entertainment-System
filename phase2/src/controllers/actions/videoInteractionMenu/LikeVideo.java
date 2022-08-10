package controllers.actions.videoInteractionMenu;

import controllers.actionFactories.Action;
import controllers.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.VideoEditor;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

public class LikeVideo extends MenuAction implements Action {
    private final Video video;

    public LikeVideo(UserPrompt userPrompt, User user, MenuPresenter mp, LanguagePresenter lp, Video video) {

        this.userPrompt = userPrompt;
        this.currentUser = user;
        this.mp = mp;
        this.lp = lp;
        this.video = video;
    }

    @Override
    public void run() {
        VideoEditor ve = new VideoEditor();
        ve.likeVideo(video, currentUser);
        mp.displayAlert(LanguagePresenter.AlertTextType.LIKEVIDEO);
        next();
    }

    @Override
    public void next() {
        MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
        if (um.getRole(currentUser)) {
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        } else {
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }
    }
}

