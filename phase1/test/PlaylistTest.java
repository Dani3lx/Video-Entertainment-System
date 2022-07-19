//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//public class PlaylistTest {
//
//    @Test
//    public void addToPlaylistTest() {
//        Playlist play = new Playlist("Music","k");
//        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
//        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("0","0"));
//        Video v = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
//        PlaylistManager PM = new PlaylistManager();
//        assertTrue(PM.addToPlaylist(play, v));
//        assertEquals(1, play.getUniqueIDs().size());
//
//    }
//    @Test
//    public void deleteFromPlaylistTest(){
//        Playlist play = new Playlist("Music","k");
//        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
//        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("0","0"));
//        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
//        PlaylistManager PM = new PlaylistManager();
//        assertTrue(PM.addToPlaylist(play, v1));
//
//        ArrayList<String> cates2 = new ArrayList<String>(Arrays.asList("fun"));
//        ArrayList<String> ratings2 = new ArrayList<String>(Arrays.asList("0","0"));
//        Video v2 = new Video("t","rockmusic","amazing music",cates2, "url", "2",ratings2,"today");
//        assertTrue(PM.addToPlaylist(play, v2));
//
//        assertEquals(2, play.getUniqueIDs().size());
//
//        assertTrue(PM.deleteFromPlaylist(play, v2));
//        assertEquals(1, play.getUniqueIDs().size());
//
//
//    }
//    //Ratings not implemented yet
//    @Test
//    public void reorderPlaylistByRatingTest(){
//        Playlist play = new Playlist("Music","k");
//        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
//        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("10","0"));
//        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
//        PlaylistManager PM = new PlaylistManager();
//        PM.addToPlaylist(play, v1);
//
////        ArrayList<String> cates2 = new ArrayList<String>(Arrays.asList("fun"));
////        ArrayList<String> ratings2 = new ArrayList<String>(Arrays.asList("2","0"));
////        Video v2 = new Video("t","rockmusic","amazing music",cates2, "url", "2",ratings2,"today");
////        PM.addToPlaylist(play, v2);
//
//        Playlist play_dup = new Playlist("Music1","k1");
//        play_dup.addUniqueID(v1.getUniqueID());
////        play_dup.addVideo(v2);
//
//        assertTrue(play_dup.equals(PM.reorderPlaylistByName(play)));
//    }
//
//    @Test
//    public void reorderPlaylistByNameTest(){
//        Playlist play = new Playlist("Music","k");
//        ArrayList<String> cates = new ArrayList<String>(Arrays.asList("energizing"));
//        ArrayList<String> ratings = new ArrayList<String>(Arrays.asList("10","0"));
//        Video v1 = new Video("k","popmusic","amazing music",cates, "url", "1",ratings,"today");
//        PlaylistManager PM = new PlaylistManager();
//
//
//        ArrayList<String> cates2 = new ArrayList<String>(Arrays.asList("fun"));
//        ArrayList<String> ratings2 = new ArrayList<String>(Arrays.asList("2","0"));
//        Video v2 = new Video("t","rockmusic","amazing music",cates2, "url", "2",ratings2,"today");
//        PM.addToPlaylist(play, v2);
//        PM.addToPlaylist(play, v1);
//
//        Playlist play_dup = new Playlist("Music1","k1");
//        PM.addToPlaylist(play_dup, v1);
//        PM.addToPlaylist(play_dup, v2);
//
//        assertTrue(play_dup.equals(PM.reorderPlaylistByName(play)));
//    }
//
//
//    @Test(timeout = 80)
//    public void likePlaylistTest(){
//        PlaylistManager PM = new PlaylistManager();
//        Playlist play = new Playlist("Music","k");
//        PM.likePlaylist(play);
//        assertEquals(1,play.getLikes());
//    }
//}



