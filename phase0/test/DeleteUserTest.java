import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class DeleteUserTest {

    // test deleting an existing NonAdminUser
    @Test(timeout=50)
    public void testDeleteExisting(){

        NonAdminUser u1 = new NonAdminUser("d","e");
        NonAdminUser u2 = new NonAdminUser("f","g");

        String n = "d";
        InputStream in = new ByteArrayInputStream(n.getBytes());
        System.setIn(in);

        DeleteUser DU = new DeleteUser();

        assertFalse("User u2 has been deleted", DU.deleteUser(u2));

    }

    // test deleting an NonAdminUser that does not exist
    @Test(timeout=50)
    public void testDeleteNonExisting(){
        NonAdminUser u1 = new NonAdminUser("d","e");
        NonAdminUser u2 = new NonAdminUser("f","g");

        String n = "z";
        InputStream in = new ByteArrayInputStream(n.getBytes());
        System.setIn(in);

        DeleteUser DU = new DeleteUser();

        assertFalse("User non-existent", DU.deleteUser(u2));
    }

}
