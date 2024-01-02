# Service: Cart

## Description:
This service manages and stores products in a shopping cart, where you can add an item, edit the quantity, and delete. This cart is associated with a user's account. The database is based on MySQL 8.0.33 and is integrated with Docker.

---

## Main Functions:

1. **Decide whether to create a new cart or add a product:**
   `decideCreateOrAdd` - Determines whether to create a new cart or add an item to an existing cart by searching the database for the cart associated with the provided email.
2. **Save a cart:**
   `saveCart` - Once the decision to create a new cart is made, it queries the user service to check if the email exists. If it does, it creates a new cart, initializing the total price to '0.0', and then calls the `addItemCart` method.
3. **Add an item:**
   `addItemCart` - To add an item, it queries the Products service for the added item. It then checks if the item already exists in the cart associated with the user's account. If it does, it calls the `plusQuantityOfProduct` method, and then the `updatePriceTotalCart` method.
4. **Increase the quantity of a product:**
   `plusQuantityOfProduct` - Increases the quantity of a product by one unit.
5. **Edit the quantity of a product:**
   `editQuantityOfProduct` - Edits the quantity of the product.
6. **Update the total price of the cart:**
   `updatePriceTotalCart` - Updates the total price of the cart based on all the items associated with the user's account.

### Code links:

- [Code `CartService`](./src/main/java/com/dixpa/store/Cart/service/CartService.java)

---

## Database: MySQL
The service is using a MySQL database to store information.

### Docker Compose configuration

The `docker-compose.yml` file is used to configure and run containers for the application and the database.

- **mysql:** Contains the `db_carts` database.

---

## Author:
Paulo Alvarez.
