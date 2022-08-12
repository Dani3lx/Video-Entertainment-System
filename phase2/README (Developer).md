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

* **Code Organization into well-named packages and/or classes**:
    * The main problem we had with this category in phase 1 was that some class names does not fit in with the others.
      For phase 2, not only we did we make sure all the classes in the same package follow the same naming convention,
      but also improved upon the division of our code into more well organized folders, making everything's really easy
      to
      find and organized.
* **Code Smells**:
    * In phase 1 a major code smell we had was the dependency of manager classes across the application. Almost every
      class except for our entities keeps a reference to the manager classes which is not ideal. To fix this problem, we
      implemented the singleton design pattern with the manager classes. This allows us to have one instance of the user
      manager classes throughout the whole program, thus we no longer need to pass them around in a parameter, and
      instead call them when they are needed.
    * The other code smell was the MenuDisplayer class, which is very long, has a lot of duplicated code and responsible
      for a lot of different responsibilities. To improve upon this, we separated each menu into its own class that all
      implements the Menu interface. We also generalized the menu system so that instead of having massive switch cases
      in each menu class, we now just have a run method that takes in user input and calls a corresponding action in a
      pre-determined action list to perform each menu action. This way each menu class has its own responsibility, and
      all the long duplicated code are replaced with a small simple algorithm.
    * The last code smell mentioned in the rubric was the hard coded strings, which we fixed by moving all the
      hard coded strings into a class called the English presenter, which includes all the general text that the program
      can output no matter what user interface it is using. Now all the strings are no longer specific to the shell UI
      and well separated into its own presenter class.
* **Design Patterns**:
    * For phase 1 we did not apply any design patterns. For phase 2 however, we have improved on this category a lot by
      actively searching for places to implement design patterns to make our code better, and some of them includes the
      abstract menu factory / factory design pattern for the menus and menu actions, the singleton pattern for the user
      managers, the iterator pattern for the playlists, and Dependency injection throughout the program. More detail on
      the next section.
* **Clean Architecture**:
    * In phase 1 we had some issues with controllers not acting like controllers as well as some UI not acting
      like UI. To fix this, we relocated code in our controller classes that seem like business logic to the
      appropriate use case classes, and took out the data passing part of UI to the controllers. This way each layer is
      doing exactly what it needs to do, adhering to the clean architecture.
* **SOLID**:
    * A principle we needed to improve on was the interface segregation principle. We fixed this by introducing
      interfaces to most of the layers such as one for presenter, one for user inputs, one for menu, menu action and
      more. This way we have decoupled the implementation of the code from where it is used.
    * For Open-closed principle, by refactoring the code such as implementing the abstract factory design pattern for
      the menus and menu actions, we can now easily create new menus and menu actions without needing to modify any
      other menu or menu actions, thus it is very open to expansion and close to modification. The same goes to the use
      of factory design pattern for the LanguagePresenter.
    * Dependency inversion was also improved upon by refactoring our code to adher to the clean architecture as
      mentioned
      above.
    * Single responsibility is also significantly improved upon by separating each menu into its own class, which allow
      each class to be responsible for one menu at a time. We have also made it so that calling and generating menus
      belong to its own factory class, so every step are well separated into different classes each with a specific
      responsibility.

## 3. Design Patterns and Rationale

This section is our submission for Design Document 2 - Phase 2.

In Phase 1, we did not apply any design patterns, and it caused many issues with respect to code smells and SOLID
principles.
In Phase 2, we significantly refactored the code with an emphasis on design patterns so that our program follows clean
architecture.

We implemented the following Design Patterns:

1. Abstract Factory / Factory method for Menu and Actions.
2. Singleton for instantiating and passing entity Managers
3. Dependency Injection
4. Iterator for Playlist

Below we will explain how each design pattern works (using specific class and package names), why we implemented it,
how it improved our code

1. For the abstract factories, we have two which are the `MenuFactory` in the `menufactories` package, and
   the `ActionFactory` in the `actionfactories` package. Both of these interfaces
   are implemented by concrete factories such as `UserMenuFactory` and `UserActionFactory` which are call located in the
   same package as their corresponding interface, which all utilizes the factory
   method pattern. For each of the concrete MenuFactory classes, they all have a getter that generates and returns a
   menu class
   that are closely related, for example `UserMenuFactory` returns menu such as `AdminMenu` and `NonAdminMenu` which are
   related to users. For each of the concrete ActionFactory classes, they all have a getter that generates and returns
   an Action
   that are closely related, such as `CheckLoginHistory` and `UserLogout` which are actions performed by users. The
   reason we implemented these patterns is so that we can have an interface for creating these different families of
   menus and menu actions wthout specifying their concrete classes, as well as creating a hierarchy that encapsulates as
   much of the construction of the menu as possible, as all we need are only dependent on the interface and not the
   implementations.


2. The Singleton design pattern was implemented in VideoManager, UserManager, PlaylistManager,
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

4. The Dependency Injection design pattern was implemented in many different classes in the program. In the Video class, the objects Ratings, 
    and Comments were directly applied to the class's constructor. In addition, this design pattern was used in all of the classes located in the menus folder. They took in objects of UserPrompt,
    MenuPresenter, LanguagePresenter and the User. This allowed for decreased coupling among classes as constructors would not have to be instantiated throughout the program.

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

When creating the GUI, different themes such as dark and high contrast themes could be created so 
that color-blind users don't need to struggle. 
2. Flexibility in Use

Our current text based interface allows users to input their responses at their own speed. In the future, 
we could implement features where users would be able to resize or have various methods of inputting the data.
3. Simple and Intuitive Use

The program is designed with clear commands and have all possible options listed and numbered to give
the user a clear understanding of the inputs they are allowed.
4. Perceptible Information

Features could be implemented to for different modes of presentation of the commands. 
5. Tolerance for Error

The program will promptly alert the user if their input is invalid. Other modes of input could be implemented for users.
6. Low Physical Effort

User does not need to move around the terminal as once a command has been received, a new menu will show.
7. Size and Space for Approach and Use
    1. If we were to use a graphical user interface, we can implement features such as being able to adjust the contrast
       of the information being presented, which can allow the user to be able to use the program efficiently
       regardless of their viewing angle on their monitor, thus provide more options for user's posture and position.

Only components necessary for the use of this progrma are a computer, keyboard and mouse. The user should be able to 
comfortably use the program without much trouble.

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

We have two main presenter interfaces that makes up the output system of our program. We have a menuPresenter and a
language presenter.

- The MenuPresenter is responsible for displaying information base on type of user interface. Right now since we are
  using only the shell UI, we only have a class called TerminalMenuPresenter which implements the MenuPresenter and is
  responsible for displaying all the information in a way that works best with the terminal. To expand and use other
  type of user interface, all that needs to be done is to create another class that implements the MenuPresenter, and
  make the implementation of the methods base on the type of user interface that is to be implemented, such as
  a graphical interface created using javaFX.
- The LanguagePresenter is responsible for handling all the hard coded text. Right now we have an english translation of
  the program that includes all the english text stored in EnglishPresenter, but future developer may choose to create
  new classes that implements
  the languagePresenter and easily add new language translations such as French.

### UserPrompt

We have a UserPrompt interface that is responsible for handling user inputs. So far we only have a TerminalUserPrompt
which implements this interface and handles all terminal based user inputs. Future developer are free to implement new
form of user inputs by creating new classes that implement the interface, such as input from a button in a graphical
user interface created with javaFX.

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