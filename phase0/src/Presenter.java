public class Presenter {
    public String startMenuOption(String input){
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("*".repeat(num));
        return decorator +"\n" + input + "\n" + decorator;
    }

    public String alertText(String input){
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("-".repeat(num));
        return decorator +"\n" + input + "\n" + decorator;
    }
}
