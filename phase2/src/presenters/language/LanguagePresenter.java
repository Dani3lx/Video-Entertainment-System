package presenters.language;

public interface LanguagePresenter {
    String getMenuText(String str);
    String getAlertText(String str);
    String getErrorText(String str);
    String getRequestText(String str);
}
