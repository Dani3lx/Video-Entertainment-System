package controllers.actions.playlistactions.orderplaylist;

import controllers.actions.MenuAction;
import entities.Playlist;
import presenters.language.LanguagePresenter;
/**
 * This abstract parent class holds code that would be duplicated across all of the
 * reorder playlist actions.  A switch is used to denote which reordering type is used
 * and in that switch, the correct playlist manager method is called.
 * For future reordering options, add a switch case and pm method here
 */
public abstract class PlaylistOrderAction extends MenuAction {
    /**
     *
     * @param order string used to determine switch case
     * @param pl playlist being reordered
     * @return a new playlist that is reordered
     */
    protected Playlist playlistOrderRun(String order, Playlist pl) {

        /* Validate if user can make changes*/
        String username = um.getUserName(currentUser);
        String old_name = pm.getPlName(pl);
        boolean validate = pm.validatePlaylistAction(pl, username);
        if (!validate) {
            mp.displayError(LanguagePresenter.ErrorTextType.INVALIDUSER);
            return pl;
        } else {
            switch (order) {
                case "by_name":
                    Playlist sorted_pl = pm.reorderPlaylistByName(pl, username);
                    pm.setPlName(sorted_pl, old_name + "_name_sorted");
                case "by_rating":
                    sorted_pl = pm.reorderPlaylistByRating(pl, username);
                    pm.setPlName(sorted_pl, old_name + "_rating_sorted");
                case "shuffle":
                    sorted_pl = pm.shufflePlaylist(pl, username);
                    pm.setPlName(sorted_pl, old_name + "_shuffled");
            }
            Playlist sorted_pl = pm.reorderPlaylistByName(pl, username);
            pm.addPlaylist(sorted_pl);
            mp.displayAlert(LanguagePresenter.AlertTextType.SUCCESS);
            return sorted_pl;

        }


    }
}
