package presenters.language;

public class EnglishPresenter implements LanguagePresenter {
    public String getMenuText(MenuTextType var) {
        switch (var) {
            case START:
                return "This is the start menu, please select one of the following options to proceed.";
            case ADMIN:
                return "Welcome to the admin menu, please select one of the following admin action to proceed.";
            case NONADMIN:
                return "Welcome to the non-admin menu, please select one of the following non-admin action to proceed.";
            case VIDEOBROWSE:
                return "Welcome to the video browsing system, please select one of the following action to proceed.";
            case VIDEOSTUDIO:
                return "Welcome to the video studio, please select one of the following action to proceed.";
            case PLAYLIST:
                return "Welcome to the playlist system, please select one of the following action to proceed.";
            default:
                return "";
        }
    }

    public String getAlertText(AlertTextType type) {
        switch (type) {
            case CREATEACCOUNT:
                return "Account creation was successful";
            case CHANGEPASSWORD:
                return "Password has been successfully changed";
            case VIDEOSUPLOADED:
                return "Here are all the videos you uploaded";
            case ALLUSERS:
                return "Here are all the users";
            case DELETEUSER:
                return "Deletion was successful";
            default:
                return "";
        }
    }

    public String getErrorText(ErrorTextType type) {
        switch (type) {
            case INVALIDINPUT:
                return "Invalid input, please try again.";
            case CREATEACCOUNT:
                return "Account creation was not successful";
            case DELETEUSER:
                return "User was not successfully deleted";
            default:
                return "";
        }
    }

    public String getRequestText(RequestTextType type) {
        switch (type) {
            case USERNAME:
                return "Please input a username";
            case PASSWORD:
                return "Please input a password";
            case VIDEONAME:
                return "Please input the video name";
            case DELETEUSER:
                return "Please enter the username of the user you wish to delete";
            default:
                return "";
        }
    }
}
