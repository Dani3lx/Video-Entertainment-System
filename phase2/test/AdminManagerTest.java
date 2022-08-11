import entities.AdminUser;
import entities.NonAdminUser;
import entities.User;
import org.junit.Test;
import usecase.runtimeDataManager.AdminManager;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdminManagerTest {
    private final UserManager UM = UserManager.getInstance();
    private final NonAdminUser u1 = new NonAdminUser("k", "1");
    private final AdminUser u = new AdminUser("p", "1");
    private final AdminManager AM = new AdminManager();


    @Test
    public void banUserTest() {
        UM.updateData(u);
        UM.updateData(u1);
        assertTrue(AM.banUser(u, "k"));
        assertTrue(u1.getBanStatus());
    }

    @Test
    public void UnbanUserTest() {
        UM.updateData(u);
        UM.updateData(u1);
        AM.banUser(u, "k");
        assertTrue(AM.unbanUser("k"));
        assertFalse(u1.getBanStatus());
    }

    @Test
    public void deleteUserTest() {
        UM.updateData(u1);
        assertTrue(AM.deleteUser("k"));
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
