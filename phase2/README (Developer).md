# Developer README

Developer guide for maintaining and updating this system

* Includes Major Design Decisions
* Rationale for Design Patterns
* How to add new features

This document contains the following Phase 2 submission requirements:

* List of features implemented for phase 2
* A list of design decisions and explanations about how our code has improved since Phase 1. (Design Document 3)
* List of design patterns that we used, why we used them and how it works (Design Document 2)
* Description of how our program does/could follow 7 principles of universal design (Design Document 4)

---

# CSC207 Summer 2022 Phase 2 - Video Entertainment System

Group 0249 - Akmar, Wing, Daniel, Benedek, Nicholas, Kate

## 1. Phase 2 Features

* Overhauled and refactored the menu system for extensibility
* Implemented a new user interface system such that it is easily swappable between different platform of user
  interfaces.
* Implemented a new text system that can offer an easy and efficient way to implement different languages to the
  program.
* Expanded Video editing options
* Implementation of Video Ratings
* Implementation of Video commenting

## 2. Improvements from Phase 1

This section is our submission for Design Document 3 - Phase 2.

From our feedback for Phase 1, we had committed most of our resources to refactor our code to follow clean architecture,
obey SOLID design principles, apply several design patterns (to help us achieve the former 2) and remove code smells.
In terms of program functionality, we heavily expanded on the ratings system and implemented a commenting system. We
will note how design patterns were implemented and their purpose in the next section. This section will focus on design
decisions and explanations of how our code has significantly improved since Phase 1.

* 

## 3. Design Patterns and Rationale

This section is our submission for Design Document 2 - Phase 2.

In Phase 1, we did not apply any design patterns, and it caused many issues with respect to code smells and SOLID
principles.
In Phase 2, we significantly refactored the code with an emphasis on design patterns so that our program follows clean
architecture.

We implemented the following Design Patterns:

1. Abstract Factory for Menu
2. Abstract Factory for Actions
3. Singleton for instantiating and passing entity Managers
4. Dependency Injection
5. Iterator for Playlist

Below we will explain how each design pattern works (using specific class and package names), why we implemented it,
how it improved our code

3. The Singleton design pattern was implemented in VideoManager, UserManager, PlaylistManager,
   all within the usercase/runtimedatamanager package. Each of these classes define
   a static getInstance() method as well as storing a static instance attribute. When called, if no instance
   of the class yet exists in the program, one will be instantiated and returned. If an instance already exists
   within the attribute, it will be returned. In this way, we ensure that only one single instance of each Manager
   exists within the entire program so that all operations performed on the lists of playlists, users, or videos
   will utilize and modify the same list which will then be saved by DataManager. An example of this pattern is in
   PlaylistManager,
   where a VideoManager is required in the method namesInPlaylist(). Thereby, this method calls
   VideoManager.getInstance()
   to access a method within the other class to access the names of videos by way of their uniqueIDs. Furthermore, using
   this design
   pattern eliminates the need to pass the Manager classes around as parameters to constructors within the use case
   layer,
   eliminating the code smell of long parameter lists.


5. The Iterator design pattern was implemented for the class Playlist. Playlist implements the Iterable interface, and
   defines the
   class iterator within, which includes the hasNext() and next() methods.
   There are a variety of methods in PlaylistManager such as for example
   deleteFromPlaylist that uses a for-each loop to directly iterate over the
   String uniqueIDs stored by the Playlist instead of having to first call
   the getUniqueIDs() method. In this way, we can avoid other classes being aware
   of the underlying implementation of Playlist, since Playlist might store a list of Video objects
   like we had it initially, or String UniqueIDs like we do now, without the other classes
   having to change accordingly. Hence, complying with the open-close design
   principle.

## 4. Principles of Universal Design

This section is our submission for Design Document 4 - Phase 2.

In this section we speak about which of the principles of universal design our program fulfills, which principles our
program
can fulfill with some extensions. We believe that extending our program through a GUI would be next best step for us our
program to be more in line with the principles.

The Principles:

1. Equitable Use
2. Flexibility in Use
3. Simple and Intuitive Use
4. Perceptible Information
5. Tolerance for Error
6. Low Physical Effort
7. Size and Space for Approach and Use

## Updating and Extending the program

This (optional) section serves as a guide for developers on how to extend the program. While methods that deal with
entities
can be implemented through the associated manager use-case class, the menu and presenter are a bit more involved to
update.

### Menu

For adding new Menu options, you can see the writeups on the design pattern above. Implementing new menu options for
Playlist,
Videos and Users are all quite similar and I have used playlist as an example (you can switch out a few class names to
do
the same for video and user menu extensions)

2. Playlist Menu
    * To add new playlist menu actions:
        1. (Optional) If you want to create a new submenu, `PlaylistMenuFactory.java`, `getMenu` contains all of the
           submenus related to playlist.
           You can add a new case for new submenus. You then have to create a class that implements menu in
           the `playlistsMenu` package
        2. In the `playlistsMenu` package there are the various playlist menus. In order to add new actions, update
           the `actionList`
           variable with the string that will be displayed in the menu that is associated with the new action
            * e.g. if you want to add a dislike playlist option, go to `PlaylistViewMenu.java` and add a new string
              to `actionList` such as `"dislike playlist"`
        3. In `PlaylistActionFactory.java`, add a new case with the same string name as the menu action (
           e.g. `case "dislike playlist":`)
        4. Create a new action class that implements action in the `playlistActions` package, and then include the
           constructor for the case action.
           The abstract factory will utilize the `run();` and `next();` methods

### Presenter

- how to update presenter
- other languages

## UML Diagram

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