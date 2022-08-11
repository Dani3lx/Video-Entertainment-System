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

public class PlaylistOrderMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;

    private final List<String> actionList = List.of(new String[]{"reorder playlist alphabetically",
            "reorder playlist by likes",
            "shuffle playlist",
            "return to playlist menu"});

    public PlaylistOrderMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, List<Playlist> playlists) {
        actionFactory = new PlaylistActionFactory(userPrompt, user, lp, mp, playlists);
        this.userPrompt = userPrompt;
    }

    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.PLAYLISTORDER, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}