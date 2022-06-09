import java.util.*;
public abstract class User {
    private List<User> allUsers = new ArrayList<>();
    private String username, password;

    public User(String name,String pass){
        username = name;
        password = pass;
        addUser();
    }

    public void addUser(){
        allUsers.add(this);
    }

    public List<User> getAllUsers(){
        return allUsers;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
