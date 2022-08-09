package userInterfaces.menuFactories;


import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menus.videosMenus.VideoBrowseMenu;
import userInterfaces.menus.videosMenus.VideoInteractionMenu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideosMenuFactory implements MenuFactory{
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final Video video;

    /**
     * Factory that returns menus that require a video/videos to be passed around.
     *
     * @param userPrompt
     * @param user
     * @param lp
     * @param mp
     * @param video
     */
    public VideosMenuFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video video) {
        this.user = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.video = video;
    }

    public Menu getMenu(MenuEnums type) {
        switch (type) {
            case VIDEOBROWSE:
                return new VideoBrowseMenu(userPrompt, user, lp, mp);
            case VIDEOINTERACTION:
                return new VideoInteractionMenu(userPrompt, user, lp, mp, video);
            default:
                return null;
        }
    }
}