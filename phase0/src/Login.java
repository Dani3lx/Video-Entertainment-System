import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class Login {
    // Controller Class utilized to assist with login requests
    private User user; //todo: currently CRC slide says collaborators are the subclasses - Need to change
    private UserData dt;
    private long loginattempt;

    public Login(User user,UserData dt){
        // Login Constructor - sets the connects
        this.user = user;
        this.dt = dt;
        this.loginattempt = System.currentTimeMillis();
    }

    public boolean validateLogin(String username, String password){

        //validateLogin will take username and password input from Main
        //it will loop through all users to see if the username and password match
        //if it matches we'll return true, if it does not match it will return false

        boolean retval = false;
        for(int i = 0;i<dt.getAllUsers().size();i++){
            if (dt.getAllUsers().get(i).getUserName().compareTo(username)==0 &&
            dt.getAllUsers().get(i).getPassword().compareTo(password)==0){
                retval = true;
                this.loginattempt = System.currentTimeMillis();
                //Overwrite timestamp for successful login
                break;
            }
        }
        return retval;
    }

}
