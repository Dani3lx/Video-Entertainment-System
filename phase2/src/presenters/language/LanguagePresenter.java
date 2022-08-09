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
        EDIT,
        EDITCOMMENT,
        DELETECOMMENT,
    }

    enum ErrorTextType {
        INVALIDINPUT,
        CREATEACCOUNT,
        DELETEUSER,
        BANNED,
        UNBANNED,
        EDIT,
        NORESULT,
        EDITCOMMENT,
        DELETECOMMENT,
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
        EDITVIDEO,
        TITLE,
        DESCRIPTION,
        EDITCOMMENT,
        COMMENT,
        DELETECOMMENT,
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
