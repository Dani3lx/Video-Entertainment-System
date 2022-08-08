package presenters.language;

public interface LanguagePresenter {
    enum MenuTextType {
        START,
        ADMIN,
        NONADMIN,
        VIDEOBROWSE,
        VIDEOSTUDIO,
        PLAYLIST
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
    }

    enum ErrorTextType {
        INVALIDINPUT,
        CREATEACCOUNT,
        DELETEUSER,
        BANNED,
        UNBANNED,
        NOTFOUND
    }

    enum RequestTextType {
        USERNAME,
        PASSWORD,
        VIDEONAME,
        DELETEUSER,
        BANUSER,
        UNBANUSER,
        PLAYLIST,
    }

    String getMenuText(MenuTextType type);

    String getAlertText(AlertTextType type);

    String getErrorText(ErrorTextType type);

    String getRequestText(RequestTextType type);
}
