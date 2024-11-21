# Faculty-Management-System-java

## **OVERVIEW**
The Faculty Management System is a Java-based application that facilitates efficient management of faculty records, including functionalities to add, update, view, and delete faculty details. It uses a MySQL database for data persistence and adheres to modular programming practices, ensuring maintainability and scalability.

## **FEATURES**
1. **Add Faculty**: Create new faculty records by providing details such as name, department, email, and phone number.
2. **View All Faculty**: Display all faculty records stored in the database.
3. **Update Faculty**: Modify existing faculty details using their unique ID.
4. **Delete Faculty**: Remove faculty records from the system based on their ID.
5. **Exit**: Terminate the application gracefully.
   
## **Technologies Used**

1. **Programming Language**: Java
2. **Database**: MySQL
3. **JDBC**: For database connectivity
4. **Architecture**:
DAO (Data Access Object) pattern for database operations
Modular classes for clean separation of concerns
Installation and Setup

## **Prerequisites**

- Java Development Kit (JDK) 8 or higher
- MySQL Server
- MySQL JDBC Driver

## **Usage**
Launch the application.
Choose from the menu options to perform the desired actions:
1. Add faculty details
2. View all existing records.
3. Update a specific record by ID.
4. Delete a record by ID.

Follow the prompts to interact with the system.
## **Code Highlights**
1. Modular Design: Each functionality is encapsulated in separate classes:
2. Faculty: Represents a faculty entity.
3. DatabaseConnection: Handles database connectivity.
4. FacultyDAO: Provides CRUD operations for the faculty table.
5. Error Handling: Includes robust exception handling to manage SQL and connection issues.
6. Scalability: Can be extended to support additional features like search or advanced reporting.
Future Enhancements


Contributions are welcome! If you want to improve the project or fix bugs, feel free to submit a pull request.

