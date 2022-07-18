import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.junit.Assert.*;

public class UserManagerTest {

    @Test
    public void testValidateUser(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("k","1");
        UM.updateData(u1);
        assertEquals(u1, UM.validateUser("k","1"));

    }

    @Test
    public void testChangePass(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a","b");
        UM.changePassword(u1, "new");
        assertEquals("new",u1.getPassword());

    }

    @Test
    public void testInstantiateUser(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a","b");
        assertTrue(u1.equals(UM.instantiateUser("a","b",false)));
    }

    @Test
    public void testValidateUsername(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a","b");
        assertTrue(UM.validateUserName(u1, "a"));
    }

    @Test
    public void testvalidateBanStatus(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a","b");
        u1.setBanStatus(true);
        assertTrue(UM.validateBanStatus(u1));
    }

    @Test
    public void testUpdateData(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        NonAdminUser u1 = new NonAdminUser("a","b");
        UM.updateData(u1);
        assertEquals(u1, UM.getAllUsers().get(0));
    }
    @Test
    public void testGetRole(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        AdminUser u1 = new AdminUser("a","b");
        assertTrue(UM.getRole(u1));
    }
    @Test
    public void testReturnVids(){
        VideoManager VM = new VideoManager();
        UserManager UM = new UserManager(VM);
        ArrayList<String> cates = new ArrayList<>(List.of("serious"));
        ArrayList<String> ratings = new ArrayList<>(Arrays.asList("10","0"));
        ArrayList<Video> vids = new ArrayList<>();
        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
        vids.add(v1);
        VM.setVids(vids);
        ArrayList<String> names = new ArrayList<>(List.of("popmusic"));
        assertEquals(v1, UM.returnVideos(names,"name").get(0));
    }
}
