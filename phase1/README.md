# READ ME

Group 249 - Akmar, Wing, Daniel, Benedek, Nicholas, Kate

---

**CSC207 Summer 2022 Phase 1 - Video Entertainment System** is group 0249's initial implementation of their CSC207 project. It aims to cover all of the required functionality for phase 1 specifications whilst still following clean architecture and SOLID principles. The program serves as a video entertainment system that has the ability to store persistent data about users, videos, and playlists. Each video created will have a unique ID, ensuring that all videos are unique even if they have similar features such as title or urls; video objects store real urls that lead to videos on the internet. Each playlist consists of existing videos in the system.

## Quick Start 
1. Set SDK to Amazon Coretto 11
2. Run Main.Main
3. At the start menu, user can choose to create a new non-admin account or log into an existing account.
    * One provided admin user's login credentials are:
        * **Username:** admin
        * **Password:** admin
4. There will be separate menus for admin and non-admin
   * Non-admin user menu gives the user the option to:
     * Change their **own** password
     * Check their **own** login history
     * Log out of their **own** account
     * Browse videos by name, categories, and uploader
       * For browse by name, input keywords in the video title, casing doesn't matter
       * For browse by category, input a category name then press enter to input the next category name. Input **CONTINUE** to began search.
       * For browse by uploader, input the uploader name
       * After browsing, a list of video will be presented
         * Select a video by entering the number associated with each video
           * User can like, dislike or add the video to a playlist. Select **return** to return to the user menu
     * View all videos uploaded by themselves plus upload/delete/edit their own videos
       * In the case of uploading videos, the description and categories are optional. Press enter to skip it.
       * In order to edit or delete videos, the non-admin user needs to input the unique ID of the video. The view all videos function lists all the videos with their corresponding unique IDs.
       * Non-admin users can only delete and edit their own videos
     * Create/display playlists and browse playlists by name
   * Admin user menu gives the user the action to:
     * Change their **own** password
     * Check their **own** login history
     * Log out of their **own** account
     * Create a new admin account
     * Delete a user
     * Ban a user
     * Unban a user
     * Browse videos by name, categories, and uploader like non-admin user
     * Create/display playlists and browse playlists by name
5. When you log out, you will be returned to the login menu 
    * **You Must Exit Program by inputting 3 in order for the session history to be saved**

## 2.2 Video Browse Menu
* **Browse** videos by name, categories, and uploader
   * For **browse by name**, input keywords in the video title (not case sensitive)
   * For **browse by category**, input a category name then press enter to input the next category name. Input **CONTINUE** to began search.
   * For **browse by uploader**, input the uploader name
      * After browsing, a list of video will be presented
      * Select a video by entering the number associated with each video
      * User can like, dislike or add the video to a playlist. Select **return** to return to the user menu
   * View all videos uploaded by themselves plus upload/delete/edit their own videos
      * In the case of uploading videos, the description and categories are optional. Press enter to skip it.
      * In order to edit or delete videos, the non-admin user needs to input the unique ID of the video. The view all videos function lists all the videos with their corresponding unique IDs.
      * Non-admin users can only delete and edit their own videos

## 2.3 Playlist Menu
Playlists are a collection of videos that are created by a user.  Playlists may be used by users other than the creator but any changes to the playlist can only be made by the user that created the playlist.  Future functionality will give Admin's the right to change playlists, users ability to copy playlists and an autoplay playlist (automatically play video after video)

* Selecting Playlist 
  * **Search Playlist By Name**
    * Will return a playlist if you know its exact name
    * After getting the playlist you will be able to access the playlist submenu
    * If you didn't enter an existing playlist name, you will be returned to the beginning of this menu
  * **Create New Playlist**
    * Can enter the name of a playlist to create
    * If the name already exists, playlist creation will fail
    * Once playlist is created, you will be able to access the playlist submenu
  * **View All Playlists** 
    * This lists all existing playlists
    * User can input a number to select a playlist among the entire list
    * Once playlist is selected, you will be able to access the playlist submenu
* Playlist Submenu
  * **View Playlist**
    * This will take you to the following submenu:
    1. **View Video Names in Playlist**
       * This will allow you to see which videos are in the playlist
    2. **View Playlist Likes**
       * This will show the playlist ratings
    3. **Change Playlist Name**
       * Allows you to change playlist name
       * If you did not create the playlist you will be unsuccessful
  * **Add Video to Playlist**
  * **Delete Video From Playlist**
    * The above two actions will allow the user to add and delete videos from the playlist if they were the creator of the playlist
  * **Reorder Playlist**
    * This will take you to the following submenu
    1. **Reorder Playlist Alphabetically**
       * The order of the videos inside the playlist is organized from A-Z
       * This is useful for video series with a naming convention and order such as educational content or vod's
    2. **Reorder Playlist by Rating**
       * This order of the videos inside the playlist is organized by highest rating first
       * This is useful if you want to see popular videos earlier
    3. **Shuffle Playlist**
       * This will randomize the order of videos inside the playlist
       * This is useful for playlists with music or random content
  * **Like Playlist**
    * You can like the playlist (no functionality at the moment but will be implemented in future rating system)


# 3. Potential Additions for Phase 2
* Expanded Ratings System
* Usage of database tables for data storage
* "viewing" video will open the url on system browser
* Expanded UI
* Expand Video Actions
* Expand Playlist Actions
  * Include Rating System
  * Favourites
  * Allow users to copy playlists
  * Allow admins to have access to change playlists even if they are not the creators
  * Index playlist items by uniqueID so that name changes and whatnot will not adverse affect playlist
  * Have autoplay when viewing playlist
  * Create Menu System for adding video (perhaps recently watched)
  * Create Menu System for deleting video (listing videos in the playlist)


# 4. Unit Tests:
* AdminManagerTest
  * Tests banning and unbanning of users
  * Tests user deletion
  * Tests the returning of users based on ban status
  * Tests the returning of all users inputted
* NonAdminManagerTest
  * Tests the ability to upload a video
  * Tests deletion of a video
  * Tests the ability to manipulate attributes of a video
* PlaylistTest
  * Tests the successfulness of adding a video to a playlist
  * Tests the various strategies of reordering a playlist
    * Name
    * Random shuffle
    * Rating
  * Tests the deletion of a video from a playlist
  * Tests liking a playlist
* UserManagerTest
  * Tests to see if the login credentials matches a user in the system
  * Tests to validate parts of a user login
    * Username
    * Ban status
  * Tests that the correct videos which correspond to either a name, category or
    uploader are returned
* VideoEditorTest
  * Tests that fields of the Video class can be edited
* VideoTest
  * Tests that uploading a video is successful
  * Tests that video deletion can be completed
  * Tests getting the correct Videos by Category, Uploader, Name, ID


## Contributors
|Name|Email Address|
|----|-------------|
|Akmar|akmar.chowdhury@mail.utoronto.ca|
|Wing|wing.zou@mail.utoronto.ca|
|Daniel|danielx.xu@mail.utoronto.ca|
|Benedek|b.balla@mail.utoronto.ca|
|Nicholas|nicholas.au@mail.utoronto.ca|
|Kate|katee.ma@mail.utoronto.ca|