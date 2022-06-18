import org.junit.*;

import java.util.ArrayList;


import static org.junit.Assert.*;

public class UserManagerTest {

    @Test(timeout=50)
    public void testdeleteuser(){
        UserManager UM = new UserManager();
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        NonAdminUser u2 = new NonAdminUser("c","b");
        UserData.updateData(u2);
        UM.deleteUser(u1);
        ArrayList<User> l3 = new ArrayList<>();
        l3.add(u2);
        assertEquals(l3, UserData.getAllUsers());
    }

    @Test(timeout=50)
    public void testbanuser(){
        UserManager UM = new UserManager();
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        UM.banUser(u1);
        assertTrue("user is banned", u1.getBanStatus());
    }

    @Test(timeout=50)
    public void testunbanuser(){
        UserManager UM = new UserManager();
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        UM.banUser(u1);
        UM.unbanUser(u1);
        assertFalse("user not banned", u1.getBanStatus());
    }

    @Test(timeout=50)
    public void testvalidateuser(){
        UserManager UM = new UserManager();
        NonAdminUser u1 = new NonAdminUser("a","b");
        UserData.updateData(u1);
        User u2 =  UM.validateUser("a","b");
        assertEquals(u2, u1);
    }

    @Test(timeout=50)
    public void testchangepass(){
        UserManager UM = new UserManager();
        NonAdminUser u1 = new NonAdminUser("a","b");
        UM.changePassword(u1, "new");
        assertEquals("new", u1.getPassword());
    }
}
