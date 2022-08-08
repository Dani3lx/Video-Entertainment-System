package userInterfaces.menuFactories;


import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menus.videosMenus.VideoBrowseMenu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

public class VideosMenuFactory implements MenuFactory{
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final List<Video> videos;

    /**
     * Factory that returns menus that require a video/videos to be passed around.
     *
     * @param userPrompt
     * @param user
     * @param lp
     * @param mp
     * @param videos
     */
    public VideosMenuFactory(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Video> videos) {
        this.user = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.videos = videos;
    }

    public Menu getMenu(MenuEnums type) {
        switch (type) {
            case VIDEOBROWSE:
                return new VideoBrowseMenu(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}