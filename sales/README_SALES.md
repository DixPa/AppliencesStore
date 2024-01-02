# Service: Sale

## Description:
This service handles and stores sales, where the `cart` service is queried to store the total price and generate a sale number. The database is based on MySQL 8.0.33 and is integrated with Docker.

---

## Main Functions:

1. **Save a purchase:**
   `saveSale` - Generates a sale number and saves the total price of the purchase, making a query to the `cart` service.
2. **Get a purchase:**
   `getSaleByIdUser` - Retrieves a purchase made based on the user ID.
3. **Delete a purchase:**
   `deleteSale` - Deletes a purchase once the products have been delivered.

### Code links:

- [Code `SaleService`](src/main/java/com/dixonpa/store/sales/service/SaleService.java)

---

## Database: MySQL
The service is using a MySQL database to store information.

### Docker Compose configuration

The `docker-compose.yml` file is used to configure and run containers for the application and the database.

- **mysql:** Contains the `db_sales` database.

---

## Author:
Paulo Alvarez.
