import java.util.Scanner;

public class Main {
    private Scanner sc= new Scanner(System.in);
    public static void displayBeforelogin(){

    }
    public void displayBeforeCreateAccount(){

    }
    public void displayEnterAccount(String username, String password){
        UserManager usermanager = new UserManager();
        if(usermanager.validateUser(username, password)){

        }
    }
    public void main(String[] args) {

    }
}
