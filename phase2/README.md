# USER README

Quickstart guide to using this program
Group 249 - Akmar, Wing, Daniel, Benedek, Nicholas, Kate

---

**CSC207 Summer 2022 Phase 2 - Video Entertainment System** is group 0249's initial implementation of their CSC207
project. It aims to cover all of the required functionality for phase 2 specifications whilst still following clean
architecture and SOLID principles. The program serves as a video entertainment system that has the ability to store
persistent data about users, videos, and playlists. Each video created will have a unique ID, ensuring that all videos
are unique even if they have similar features such as title or urls; video objects store real urls that lead to videos
on the internet. Each playlist consists of existing videos in the system.

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
        * View all videos uploaded by themselves plus upload/delete/edit their own videos
            * In the case of uploading videos, the description and categories are optional. Press enter to skip it.
            * In order to edit or delete videos, the non-admin user needs to input the unique ID of the video. The view
              all videos function lists all the videos with their corresponding unique IDs.
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
        * Browse videos by name, categories, and uploader
        * Create/display playlists and browse playlists by name
5. When you log out, you will be returned to the login menu
    * **You Must Exit Program by inputting 3 in order for the session history to be saved**

## 2.2 Video Browse Menu

* **Browse** videos by name, categories, and uploader
    * For **browse by name**, input keywords in the video title (not case sensitive)
    * For **browse by category**, input a category name then press enter to input the next category name. Input **
      CONTINUE** to began search.
    * For **browse by uploader**, input the uploader name
    * After browsing, a list of video will be presented
        * Select a video by entering the number associated with each video
        * If a valid video was selected, then the video information will be displayed
          * This includes title, uploader, categories, date uploaded, contents, and their ratings.
          * The program will then navigate to the video interaction menu.
    * View all videos uploaded by themselves plus upload/delete/edit their own videos
        * In the case of uploading videos, the description and categories are optional. Press enter to skip it.
        * In order to edit or delete videos, the non-admin user needs to input the unique ID of the video. The view all
          videos function lists all the videos with their corresponding unique IDs.
        * Non-admin users can only delete and edit their own videos


## 2.3 Video Interaction menu

* **Interact** with the videos by liking, disliking or commenting on the video.
  * Each user gets to comment once on each video.
    * That comment can be edited or deleted.
  * When a user rates a video, all their previous rating on the video will be replaced.
    * So if a user likes a video and then dislike it later, their like record will be erased.
  * The user will be returned to their user menu after an action was selected.

## 2.4 Playlist Menu

Playlists are a collection of videos that are created by a user. Playlists may be used by users other than the creator
but any changes to the playlist can only be made by the user that created the playlist. Future functionality will give
Admin's the right to change playlists, users ability to copy playlists and an autoplay playlist (automatically play
video after video)

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
        * The above two actions will allow the user to add and delete videos from the playlist if they were the creator
          of the playlist
        * You may have to press enter twice here if the program doesn't continue immediately after entering the video
          name
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

# 3. Potential Future Additions

* Usage of database tables for data storage
* "viewing" video will open the url on system browser
* Expanded UI
* Expand Video Actions
* Include more languages
* Expand Playlist Actions
    * Favourites
    * Allow users to copy playlists
    * Allow admins to have access to change playlists even if they are not the creators
    * Index playlist items by uniqueID so that name changes and whatnot will not adverse affect playlist
    * Have autoplay when viewing playlist

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

# 5. Data

We have 3 data files `datasets/Data.csv`, `datasets/PlaylistData.csv`, `datasets/VideoData.csv` which stores various
information corresponding to our program. They are in `.csv` and are delimited by commas.

`datasets/Data.csv` includes user data in the form:

`[anarcyst,2,false,false,2022-07-22 16:00:23/2022-07-21 23:27:36/2022-07-23 23:48:37/]`
This is interpreted as:
`[Username,Password,isBanned?,isAdmin?,loginhistory]`

`datasets/PlaylistData.csv` includes playlist data in the form:
`[anarcysts_vids,0,0665353f-6446-48ad-8628-864986d2fc5e/,anarcyst]`
This is interpreted as:
`[PlaylistName,NumberofLikes,VideoUniqueIDs,UploaderUsername]`

`datasets/VideoData.csv` includes video data in the form:
`[anarcyst,Why I hate Nintendo,I speak at length about how Nintendo consistently rips people off due to good-will of the past and how they fail to embrace modernization,gaming/pokemon/nintendo/cheap/online/internet/anarcyst/,www.youtube.com/u/anarcyst/videolink,1bc0ae3c-6f27-4fa8-88c1-e2ab5c75f252,0/0/,2022-07-23T23:52:21.818647700]`

This is interpreted as:
`[UploaderUserName,VideoName,VideoDescription,Categories,ContentLocation,UniqueID,ratings,upload_date]`

## Contributors

|Name|Email Address|
|----|-------------|
|Akmar|akmar.chowdhury@mail.utoronto.ca|
|Wing|wing.zou@mail.utoronto.ca|
|Daniel|danielx.xu@mail.utoronto.ca|
|Benedek|b.balla@mail.utoronto.ca|
|Nicholas|nicholas.au@mail.utoronto.ca|
|Kate|katee.ma@mail.utoronto.ca|