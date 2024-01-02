# Service: Product

## Description:

This service manages product information, allowing the creation, editing, deletion, and querying of products by ID. The database uses MySQL 8.0.33 and integrates with Docker to simplify initial configuration.

---

## Key Functions:

1. **Save a Product:** `saveProduct` - Saves a new product to the database.
2. **Delete All Products:** `deleteAllProducts` - Deletes all products from the database.
3. **Delete a Product by ID:** `deleteProductById` - Deletes a product by its ID.
4. **Update a Product by ID:** `updateProductById` - Updates an existing product by its ID.
5. **Get a Product by ID:** `getProductById` - Retrieves a product by its ID.
6. **Get All Products:** `getAllProducts` - Returns a list of all available products.

### Code Links:

- [Code `ProductService`](./src/main/java/com/dixonpa/store/products/service/ProductService.java)
---

## Database: MySQL

The service uses MySQL as the database to store information. Make sure to have Docker and Docker Compose installed to simplify the initial setup.

### Docker Compose Configuration

The `docker-compose.yml` file is used to configure and run containers for the application and the database.

- **mysql:** Contains the `db_products` database.

---

## Author

Paulo Alvarez.
