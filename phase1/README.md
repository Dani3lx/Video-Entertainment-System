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



## Contributors
|Name|Email Address|
|----|-------------|
|Akmar|akmar.chowdhury@mail.utoronto.ca|
|Wing|wing.zou@mail.utoronto.ca|
|Daniel|danielx.xu@mail.utoronto.ca|
|Benedek|b.balla@mail.utoronto.ca|
|Nicholas|nicholas.au@mail.utoronto.ca|
|Kate|katee.ma@mail.utoronto.ca|