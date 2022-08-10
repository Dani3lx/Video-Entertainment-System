import entities.*;
import org.junit.*;
import usecase.runtimeDataManager.UserManager;
import usecase.runtimeDataManager.VideoManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.*;

public class UserManagerTest {
    private static final VideoManager VM = new VideoManager();
    private static final UserManager UM = new UserManager(VM);

    @BeforeClass
    public static void setUp() {
        NonAdminUser u1 = new NonAdminUser("k", "1");
        UM.updateData(u1);

        ArrayList<String> cates = new ArrayList<>(List.of("serious"));
        Ratings r = new Ratings();
        ArrayList<Video> vids = new ArrayList<>();
        Video v1 = new Video("k", "popmusic", "amazing music", cates, "url", "1", r, "today", new ArrayList<Comments>(List.of(new Comments("", "", ""))));
        vids.add(v1);
        VM.setVids(vids);
    }

    @Test
    public void testValidateUser() {

        NonAdminUser u1 = new NonAdminUser("k", "1");
        assertTrue(u1.equals(UM.validateUser("k", "1")));

    }

    @Test
    public void testChangePass() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a", "b");
        UM.changePassword(u1, "new");
        assertEquals("new", u1.getPassword());

    }

    @Test
    public void testInstantiateUser() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a", "b");
        assertTrue(u1.equals(UM.instantiateUser("a", "b", false)));
    }

    @Test
    public void testValidateUsername() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a", "b");
        assertTrue(UM.validateUserName(u1, "a"));
    }

    @Test
    public void testvalidateBanStatus() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a", "b");
        u1.setBanStatus(true);
        assertTrue(UM.validateBanStatus(u1));
    }

    @Test
    public void testUpdateData() {

        NonAdminUser u1 = new NonAdminUser("k", "1");
        assertTrue(u1.equals(UM.getAllUsers().get(0)));
    }

    @Test
    public void testGetRole() {
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        AdminUser u1 = new AdminUser("a", "b");
        assertTrue(UM.getRole(u1));
    }

}
