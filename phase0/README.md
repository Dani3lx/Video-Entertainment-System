# READ ME

Group 249 - Akmar, Wing, Daniel, Benedek, Nicholas, Kate

---

**CSC207 Summer 2022 Phase 0 - Login Explorer** is group 0249's login backbone for their CSC207 project.  It aims to cover all the required functionality for phase 0 specifications whilst still following clean architecture guidelines.  We expect to add onto this backbone to create a video explorer system (akin to Youtube) in the future.

## Quick Start 
1. Set SDK to Amazon Coretto 11
2. Run Main.Main
3. Once the program starts, the main menu has the following options: log in to an account, create a new non-admin user, or exit the program
    * There are no non-admin users to begin with, so you would need to create one
    * Only admin users are allowed to create admin users, so the program comes with a default admin user with the following credentials:
        * **Username:** admin
        * **Password:** admin
4. Admin users and non-admin users have different permissions so their menus will look different
5. When you fail to log in to an account, you will be returned to the main menu
6. When you log out of an account, you will be returned to the main menu 
7. In order to save all the user data into the database (data.csv), you need to exit the program through the main menu



## Contributors
|Name|Email Address|
|----|-------------|
|Akmar|akmar.chowdhury@mail.utoronto.ca|
|Wing|wing.zou@mail.utoronto.ca|
|Daniel|danielx.xu@mail.utoronto.ca|
|Benedek|b.balla@mail.utoronto.ca|
|Nicholas|nicholas.au@mail.utoronto.ca|
|Kate|katee.ma@mail.utoronto.ca|