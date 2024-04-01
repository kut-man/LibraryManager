# LibraryManager

LibraryManager is a Java Spring project designed to manage a library system efficiently. It utilizes Spring Boot, PostgreSQL, Spring Data JPA, Thymeleaf, and Hibernate to provide a robust and user-friendly interface for handling books and persons within a library.

## Features

- **Entities:** The project consists of two main entities: Person and Book. 
- **Book Management:** Users can add, edit, and delete books. They can also search for specific books.
- **Person Management:** Users can add, remove, and edit persons. 
- **Assign and Release Books:** Books can be assigned to specific persons, and then released when returned.
- **Display Books Assigned to Persons:** The system displays the books assigned to each person. If a book has been assigned to a person for more than 10 days, its name is displayed in red.
- **Pagination and Sorting:** Results are paginated and can be sorted for easier navigation.

## Technologies Used

- **Spring Framework:** Utilized for dependency injection and managing application components.
- **Spring Boot:** For rapid application development and easy setup.
- **PostgreSQL:** Used as the database management system to store data.
- **Spring Data JPA:** Simplifies data access and persistence.
- **Thymeleaf:** Server-side Java template engine for web and standalone environments.
- **Hibernate:** Object-relational mapping framework for the Java language.

## Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/kut-man/LibraryManager
   ```

2. **Database Configuration:**
   - Set up a PostgreSQL database and configure the connection details in `application.properties`.
   
3. **Build and Run:**
   - Navigate to the project directory and run the following command:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the Application:**
   - Once the application is running, open a web browser and go to `http://localhost:8080` to access the LibraryManager interface.

## Usage

- Upon accessing the application, you'll be presented with options to manage books and persons.
- Navigate through the intuitive interface to add, edit, delete, or search for books and persons.
- Assign books to specific persons and release them when returned.
- View which books are assigned to each person, with overdue books highlighted in red.
