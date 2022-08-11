package userinterfaces.menus.playlistsmenu;

import controllers.actionfactories.Action;
import controllers.actionfactories.ActionFactory;
import controllers.actionfactories.PlaylistActionFactory;
import entities.Playlist;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menufactories.Menu;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;


public class PlaylistManageMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;

    private final List<String> actionList = List.of(new String[]{"view playlist",
            "add videos to playlist",
            "remove videos from playlist",
            "like playlist",
            "reorder playlist",
            "return to playlist search"});

    public PlaylistManageMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> playlists) {
        actionFactory = new PlaylistActionFactory(userPrompt, user, lp, mp, playlists);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.PLAYLISTMANAGE, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}