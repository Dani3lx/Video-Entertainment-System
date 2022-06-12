public class UserManager {

    public void deleteUser(User user){
        // go into UserData and remove User instance from ArrayList
    }

    public void banUser(User user){
        user.setBanStatus(true);
    }

    public void unbanUser(User user){
        user.setBanStatus(false);
    }

    public boolean validateUser(String username, String password){
        // go into UserData and check if the username exists
        // if it does, check if the passwords match
        // if they do, check the User's banStatus
    }

    public void changePassword(User user, String password){
        user.setPassword(password);
    }

    /*
    @param username: username of new User to be created
    @param passowrd: password of new User to be created
    @return true if new User was created successfully, false otherwise
     */
    public boolean createUser(String username, String password){
        // check if UserData already contains a User with same username
        // if not, create a new User instance and add it to UserData
        // HOW DO WE DETERMINE IF WE CREATE ADMIN OR NONADMIN USER???
    }

}