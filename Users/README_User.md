# Service: Users

## Description:
This service handles the creation of a user in our application, where you can create, edit, and delete the account associated with an email. The database is based on MySQL 8.0.33 and is integrated with Docker.

---

## Main Functions:

1. **Save a User:**
   `saveUser` - Saves a user in the database, encrypting their password and verifying the email. It also ensures that a user with the same email cannot have two accounts.
2. **Update a User:**
   `updateUser` - Updates user data, also checking the email.
3. **Delete a User:**
   `deleteUser` - Deletes a user account associated with an email.
4. **Get a user by id or Email:**
   `getUserById` or `getUserByEmail` - Returns user information excluding the password.

### Code links:

- [Code `UserService`](./src/main/java/com/dixonpa/store/users/service/UserService.java)

---

## Database: MySQL
The service is using a MySQL database to store user information.

### Docker Compose configuration

The `docker-compose.yml` file is used to configure and run containers for the application and the database.

- **mysql:** Contains the `db_users` database.

---

## Author:
Paulo Alvarez.
