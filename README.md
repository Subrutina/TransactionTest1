# TransactionTest
This is a sample project to demonstrate the use of Spring Boot and PostgreSQL for a simple transaction management system.

## Requirements
- JDK 11 or higher
- Apache Maven 3.6.3 or higher
- PostgreSQL 13

## Installing
- Clone the repository:
  git clone https://github.com/Subrutina/TransactionTest1.git
- Open the project in your preferred IDE (e.g. IntelliJ IDEA, Eclipse)
- Update the PostgreSQL connection details in src/main/resources/application.properties to match your environment settings:
     spring.datasource.url=jdbc:postgresql://localhost:5432/{database-name}
     spring.datasource.username={username}
     spring.datasource.password={password}
- Run the application by executing the main method in the com.example.transaction.TransactionApplication class.

## Usage
- You can use this application via a RESTful API using any HTTP client, such as curl or Postman.
- Adding a new client
  - To add a new client, send a POST request to the /clients endpoint with a JSON payload containing the client details. The required fields are name, email, and phoneNumber.
