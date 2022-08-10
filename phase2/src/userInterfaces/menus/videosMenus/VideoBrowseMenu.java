package userInterfaces.menus.videosMenus;

import controllers.actionFactories.VideoBrowseActionFactory;
import controllers.actionFactories.Action;
import controllers.actionFactories.ActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuFactories.Menu;
import userInterfaces.userPrompt.UserPrompt;

import java.util.List;

/**
 * A menu that handles video browsing related actions.
 */
public class VideoBrowseMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;
    private final List<String> actionList = List.of(new String[]{"browse by name", "browse by category", "browse by uploader", "return"});

    /**
     * Creates a video browse menu with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public VideoBrowseMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        actionFactory = new VideoBrowseActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    /**
     * Runs the video browse menu's actions.
     */
    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEOBROWSE, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}