import java.util.Scanner;

public class Main {
    private Scanner sc= new Scanner(System.in);
    public void displayBeforeLogin(){
        while (true){
            System.out.println("Here is Before Login Page");
            System.out.println("Do you want to login?- ");
            if (sc.nextBoolean()){
                System.out.println("Please Enter Your UserName");
                String username = sc.next();
                System.out.println("Please Enter Your Password");
                String password = sc.next();
                loginValidateUser(username, password);
            }
            System.out.println("Do you want to create non-admin account?- ");
            if (sc.nextBoolean()){
                System.out.println("Please Enter Your UserName");
                String username = sc.next();
                System.out.println("Please Enter Your Password");
                String password = sc.next();
                createAccountValidateUser(username, password);
            }
        }
    }
    // If login successful, then direct option to after login. If not, Stay on page
    public void loginValidateUser(String username, String password){
        UserManager thisUserManager = new UserManager();
        if(thisUserManager.validateUser(username, password)){
            displayAfterLogin();
        }
        else{
            System.out.println("Failed to Login");
        }
    }
    public void createAccountValidateUser(String username, String password){
        CreateUser thisCreateUser = new CreateUser();
        if(thisCreateUser.createUser(username, password, false)){
            displayAfterCreateUser();
        }
        else{
            System.out.println("Create Account Failed");
        }
    }
    private void displayAfterLogin(){

    }
    private void displayAfterCreateUser(){

    }
    public void main(String[] args) {
        displayBeforeLogin();
    }
}
