import org.junit.*;

import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.List;

public class CreateBanLoginDeleteUserTest {

    // test creating, logging in as AdminUser
    @Test(timeout = 80)
    public void testCreateAdminUser() {

        //THIS ADMIN USER LEFT IN SYSTEM
        // creates a new admin user and adds it to a clone of allUsers
        AdminUser a1 = new AdminUser("admin", "123");
        List<User> clone = new ArrayList<>(UserData.getAllUsers());
        clone.add(a1);

        //CHECKING CreateUser
        // acts as someone inputting their username and password to create an admin user
        InputStream in = new ByteArrayInputStream(("admin" + System.lineSeparator() + "123").getBytes());       //https://stackoverflow.com/questions/31635698/junit-testing-for-user-input-using-scanner
        System.setIn(in);
        CreateUser CU = new CreateUser();
        CU.creatAdminUser();
        // test to see that the content of both lists match
        for (int i = 0; i < clone.size(); i++) {
            assertEquals(clone.get(i).getUserName(), UserData.getAllUsers().get(i).getUserName());
            assertEquals(clone.get(i).getPassword(), UserData.getAllUsers().get(i).getPassword());
            assertEquals(clone.get(i).getBanStatus(), UserData.getAllUsers().get(i).getBanStatus());
            assertEquals(clone.get(i).isAdminInd(), UserData.getAllUsers().get(i).isAdminInd());
        }
        // CHECKING Login
        InputStream in_2 = new ByteArrayInputStream(("admin" + System.lineSeparator() + "123").getBytes());
        System.setIn(in_2);
        User u2 = UserLogin.loginUser();
        assertEquals(a1.getUserName(), u2.getUserName());
        assertEquals(a1.getPassword(), u2.getPassword());
        assertEquals(a1.getBanStatus(), u2.getBanStatus());
        assertEquals(a1.isAdminInd(), u2.isAdminInd());


    }

    // test creating,banning, login, unbanning and then deleting a NonAdminUser
    @Test(timeout = 80)
    public void testCreateAndDeleteNonAdminUser() {

        // creates 2 non-admin users and adds them to a clone of allUsers
        NonAdminUser u1 = new NonAdminUser("nonadmin", "123");
        NonAdminUser u2 = new NonAdminUser("nonadmin2", "123");

        List<User> clone = new ArrayList<>(UserData.getAllUsers());
        clone.add(u2);
        clone.add(u1);
        UserData.updateData(u2);

        //CHECK CreateUser
        // acts as someone inputting their username and password to create a non-admin user
        InputStream in = new ByteArrayInputStream(("nonadmin" + System.lineSeparator() + "123").getBytes());
        System.setIn(in);
        CreateUser CU = new CreateUser();
        CU.createUser();
        // test to see that the content of both lists match
        for (int i = 0; i < clone.size(); i++) {
            assertEquals(clone.get(i).getUserName(), UserData.getAllUsers().get(i).getUserName());
            assertEquals(clone.get(i).getPassword(), UserData.getAllUsers().get(i).getPassword());
            assertEquals(clone.get(i).getBanStatus(), UserData.getAllUsers().get(i).getBanStatus());
            assertEquals(clone.get(i).isAdminInd(), UserData.getAllUsers().get(i).isAdminInd());
        }

        // CHECK BanUser-BAN
        InputStream in_2 = new ByteArrayInputStream(("nonadmin").getBytes());
        System.setIn(in_2);
        BanUser BU = new BanUser();
        BU.banUser(u2);
        UserData.getAllUsers().get(1).setBanStatus(true);
        assertTrue("You have successfully banned nonadmin", UserData.getAllUsers().get(1).getBanStatus());


        //CHECK UserLogin - should not be allowed since banned
        InputStream in_3 = new ByteArrayInputStream(("nonadmin" + System.lineSeparator() + "123").getBytes());
        System.setIn(in_3);
        User u3 = UserLogin.loginUser();
        assertNull(u3);

        // CHECK BanUser-UNBAN
        InputStream in_5 = new ByteArrayInputStream(("nonadmin").getBytes());
        System.setIn(in_5);
        BanUser BU_2 = new BanUser();
        BU_2.unBanUser();
        UserData.getAllUsers().get(1).setBanStatus(false);
        assertFalse("You have successfully unbanned nonadmin", UserData.getAllUsers().get(1).getBanStatus());

        //CHECK UserLogin - should be allowed since NOT banned
        in_3 = new ByteArrayInputStream(("nonadmin" + System.lineSeparator() + "123").getBytes());
        System.setIn(in_3);
        User u4 = UserLogin.loginUser();

        assertEquals(u1.getUserName(), u4.getUserName());
        assertEquals(u1.getPassword(), u4.getPassword());
        assertEquals(u1.getBanStatus(), u4.getBanStatus());
        assertEquals(u1.isAdminInd(), u4.isAdminInd());

        // deleting the new non-admin users from the cloned list
        clone.remove(u1);
        clone.remove(u2);

        //CHECK DeleteUser
        //acts as someone inputting the username of the new non-admin to delete that user
        in_2 = new ByteArrayInputStream(("nonadmin").getBytes());
        System.setIn(in_2);
        DeleteUser DU = new DeleteUser();
        DU.deleteUser(u2);

        InputStream in_4 = new ByteArrayInputStream(("nonadmin2" + System.lineSeparator() + "t").getBytes());
        System.setIn(in_4);
        DeleteUser DU_2 = new DeleteUser();
        DU_2.deleteUser(u2);
        // test to see that the content of both lists match
        for (int i = 0; i < clone.size(); i++) {
            assertEquals(clone.get(i).getUserName(), UserData.getAllUsers().get(i).getUserName());
            assertEquals(clone.get(i).getPassword(), UserData.getAllUsers().get(i).getPassword());
            assertEquals(clone.get(i).getBanStatus(), UserData.getAllUsers().get(i).getBanStatus());
            assertEquals(clone.get(i).isAdminInd(), UserData.getAllUsers().get(i).isAdminInd());
        }
    }
}
