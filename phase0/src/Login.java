import java.time.LocalDateTime;
import java.util.*;

public class Login {
    // Controller Class utilized to assist with login requests
    private User user; //todo: currently CRC slide says collaborators are the subclasses - Need to change
    private UserData dt;

    public Login(User user,UserData dt){
        // Login Constructor - sets the connects
        this.user = user;
        this.dt = dt;
    }

    public boolean validateLogin(String username, String password){ //todo
        if(!dt.all.userName.contains(username)){
            dt.
        }

    }

}
