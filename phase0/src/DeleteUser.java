import java.util.List;
import java.util.Scanner;

public class DeleteUser {

    /**
     * Delete a specific user.
     * Returns true if self is deleted.
     */
    public boolean deleteUser(User currentUser) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the username of the user you wish to delete");
        String name = sc.nextLine();
        List<User> all_users = UserData.getAllUsers();



        for (User user : all_users){
            if (user.getUserName().equals(name)) {
                if (currentUser.getUserName().equals(name)) {
                    System.out.println("Are you sure you want to delete this account");
                    System.out.println("Type t to continue");
                    String result = sc.nextLine();
                    if (result.equals("t")){
                        all_users.remove(user);
                        System.out.println("You have successfully deleted your own account");
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    all_users.remove(user);
                    System.out.println("The user " + name + " has been successfully deleted");
                    return false;
                }
            }
        }
        System.out.println("The user " + name + " does not exist");
        return false;
    }
}
