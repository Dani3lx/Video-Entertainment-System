import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import java.util.List;

public class CreateUserTest {

    // test create AdminUser
    @Test(timeout=80)
    public void testCreateAdminUser(){
        AdminUser a1 = new AdminUser("red","y");
        List<User> l3 = UserData.getAllUsers();
        l3.add(a1);

        InputStream in = new ByteArrayInputStream(("red" + System.lineSeparator() + "y").getBytes());
        System.setIn(in);

        CreateUser CU = new CreateUser();
        CU.creatAdminUser();

        assertEquals(l3, UserData.getAllUsers());




    }

    // test create NonAdminUser
    @Test(timeout=80)
    public void testCreateNonAdminUser(){
        User u1 = new NonAdminUser("f","g");

        InputStream in = new ByteArrayInputStream(("f" + System.lineSeparator() + "g").getBytes());
        System.setIn(in);

        CreateUser CU = new CreateUser();
        User u2 = CU.createUser();
        String u1_str = u1.toString();
        String u2_str = u2.toString();
        assertEquals(u1_str, u2_str);

    }
}
