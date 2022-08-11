package userinterfaces.menus.videosmenus;

import controllers.actionfactories.Action;
import controllers.actionfactories.ActionFactory;
import controllers.actionfactories.VideoInteractionActionFactory;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menupresenter.MenuPresenter;
import userinterfaces.menufactories.Menu;
import userinterfaces.userprompt.UserPrompt;

import java.util.List;

/**
 * A menu that handles video interaction.
 */
public class VideoInteractionMenu implements Menu {
    private final UserPrompt userPrompt;
    private final ActionFactory actionFactory;
    private final List<String> actionList = List.of(new String[]{"like video", "dislike video", "add comment", "delete comment", "edit comment", "view comments"});

    /**
     * Creates a video interaction menu with the given user prompt, user, language presenter, menu presenter and video.
     *
     * @param userPrompt the program's user prompt
     * @param user       a user
     * @param lp         the program's language presenter
     * @param mp         the program's menu presenter
     * @param video      a video
     */
    public VideoInteractionMenu(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video video) {
        actionFactory = new VideoInteractionActionFactory(userPrompt, user, lp, mp, video);
        this.userPrompt = userPrompt;
    }

    /**
     * Runs the video interactions.
     */
    public void run() {
        int result = userPrompt.getUserChoice(LanguagePresenter.ChoiceTextType.VIDEOINTERACTION, actionList);
        Action action = actionFactory.getAction(actionList.get(result - 1));
        action.run();
    }
}
