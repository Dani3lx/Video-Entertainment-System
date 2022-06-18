import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class BanUserTest {

    // test banning existing NonAdminUser
    @Test(timeout=50)
    public void testBanExisting(){
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        NonAdminUser u2 = new NonAdminUser("c", "d");
        UserData.updateData(u2);

        String n = "c";             //https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner
        InputStream in = new ByteArrayInputStream(n.getBytes());
        System.setIn(in);
        BanUser b = new BanUser();
        b.banUser(u1);
        assertTrue("You have successfully banned c",u2.getBanStatus()) ;

    }

    // test banning NonAdminUser that does not exist
    @Test(timeout=50)
    public void testBanNonExisting(){
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        NonAdminUser u2 = new NonAdminUser("c", "d");
        UserData.updateData(u2);

        String n = "nonexist";
        InputStream in = new ByteArrayInputStream(n.getBytes());
        System.setIn(in);
        BanUser b = new BanUser();
        b.banUser(u1);
        assertFalse("User a is not banned",u1.getBanStatus()) ;
        assertFalse("User c is not banned",u2.getBanStatus()) ;

    }

    // test unbanning existing NonAdminUser
    @Test(timeout=50)
    public void testUnBanExisting(){
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        NonAdminUser u2 = new NonAdminUser("e", "d");
        UserData.updateData(u2);
        UserManager UM = new UserManager();
        UM.banUser(u2);
        assertTrue("User e is banned", u2.getBanStatus());

        String n = "e";
        InputStream in = new ByteArrayInputStream(n.getBytes());
        System.setIn(in);
        BanUser b = new BanUser();
        b.unBanUser();
        assertFalse("User e is not banned",u2.getBanStatus()) ;

    }

    // test unbanning NonAdminUser that does not exist
    @Test(timeout=50)
    public void testUnBanNonExisting(){
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        NonAdminUser u2 = new NonAdminUser("c", "d");
        UserData.updateData(u2);

        String n = "d";
        InputStream in = new ByteArrayInputStream(n.getBytes());
        System.setIn(in);
        BanUser b = new BanUser();
        b.unBanUser();
        assertFalse("User a is not banned",u1.getBanStatus()) ;
        assertFalse("User c is not banned",u2.getBanStatus()) ;

    }
}
