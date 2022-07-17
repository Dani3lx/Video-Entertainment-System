import java.util.ArrayList;

/**
 * Responsible for handling non admin users' actions.
 *
 * @author Daniel Xu
 * @version 1.0
 * @since 2022-07-15
 */
public class NonAdminHandler extends UserActionHandler {

    NonAdminManager nm;

    /**
     * Constructs a non admin handler with a record of all the users and videos.
     *
     * @param um this is the user manager which keep tracks of all the users
     * @param vm this is the video manager which keep tracks of all the videos
     */
    public NonAdminHandler(UserManager um, VideoManager vm) {
        super(um);
        nm = new NonAdminManager(um, vm);
    }

    // todo add the controller methods required for user interaction with playlist, and uploading videos and stuff

}
