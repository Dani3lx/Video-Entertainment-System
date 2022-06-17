public class Presenter {
    public String startMenuOption(String input){
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        for(int n = 0; n < num; n++) {
            decorator.append("*");
        }
        return decorator +"\n" + input + "\n" + decorator;
    }

    public String alertText(String input){
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        for(int n = 0; n < num; n++) {
            decorator.append("-");
        }
        return decorator +"\n" + input + "\n" + decorator;
    }
}
