package userinterfaces.menus.videosmenus;

import controllers.actionfactories.Action;
import controllers.actionfactories.ActionFactory;
import controllers.actionfactories.VideoStudioActionFactory;
import entities.User;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menufactories.Menu;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;

/**
 * A menu that handles video studio actions.
 */
public class VideoStudioMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;
    private final List<String> actionList = List.of(new String[]{"view videos uploaded", "upload video", "delete video",
            "edit title", "edit categories", "edit description"});

    /**
     * Creates a video studio menu with the given user prompt, user, language presenter and menu presenter.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     */
    public VideoStudioMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp) {
        actionFactory = new VideoStudioActionFactory(userPrompt, user, lp, mp);
        this.userPrompt = userPrompt;
    }

    /**
     * Runs the video studio menu's actions.
     */
    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEOSTUDIO, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}