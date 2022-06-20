# CSC207 Summer 2022 - Specification
Group 249 - Akmar, Wing, Daniel, Benedek, Nicholas, Kate

---

This file details the specification of phase 1 of our project as of June 19, 2022 - after completing phase 0.

## Introduction

Our group intends to create a program that would emulate a video streaming service similar to Netflix or Youtube.  The user would interact with the program through the command line and the end product will lead users to a video (the scope for this project will use URL or Filepath as opposed to integrated media player).

Features of this program will include:
* Creating an account (Users and Content Creators)
* Searching for videos
* Adding/Uploading new videos (or references to videos)
* Social engagement with videos ("reacting" to videos)
* Commenting on videos
* Favouriting videos and creating playlists
* ...

## Specification

Listed below includes the classes that will be required in order to create the program described in the introduction

1. `User` - This will be an _abstract entity_ class used to make sure that its subclasses have basic functionality for all user types such as Username, Password and History (login, view, comment, like, favourite and search histories)
    * `Viewer` - This will be similar to the current `NonAdminUser` class and will be the basic default _entity class_
    * `Creator` - This will also be similar to `NonAdminUser` class but this _entity class_ will also include history and information related to the videos that user uploaded
    * `Admin` - This will be similar to the `AdminUser` _entity class_ and they will have the power to moderate comments, delete videos, and revoke privileges among other responsibilities
2. `Video` - This _entity class_ will be utilized to store all information pertaining to a specific video such as URL/Filepath, reactions (like/dislike), comments, tags (for searching)
3. `VideoManager` - This _Use Case_ class will be utilized to handle all operations regarding the `Video` class.  So deleting videos, changing likes/dislikes, changing URL/Filepath, updating tags, and more will all utilize `VideoManager`
4. `UserManager` - This _Use Case_ class will be utilized to handle all operations regarding the `User` class and its subclasses.  This includes all the operations it is currently responsible and the future additions including editing/deleting comments, changing likes/dislikes, changing search history, etc.
5. `Main`, `Presenter`,`MenuNavigator` - Will be controller and presenter classes that will be utilized to navigate various menus and display output for the user to interact with

## Contributors
|Name|Email Address|
|----|-------------|
|Akmar|akmar.chowdhury@mail.utoronto.ca|
|Wing|wing.zou@mail.utoronto.ca|
|Daniel|danielx.xu@mail.utoronto.ca|
|Benedek|b.balla@mail.utoronto.ca|
|Nicholas|nicholas.au@mail.utoronto.ca|
|Kate|katee.ma@mail.utoronto.ca|