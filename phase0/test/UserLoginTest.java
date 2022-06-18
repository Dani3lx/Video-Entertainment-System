import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class UserLoginTest {

    // test existing AdminUser login
    @Test(timeout=80)
    public void testAdminUserLogin(){

        AdminUser a1 = new AdminUser("z","y");
        UserData.updateData(a1);
        InputStream in = new ByteArrayInputStream(("z" + System.lineSeparator() + "y").getBytes());

        System.setIn(in);
        assertEquals(a1, UserLogin.loginUser());

    }

    // test existing NonAdminUser login
    @Test(timeout=80)
    public void testNonAdminUserLogin(){
        NonAdminUser a1 = new NonAdminUser("hello","y");
        UserData.updateData(a1);
        InputStream in = new ByteArrayInputStream(("hello" + System.lineSeparator() + "y").getBytes());

        System.setIn(in);
        assertEquals(a1, UserLogin.loginUser());

    }
}
