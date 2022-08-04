package presenters.language;

public class EnglishPresenter implements LanguagePresenter {
    public String getMenuText(String str){
        switch(str){
            case "start":
                return "This is the start menu, please select one of the following options to proceed.";
            case "...":
                return "...";
            default:
                return "";
        }
    }

    public String getAlertText(String str){
        switch(str){
            case "....":
                return "....";
            case "...":
                return "...";
            default:
                return "";
        }
    }

    public String getErrorText(String str){
        switch(str){
            case "invalidInput":
                return "Invalid input, please try again.";
            case "....":
                return "...";
            default:
                return "";
        }
    }

    public String getRequestText(String str){
        switch(str){
            case "username":
                return "Please input a username";
            case "password":
                return "Please input a password";
            default:
                return "";
        }
    }
}
