package presenters.language;

/**
 * All requests and responses
 */
public interface LanguagePresenter {

    /**
     * Returns a request for user to enter an integer
     */
    String getChoiceText(ChoiceTextType type);

    /**
     * Returns a good response
     */
    String getAlertText(AlertTextType type);

    /**
     * Returns a bad response
     */
    String getErrorText(ErrorTextType type);

    /**
     * Returns a request for user to enter a string
     */
    String getRequestText(RequestTextType type);

    /**
     * Returns a response involving video data
     */
    String getVideoDataText(VideoDataType type);

    /**
     * Integer requests
     */
    enum ChoiceTextType {
        START,
        ADMIN,
        NONADMIN,
        VIDEOBROWSE,
        VIDEOSTUDIO,
        PLAYLIST,
        VIDEO,
        PLAYLISTMANAGE,
        PLAYLISTVIEW,
        PLAYLISTORDER,
        VIDEOINTERACTION
    }

    /**
     * Good responses
     */
    enum AlertTextType {
        CREATEACCOUNT,
        CHANGEPASSWORD,
        VIDEOSUPLOADED,
        ALLUSERS,
        DELETEUSER,
        UNBANNEDUSERS,
        BANNEDUSERS,
        BANNED,
        UNBANNED,
        RESULT,
        SUCCESS,
        ALLPLAYLISTS,
        EDIT,
        EDITCOMMENT,
        DELETECOMMENT,
        ADDCOMMENT,

        DISPLAYCOMMENT,
        LIKEVIDEO,
        DISLIKEVIDEO,
        UPLOADVIDEO,
        DELETEVIDEO,
    }

    /**
     * Bad responses
     */
    enum ErrorTextType {
        INVALIDINPUT,
        CREATEACCOUNT,
        DELETEUSER,
        BANNED,
        UNBANNED,
        EDIT,
        NORESULT,
        INVALIDUSER,
        EDITCOMMENT,
        DELETECOMMENT,
        ADDCOMMENT,
        UPLOADVIDEO,
        DELETEVIDEO,
    }

    /**
     * String requests
     */
    enum RequestTextType {
        USERNAME,
        PASSWORD,
        VIDEONAME,
        DELETEUSER,
        BANUSER,
        UNBANUSER,
        CATEGORY,
        UPLOADER,
        PLAYLIST,
        EDITVIDEO,
        TITLE,
        DESCRIPTION,
        NEWCOMMENT,
        COMMENT,
        VIDLINK,
        DELETEVIDEO,
    }

    /**
     * Responses involving video data
     */
    enum VideoDataType {
        TITLE,
        LIKES,
        DISLIKES,
        DESCRIPTION,
        UPLOADER,
        DATEUPLOADED,
        CONTENT,
    }
}
