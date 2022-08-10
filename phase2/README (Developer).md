# Developer README 

Developer guide for maintaining and updating this system
* Includes Major Design Decisions
* Rationale for Design Patterns
* How to add new features

Group 249 - Akmar, Wing, Daniel, Benedek, Nicholas, Kate

---

**CSC207 Summer 2022 Phase 2 - Video Entertainment System** 

## Functionality Overview

Functionality overview covers:
* Various actions available in our program
* How they are implemented
* Design Decisions (**DD**)
* How to maintain and update

### 1. Log-in System
### 2. User System
   1. Non-Admin User
   2. Admin User
### 3. Video Management System
   1. Video Browsing
   2. Video Management
   3. Video Uploading
### 4. Video Interaction System
   1. Ratings
   2. Comments
### 5. Playlist Management System
Playlist management system comprises all interactions related to playlists, which is essentially a collection of videos.
Playlist entity has 2 constructors (**DD**):
* `Playlist(String playlistName, String userName)` - used to initialize new playlists, only needs playlist name and username of creator
* `Playlist(String playlistName, int likes, ArrayList<String> uniqueIDs, String userName)` - used primarily to load existing playlists from csv files

Playlists utilize `PlaylistManager` for actions on the playlist.  Look at the User README file for a full list of playlist functionality.
Major Design Highlights:
* `PlaylistManager`, like other managers, are instantiated at program start as it is used for almost all playlist actions
* `PlaylistManager` includes a list of all playlists, allowing us to read from csv files easily and have a directory of existing playlists
* (**DD**) There is a `validatePlaylistAction` method that validates if a user has the authority to make changes to a playlist
  * currently the program functionality will only allow users who created the playlist to make changes to the playlist
  * there is a `clonePlaylist` method that allows users to copy a playlist so they can make changes to it - currently implemented to playlist reordering
* As discussed in the **DS** section, the Singleton pattern is utilized so the `PlaylistManager` does not depend on `VideoManager`
to perform actions with videos
* To update playlist actions, functionality can be added to `PlaylistManager` or to subclasses of `PlaylistManager`
### 6. Menu
   1. User Menu
   2. Playlist Menu
        * To add new playlist menu actions:
          1. (Optional) If you want to create a new submenu, `PlaylistMenuFactory.java`, `getMenu` contains all of the submenus related to playlist.
You can add a new case for new submenus.  You then have to create a class that implements menu in the `playlistsMenu` package
          2. In the `playlistsMenu` package there are the various playlist menus.  In order to add new actions, update the `actionList`
variable with the string that will be displayed in the menu that is associated with the new action
             * e.g. if you want to add a dislike playlist option, go to `PlaylistViewMenu.java` and add a new string to `actionList` such as `"dislike playlist"`
          3. In `PlaylistActionFactory.java`, add a new case with the same string name as the menu action (e.g. `case "dislike playlist":`)
          4. Create a new action class that implements action in the `playlistActions` package, and then include the constructor for the case action.
The abstract factory will utilize the `run();` and `next();` methods
   3. Video Menu
### 7. Presenter

## Overall Program Design

### Design Patterns

1. Abstract Factory for Menu 
2. Abstract Factory for Actions
3. Singleton for Manager instantiating and passing
4. 

### Clean Architecture

### SOLID Design




# 6. UML Diagram

Unlocked green lock: public

Locked orange lock: private

Key: protected

Grey circle: Package protected


## Contributors
|Name|Email Address|
|----|-------------|
|Akmar|akmar.chowdhury@mail.utoronto.ca|
|Wing|wing.zou@mail.utoronto.ca|
|Daniel|danielx.xu@mail.utoronto.ca|
|Benedek|b.balla@mail.utoronto.ca|
|Nicholas|nicholas.au@mail.utoronto.ca|
|Kate|katee.ma@mail.utoronto.ca|