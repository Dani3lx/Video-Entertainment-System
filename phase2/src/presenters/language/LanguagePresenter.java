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

    }

    enum ErrorTextType {
        INVALIDINPUT
    }

    enum RequestTextType {
        USERNAME,
        PASSWORD
    }
    String getMenuText(MenuTextType type);
    String getAlertText(AlertTextType type);
    String getErrorText(ErrorTextType type);
    String getRequestText(RequestTextType type);
}
