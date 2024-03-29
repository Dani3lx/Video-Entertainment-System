import entities.NonAdminUser;
import entities.User;
import org.junit.Test;
import usecase.AdminManager;
import usecase.UserManager;
import usecase.VideoManager;
import org.junit.BeforeClass;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdminManagerTest {
    private static final VideoManager VM = new VideoManager();
    private static final UserManager UM = new UserManager(VM);
    private static final NonAdminUser u1 = new NonAdminUser("k", "1");
    private static final AdminManager AM = new AdminManager(UM, VM);

    @BeforeClass
    public static void setUp() {

        UM.updateData(u1);
    }

    @Test
    public void banUserTest() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k", "1");
        AdminManager AM = new AdminManager(UM, VM);
        AM.banUser(u1);
        assertTrue(u1.getBanStatus());
    }

    @Test
    public void UnbanUserTest() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k", "1");
        u1.setBanStatus(true);
        AdminManager AM = new AdminManager(UM, VM);
        AM.unbanUser(u1);
        assertFalse(u1.getBanStatus());
    }

    @Test
    public void deleteUserTest() {

        AM.deleteUser(u1);
        assertTrue(UM.getAllUsers().isEmpty());
    }

    @Test
    public void returnUsersbyBanTest() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        ArrayList<User> users = new ArrayList<>();
        NonAdminUser u1 = new NonAdminUser("k", "1");
        u1.setBanStatus(true);
        NonAdminUser u2 = new NonAdminUser("a", "1");
        users.add(u1);
        users.add(u2);
        AdminManager AM = new AdminManager(UM, VM);
        ArrayList<String> usersReturned = new ArrayList<>();
        usersReturned.add("Username: k");
        assertEquals(usersReturned, AM.returnUsersByBan(users, true));
    }

    @Test
    public void returnUsersTest() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        ArrayList<User> users = new ArrayList<>();
        NonAdminUser u1 = new NonAdminUser("k", "1");
        NonAdminUser u2 = new NonAdminUser("a", "1");
        users.add(u1);
        users.add(u2);
        AdminManager AM = new AdminManager(UM, VM);
        ArrayList<String> usersReturned = new ArrayList<>();
        usersReturned.add("Username: k");
        usersReturned.add("Username: a");
        assertEquals(usersReturned, AM.returnUsers(users));
    }


}
