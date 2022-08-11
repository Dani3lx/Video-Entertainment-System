import entities.AdminUser;
import entities.NonAdminUser;
import entities.User;
import org.junit.BeforeClass;
import org.junit.Test;
import usecase.runtimeDataManager.AdminManager;
import usecase.runtimeDataManager.UserManager;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdminManagerTest {
    private static final UserManager UM = UserManager.getInstance();
    private static final NonAdminUser u1 = new NonAdminUser("k", "1");
    private static final AdminUser u = new AdminUser("p", "1");
    private final AdminManager AM = new AdminManager();

    @BeforeClass
    public static void setUp() {
        UM.updateData(u);
        UM.updateData(u1);
    }

    @Test
    public void banUserTest() {
        AM.banUser(u, "k");
        assertTrue(u1.getBanStatus());
    }

    @Test
    public void UnbanUserTest() {
        AM.unbanUser("k");
        assertFalse(u1.getBanStatus());
    }

    @Test
    public void deleteUserTest() {
        assertTrue(AM.deleteUser("k"));
        assertTrue(AM.deleteUser("p"));
        assertTrue(UM.getAllUsers().isEmpty());
    }

    @Test
    public void returnUsersbyBanTest() {
        ArrayList<User> users = new ArrayList<>();
        NonAdminUser u1 = new NonAdminUser("k", "1");
        u1.setBanStatus(true);
        NonAdminUser u2 = new NonAdminUser("a", "1");
        users.add(u1);
        users.add(u2);
        AdminManager AM = new AdminManager();
        ArrayList<String> usersReturned = new ArrayList<>();
        usersReturned.add("Username: k");
        assertEquals(usersReturned, AM.returnUsersByBan(users, true));
    }

    @Test
    public void returnUsersTest() {
        ArrayList<User> users = new ArrayList<>();
        NonAdminUser u1 = new NonAdminUser("k", "1");
        NonAdminUser u2 = new NonAdminUser("a", "1");
        users.add(u1);
        users.add(u2);
        AdminManager AM = new AdminManager();
        ArrayList<String> usersReturned = new ArrayList<>();
        usersReturned.add("Username: k");
        usersReturned.add("Username: a");
        assertEquals(usersReturned, AM.returnUsers(users));
    }
}
