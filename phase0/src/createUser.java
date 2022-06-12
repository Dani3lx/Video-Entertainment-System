
public class createUser {

    private  String username;
    private  String password;

    public createUser(String user, String pass)
    {
        username = user;
        password=pass;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public void create(String user, String pass){
        createUser person = new createUser(user, pass);
        UserManager.createUser(username, password);
    }
}
