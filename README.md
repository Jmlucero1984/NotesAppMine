# Notes App (DOCKERIZED FULLSTACK PROJECT)
Online app for creating notes with different category tags

## 🎇 General

Using Spring Boot in the backend, React to develop the frontend and a PostgreSQL database, implements a minimal interface to register a user, login, create notes with title, body and category. Implements react router to navigate beetwen three tabs, the Active notes, the Archived ones, and the last one, to filter all notes (active and archived) according to a selected category. You can also create new categories only available to the current user.
For authentication it implements JWT and cookie storage.

## 🎯 Key requirementents

User Stories
 - [x] Login/Authentication
 - [x] Notes CRUD
 - [x] Able to archive/unarchive notes
 - [x] Able to list user's active notes
 - [x] Able to list user's archived notes
 - [x] Able to create new categories for current user
 - [x] Able to change category to notes
 - [x] Able to add many categories to notes
 - [x] Able to delete categories
 - [x] Fitler notes by category

 
## 🔎 Technologies
- JAVA 17
- Lombok
- Spring Boot
- Spring Validation
- Spring Security
- JWT
- React + Vite
- React-router
- PostgreSQL
- Docker


## 🔩 Running up
 - Clone repository
 - Open folder in your code editor ( IntelliJ, VSCode, etc)
 - In terminal (be patient):
    ``` docker-compose up --build ```
 - Open web browser: localhost:3000  ( default user mail: user@user.com  and password: Password1! )

   🙏 Keep in mind even though it may seems to be all mounted and running, in an "oldie" equipment it could take a while to send requests and receive responses, please check de docker server console to verify transactions.

   TIP: Sometimes, even though the container dependecy order is specified on the docker-compose file, you must execute first the DB container in order to make sure Spring Boot finds it at the very first moment.

## 📷 Screenshots

*Registration form* 

![screenshot1](https://github.com/Jmlucero1984/NotesAppMine/blob/45d844098ecce388195ff25aa0042da50fd9e34b/screenshot_01.JPG)

*List of active notes with multiple category tags* 

![screenshot2](https://github.com/Jmlucero1984/NotesAppMine/blob/b2cda10190494564c42ba0bb353e222af303a8e7/screenshot_02.JPG)

*Note creation/edition in Modal mode* 

![screenshot3](https://github.com/Jmlucero1984/NotesAppMine/blob/b2cda10190494564c42ba0bb353e222af303a8e7/screenshot_03.JPG)

*Create a new category tag with your custom color* 

 
<p align="center">
 <img src="https://github.com/Jmlucero1984/NotesAppMine/blob/b2cda10190494564c42ba0bb353e222af303a8e7/screenshot_04.JPG" alt="Descripción de la imagen" >
</p>

 

*Tab for filtering notes according to one or many categories* 

![screenshot5](https://github.com/Jmlucero1984/NotesAppMine/blob/b2cda10190494564c42ba0bb353e222af303a8e7/screenshot_05.JPG)


 
 
