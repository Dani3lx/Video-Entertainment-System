import java.util.Scanner;

public class Presenter {

    private String startMenuOption(String input) {
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("*".repeat(num));
        return decorator + "\n" + input + "\n" + decorator;
    }

    private String alertText(String input) {
        int num = input.length();
        StringBuilder decorator = new StringBuilder();
        decorator.append("-".repeat(num));
        return decorator + "\n" + input + "\n" + decorator;
    }

    public int startMenuOptions() {
        Scanner sc = new Scanner(System.in);
        System.out.println(startMenuOption("Type 1 to login, type 2 to create a new user account, type 3" +
                " to exit program"));
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return 0;
        }
    }

    public int basicMenuOptions(String text) {
        Scanner sc = new Scanner(System.in);
        System.out.println(startMenuOption(text));
        if (sc.hasNextInt()) {
            return (sc.nextInt());
        } else {
            return 0;
        }
    }

    public int afterLoginOptions(boolean isAdmin){
        if (isAdmin) {
            System.out.println("Please input one of the following number to proceed " +
                    "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n 4 - Create AdminUser \n" +
                    " 5 - Delete User \n 6 - Ban User \n 7 - UnBan User \n");
        } else {
            System.out.println("Please input one of the following number to proceed " +
                    "\n 1 - Change Password \n 2 - Check login history \n 3 - Log out \n\n\n");
        }

        Scanner sc = new Scanner(System.in);

        if (sc.hasNextInt()) {
            return sc.nextInt();
        } else {
            return 0;
        }
    }

    public String getPassword() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a new password");
        return sc.nextLine();
    }

    public void displayErrorMessage(String error) {
        switch (error) {
            case "login":
                System.out.println("Login was unsuccessful");
                break;
            case "accountCreation" :
                System.out.println("Account creation was unsuccessful");
                break;
            case "userInput":
                System.out.println("Please enter a valid input");
                break;
        }
    }

    public void displayAlertMessage(String alert) {
        switch (alert) {
            case "adminLogin" :
                System.out.println(alertText("you are now logged in to an admin account"));
                break;
            case "nonAdminLogin" :
                System.out.println(alertText("you are now logged in to an non-admin account"));
                break;
            case "accountCreation":
                System.out.println(alertText("A new account has been successfully created"));
                break;
            case "passwordChange":
                System.out.println("Password change was successful\n");
                break;
        }
    }

    public void displayOptionMessages(String option) {
        switch (option) {
            case "checkHistory":
                System.out.println("Checking history:");
                break;
            case "lineBreak":
                System.out.println("\n");
        }
    }
}
