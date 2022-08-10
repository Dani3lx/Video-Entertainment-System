package presenters.language;

/**
 * Stores all English responses and requests
 */
public class EnglishPresenter implements LanguagePresenter {

    /**
     *  Stores integer input requests after menu options are given
     *
     * @param var the enum type
     * @return the corresponding string
     */
    public String getChoiceText(ChoiceTextType var) {
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
                return "Welcome to the playlist browse menu, please select one of the following action to proceed.";
            case VIDEO:
                return "Here are the videos";
            case VIDEOINTERACTION:
                return "Welcome to the video interaction menu, please select one of the following action to proceed.";
            case PLAYLISTVIEW:
                return "Welcome to the view playlist options menu, please select one of the following actions to proceed.";
            case PLAYLISTORDER:
                return "Welcome to the playlist reordering menu, please select one of the following actions to proceed.";
            case PLAYLISTMANAGE:
                return "Welcome to the playlist management menu, please select one of the following actions to proceed.";
            default:
                return "";
        }
    }

    /**
     *  Stores alert messages after a certain action
     *
     * @param type the enum type
     * @return the corresponding string
     */
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
            case UNBANNEDUSERS:
                return "Here are all the unbanned users";
            case BANNEDUSERS:
                return "Here are all the banned users";
            case BANNED:
                return "The user has been successfully banned";
            case UNBANNED:
                return "The user has been successfully unbanned";
            case RESULT:
                return "These are the search result";
            case EDIT:
                return "Video has been successfully edited";
            case EDITCOMMENT:
                return "The user has edited the comment successfully";
            case DELETECOMMENT:
                return "The user has deleted the comment successfully";
            case ADDCOMMENT:
                return "The user has added the comment successfully";
            case DISPLAYCOMMENT:
                return "Here are the comments:";
            case LIKEVIDEO:
                return "You have liked this video.";
            case DISLIKEVIDEO:
                return "You have disliked this video.";
            case UPLOADVIDEO:
                return "Video uploaded successfully";
            case DELETEVIDEO:
                return "Video deleted successfully";
            case SUCCESS:
                return "Your action has been completed successfully";
            case ALLPLAYLISTS:
                return "Here is the playlist directory.  Please enter the name of the playlist you would like to access.";
            default:
                return "";
        }
    }

    /**
     *  Stores error messages after a certain action
     *
     * @param type the enum type
     * @return the corresponding string
     */
    public String getErrorText(ErrorTextType type) {
        switch (type) {
            case INVALIDINPUT:
                return "Invalid input, please try again.";
            case CREATEACCOUNT:
                return "Account creation was not successful";
            case DELETEUSER:
                return "User was not successfully deleted";
            case BANNED:
                return "The ban operation was unsuccessful";
            case UNBANNED:
                return "The unban operation was unsuccessful";
            case EDIT:
                return "Edit video operation was unsuccessful";
            case NORESULT:
                return "No results were found";
            case EDITCOMMENT:
                return "Comment edit of the video has not been successful";
            case DELETECOMMENT:
                return "Deletion of comment has not been successful";
            case  ADDCOMMENT:
                return "Addition of comment has not been successful";
            case UPLOADVIDEO:
                return "Video was not successfully uploaded, title or video path cannot be blank";
            case DELETEVIDEO:
                return "Video was not successfully deleted";
            case INVALIDUSER:
                return "You do not have sufficient permissions to access this content.";
            default:
                return "";
        }
    }

    /**
     *  Stores string input requests
     *
     * @param type the enum type
     * @return the corresponding string
     */
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
            case BANUSER:
                return "Please enter the username of the user you wish to ban";
            case UNBANUSER:
                return "Please enter the username of the user you wish to unban";
            case CATEGORY:
                return "Please enter the categories";
            case UPLOADER:
                return "Please enter the name of the uploader";
            case EDITVIDEO:
                return "Enter uniqueID of the video you want to edit";
            case DELETEVIDEO:
                return "Enter uniqueID of the video you want to delete";
            case TITLE:
                return "Enter new video title";
            case DESCRIPTION:
                return "Enter new video description";
            case NEWCOMMENT:
                return "Enter the new comment:";
            case COMMENT:
                return "Enter the comment:";
            case VIDLINK:
                return "Enter the video path";
            case PLAYLIST:
                return "Enter the playlist's name";
            default:
                return "";
        }
    }

    /**
     *  Stores data stored in a video
     *
     * @param type the enum type
     * @return the corresponding string
     */
    public String getVideoDataText(VideoDataType type) {
        switch(type){
            case UPLOADER:
                return "Uploaded by: ";
            case LIKES:
                return "Likes: ";
            case TITLE:
                return "Title: ";
            case CONTENT:
                return "Content: ";
            case DISLIKES:
                return "Dislikes: ";
            case DESCRIPTION:
                return "Description: ";
            case DATEUPLOADED:
                return "Date uploaded: ";
            default:
                return "";
        }
    }
}
