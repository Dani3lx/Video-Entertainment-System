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

    }

    enum ErrorTextType {
        INVALIDINPUT,
        CREATEACCOUNT,
        DELETEUSER,
    }

    enum RequestTextType {
        USERNAME,
        PASSWORD,
        VIDEONAME,
        DELETEUSER,
    }

    String getMenuText(MenuTextType type);

    String getAlertText(AlertTextType type);

    String getErrorText(ErrorTextType type);

    String getRequestText(RequestTextType type);
}
