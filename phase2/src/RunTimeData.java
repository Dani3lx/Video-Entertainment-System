import entities.User;
import presenters.language.LanguagePresenter;
import usecase.PlaylistManager;
import usecase.UserManager;
import usecase.VideoManager;
import userInterfaces.menu.MenuBuilder;
import userInterfaces.menuAction.MenuActionFactory;
import userInterfaces.userPrompt.UserPrompt;

public class RunTimeData {
    private final UserManager userManager;
    private final VideoManager videoManager;



    private final PlaylistManager playlistManager;
    private final MenuBuilder menuBuilder;
    private final MenuActionFactory menuActionFactory;
    private LanguagePresenter languagePresenter;
    private UserPrompt userPrompt;
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public RunTimeData(UserManager um, VideoManager vm, PlaylistManager pm, MenuBuilder builder, MenuActionFactory factory) {
        this.userManager = um;
        this.videoManager = vm;
        this.playlistManager = pm;
        this.menuBuilder = builder;
        this.menuActionFactory = factory;
    }

    public void setLanguagePresenter(LanguagePresenter languagePresenter) {
        this.languagePresenter = languagePresenter;
    }

    public void setUserPrompt(UserPrompt userPrompt) {
        this.userPrompt = userPrompt;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    public VideoManager getVideoManager() {
        return videoManager;
    }

    public PlaylistManager getPlaylistManager() {
        return playlistManager;
    }

    public MenuBuilder getMenuBuilder() {
        return menuBuilder;
    }

    public MenuActionFactory getMenuActionFactory() {
        return menuActionFactory;
    }

    public LanguagePresenter getLanguagePresenter() {
        return languagePresenter;
    }

    public UserPrompt getUserPrompt() {
        return userPrompt;
    }
}
