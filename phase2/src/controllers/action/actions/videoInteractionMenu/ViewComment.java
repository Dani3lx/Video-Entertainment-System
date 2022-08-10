package controllers.action.actions.videoInteractionMenu;

import controllers.action.actions.MenuAction;
import entities.Comments;
import entities.User;
import entities.Video;
import presenters.language.LanguagePresenter;
import presenters.menuPresenter.MenuPresenter;
import userInterfaces.menuEnums.MenuEnums;
import userInterfaces.menuFactories.MenuFactory;
import userInterfaces.menuFactories.UserMenuFactory;
import userInterfaces.userPrompt.UserPrompt;

import java.util.ArrayList;

public class ViewComment extends MenuAction {
    private final Video v;
    public ViewComment(UserPrompt userPrompt, User user, LanguagePresenter lp, MenuPresenter mp, Video v) {
        currentUser = user;
        this.userPrompt = userPrompt;
        this.lp = lp;
        this.mp = mp;
        this.v=v;
    }

    @Override
    public void run() {
        mp.displayAlert(LanguagePresenter.AlertTextType.DISPLAYCOMMENT);
        ArrayList<Comments> coms = vm.getByUniqueID(v.getUniqueID()).getComments();
        ArrayList<String> com_strs = new ArrayList<>();
        for (Comments c: coms){
            String str = c.getCommenter()+": "+c.getComment()+" "+c.getComment_date();
            com_strs.add(str);
        }
        mp.displayList(com_strs);
        next();
    }

    @Override
    public void next() {
        if (!um.getRole(currentUser)) {
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.NONADMIN).run();
        }else{
            MenuFactory userMenuFactory = new UserMenuFactory(userPrompt, currentUser, lp, mp);
            userMenuFactory.getMenu(MenuEnums.ADMIN).run();
        }
    }
}
