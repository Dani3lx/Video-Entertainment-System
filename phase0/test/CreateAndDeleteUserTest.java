import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.util.List;

public class CreateAndDeleteUserTest {

    // test creating an AdminUser
    @Test(timeout=80)
    public void testCreateAdminUser(){
        // creates a new admin user and adds it to a clone of allUsers
        AdminUser a1 = new AdminUser("admin","123");
        List<User> clone = new ArrayList<>(UserData.getAllUsers());
        clone.add(a1);
        // acts as someone inputting their username and password to create an admin user
        InputStream in = new ByteArrayInputStream(("admin" + System.lineSeparator() + "123").getBytes());
        System.setIn(in);
        CreateUser CU = new CreateUser();
        CU.creatAdminUser();
        // test to see that the content of both lists match
        for(int i = 0; i < clone.size(); i++){
            assertEquals(clone.get(i).getUserName(), UserData.getAllUsers().get(i).getUserName());
            assertEquals(clone.get(i).getPassword(), UserData.getAllUsers().get(i).getPassword());
            assertEquals(clone.get(i).getBanStatus(), UserData.getAllUsers().get(i).getBanStatus());
            assertEquals(clone.get(i).isAdminInd(), UserData.getAllUsers().get(i).isAdminInd());
        }
        // todo: we need some way to delete an admin user because this test will create a permanent admin user
    }

    // test creating and then deleting a NonAdminUser
    @Test(timeout=80)
    public void testCreateAndDeleteNonAdminUser(){
        // creates a new non-admin user and adds it to a clone of allUsers
        NonAdminUser a1 = new NonAdminUser("nonadmin","123");
        List<User> clone = new ArrayList<>(UserData.getAllUsers());
        clone.add(a1);
        // acts as someone inputting their username and password to create a non-admin user
        InputStream in = new ByteArrayInputStream(("nonadmin" + System.lineSeparator() + "123").getBytes());
        System.setIn(in);
        CreateUser CU = new CreateUser();
        User u2 = CU.createUser();
        // test to see that the content of both lists match
        for(int i = 0; i < clone.size(); i++){
            assertEquals(clone.get(i).getUserName(), UserData.getAllUsers().get(i).getUserName());
            assertEquals(clone.get(i).getPassword(), UserData.getAllUsers().get(i).getPassword());
            assertEquals(clone.get(i).getBanStatus(), UserData.getAllUsers().get(i).getBanStatus());
            assertEquals(clone.get(i).isAdminInd(), UserData.getAllUsers().get(i).isAdminInd());
        }

        // ban the new non-admin user

        // try logging in with the new non-admin user's username
        // this should not be allowed

        // unban the new non-admin user

        // try logging again with the same username, and this time it works

        // deleting the new non-admin user from the cloned list
        clone.remove(a1);
        //acts as someone inputting the username of the new non-admin to delete that user
        in = new ByteArrayInputStream("nonadmin".getBytes());
        System.setIn(in);
        DeleteUser DU = new DeleteUser();
        // test to see that the content of both lists match
        for(int i = 0; i < clone.size(); i++){
            assertEquals(clone.get(i).getUserName(), UserData.getAllUsers().get(i).getUserName());
            assertEquals(clone.get(i).getPassword(), UserData.getAllUsers().get(i).getPassword());
            assertEquals(clone.get(i).getBanStatus(), UserData.getAllUsers().get(i).getBanStatus());
            assertEquals(clone.get(i).isAdminInd(), UserData.getAllUsers().get(i).isAdminInd());
        }
    }
}
