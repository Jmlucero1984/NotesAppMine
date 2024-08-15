# Notes App
Online app for creating notes 

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

## 📷 Screenshots

*Note creation in Modal mode* 
![screenshot1](https://github.com/Jmlucero1984/NotesAppMine/blob/45d844098ecce388195ff25aa0042da50fd9e34b/screenshot_01.JPG)

*Showing active notes* 
![screenshot2](https://github.com/user-attachments/assets/c076e1eb-7bda-461c-9bb3-b781ca250c82)

*Tab for filtering notes according to a category* 
![screenshot3](https://github.com/user-attachments/assets/c5884a66-2ed5-42d1-9f89-ec8366d335cc)


 
 
