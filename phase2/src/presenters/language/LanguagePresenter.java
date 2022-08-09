package presenters.language;

public interface LanguagePresenter {
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
        DISLIKEVIDEO
    }

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
    }

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
        EDITCOMMENT,
        NEWCOMMENT,
        DELETECOMMENT,
        ADDCOMMENT,
        COMMENT,
    }

    enum VideoDataType{
        TITLE,
        LIKES,
        DISLIKES,
        DESCRIPTION,
        UPLOADER,
        DATEUPLOADED,
        CONTENT
    }

    String getChoiceText(ChoiceTextType type);

    String getAlertText(AlertTextType type);

    String getErrorText(ErrorTextType type);

    String getRequestText(RequestTextType type);
    String getVideoDataText(VideoDataType type);
}
