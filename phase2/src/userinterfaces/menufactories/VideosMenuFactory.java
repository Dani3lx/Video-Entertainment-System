package userinterfaces.menufactories;


import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menuenums.MenuEnums;
import userinterfaces.menus.videosmenus.VideoBrowseMenu;
import userinterfaces.menus.videosmenus.VideoInteractionMenu;
import userinterfaces.menus.videosmenus.VideoStudioMenu;
import userinterfaces.userprompt.UserPrompt;


public class VideosMenuFactory implements MenuFactory {
    private final User user;
    private final UserPrompt userPrompt;
    private final LanguagePresenter lp;
    private final MenuPresenter mp;
    private final Video video;

    /**
     * Creates a playlist menu factory with the given user prompt, user, language presenter, menu presenter and video.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param video      a video
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
            case VIDEOSTUDIO:
                return new VideoStudioMenu(userPrompt, user, lp, mp);
            default:
                return null;
        }
    }
}