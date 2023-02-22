# TransactionTest
TransactionTest1 is a simple Java Spring Boot application that simulates money transactions between users.

## Requirements
- JDK 11 or higher
- Apache Maven 3.6.3 or higher
- PostgreSQL 13

## Installing
- Clone the repository:
  ```git clone https://github.com/Subrutina/TransactionTest1.git```
- Open the project in your preferred IDE (e.g. IntelliJ IDEA, Eclipse)
- Update the PostgreSQL connection details in src/main/resources/application.properties to match your environment settings:
     ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/{database-name} 
     spring.datasource.username={username} 
     spring.datasource.password={password} 
     ```
  Replace {database-name}, {username}, and {password} with your own values.
- Run the application by executing the main method in the com.example.transaction.TransactionApplication class.

## Usage
- You can use this application via a RESTful API using any HTTP client, such as curl or Postman.
- The implemented functionalities are: 
  - Creating a transaction (sending money)
  - Receiving money
  - Listing all the transactions in the database
  - Listing all the transactions of a single user given their id(JMBG)
  - Listing all the transactions between given dates

