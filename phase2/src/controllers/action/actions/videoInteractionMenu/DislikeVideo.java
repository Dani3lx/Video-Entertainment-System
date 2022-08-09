package controllers.action.actions.videoInteractionMenu;

import controllers.action.actionFactories.Action;
import controllers.action.actions.MenuAction;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import usecase.VideoEditor;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

public class DislikeVideo extends MenuAction implements Action {
    private final Video video;

    public DislikeVideo(UserPrompt userPrompt, User user, MenuPresenter mp, LanguagePresenter lp, Video video) {

        this.userPrompt = userPrompt;
        this.currentUser = user;
        this.mp = mp;
        this.lp = lp;
        this.video = video;
    }

    @Override
    public void run() {
        VideoEditor ve = new VideoEditor();
        ve.dislikeVideo(video, currentUser);
        mp.displayAlert(LanguagePresenter.AlertTextType.DISLIKEVIDEO);
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
